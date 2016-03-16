package com.example.android.uvdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.android.common.logger.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 15/9/9.
 */
public class AQXFragment extends SwipeRefreshListFragment {


    private static final String LOG_TAG = AQXFragment.class.getSimpleName();
    private AQXListAdapter mAdapter;
    private List<AQXItem> mAqxItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";
    private String URL_AQX_OPEN_DATA = "http://opendata.epa.gov" +
            ".tw/ws/Data/REWXQA/?$orderby=SiteName&$skip=0&$top=1000&format=json";//update
//    private String URL_AQX_OPEN_DATA = "http://opendata.epa.gov" +
//            ".tw/ws/Data/AQX/?$orderby=SiteName&$skip=0&$top=1000&format=json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Notify the system to allow an options menu for this fragment.
        setHasOptionsMenu(true);
    }

    // BEGIN_INCLUDE (setup_views)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
        mAqxItems = new ArrayList<AQXItem>();
        mAdapter = new AQXListAdapter(getActivity(), mAqxItems);

        // Set the adapter between the ListView and its backing data.
        setListAdapter(mAdapter);

        // BEGIN_INCLUDE (setup_refreshlistener)
        /**
         * Implement {@link SwipeRefreshLayout.OnRefreshListener}. When users do the "swipe to
         * refresh" gesture, SwipeRefreshLayout invokes
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}. In
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}, call a method that
         * refreshes the content. Call the same method in response to the Refresh action from the
         * action bar.
         */
        // These two lines not needed,
        // just to get the look of facebook (changing background color & hiding the icon)
        getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
        getActivity().getActionBar().setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                doRequest();
//                initiateRefresh();
            }
        });
        doRequest();

        // END_INCLUDE (setup_refreshlistener)
    }
    // END_INCLUDE (setup_views)

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(com.example.android.uvdemo.R.menu.main_menu, menu);
    }

    // BEGIN_INCLUDE (setup_refresh_menu_listener)

    /**
     * Respond to the user's selection of the Refresh action item. Start the SwipeRefreshLayout
     * progress bar, then initiate the background task that refreshes the content.
     * <p/>
     * <p>A color scheme menu item used for demonstrating the use of SwipeRefreshLayout's color
     * scheme functionality. This kind of menu item should not be incorporated into your app,
     * it just to demonstrate the use of color. Instead you should choose a color scheme based
     * off of your application's branding.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case com.example.android.uvdemo.R.id.menu_refresh:
                Log.i(LOG_TAG, "Refresh menu item selected");

                // We make sure that the SwipeRefreshLayout is displaying it's refreshing indicator
                if (!isRefreshing()) {
                    setRefreshing(true);
                }
                new MaterialDialog.Builder(getActivity())
                        .title("title")
                        .content("content")
                        .positiveText("agree")
                        .negativeText("disagree")
                        .show();
                doRequest();
                // Start our refresh background task
//                initiateRefresh();
                return true;

            case com.example.android.uvdemo.R.id.menu_color_scheme_1:
                Log.i(LOG_TAG, "setColorScheme #1");
                item.setChecked(true);

                // Change the colors displayed by the SwipeRefreshLayout by providing it with 4
                // color resource ids
                setColorScheme(com.example.android.uvdemo.R.color.color_scheme_1_1, com.example.android.uvdemo.R.color.color_scheme_1_2,
                        com.example.android.uvdemo.R.color.color_scheme_1_3, com.example.android.uvdemo.R.color.color_scheme_1_4);
                return true;

            case com.example.android.uvdemo.R.id.menu_color_scheme_2:
                Log.i(LOG_TAG, "setColorScheme #2");
                item.setChecked(true);

                // Change the colors displayed by the SwipeRefreshLayout by providing it with 4
                // color resource ids
                setColorScheme(com.example.android.uvdemo.R.color.color_scheme_2_1, com.example.android.uvdemo.R.color.color_scheme_2_2,
                        com.example.android.uvdemo.R.color.color_scheme_2_3, com.example.android.uvdemo.R.color.color_scheme_2_4);
                return true;

            case com.example.android.uvdemo.R.id.menu_color_scheme_3:
                Log.i(LOG_TAG, "setColorScheme #3");
                item.setChecked(true);

                // Change the colors displayed by the SwipeRefreshLayout by providing it with 4
                // color resource ids
                setColorScheme(com.example.android.uvdemo.R.color.color_scheme_3_1, com.example.android.uvdemo.R.color.color_scheme_3_2,
                        com.example.android.uvdemo.R.color.color_scheme_3_3, com.example.android.uvdemo.R.color.color_scheme_3_4);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doRequest() {
        Log.i(LOG_TAG, "doRequest");
        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_AQX_OPEN_DATA);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    setRefreshing(false);
                    parseUvJson(new JSONArray(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonArrayRequest jsonReq = new JsonArrayRequest(URL_AQX_OPEN_DATA, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    VolleyLog.d(LOG_TAG, "Response: " + response.toString());
                    setRefreshing(false);
                    if (response != null) {
                        parseUvJson(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(LOG_TAG, "Error: " + error.getMessage());
                    setRefreshing(false);
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }

    }

    private void parseUvJson(JSONArray response) {
        try {
            if (response.length() > 0) {
                mAqxItems.clear();
            }
            for (int i = 0; i < response.length(); i++) {
                JSONObject itemObj = (JSONObject) response.get(i);
                AQXItem item = new AQXItem(itemObj.getString("SiteName")
                        , itemObj.getString("County")
                        , itemObj.getString("PSI")
                        , itemObj.getString("MajorPollutant")
                        , itemObj.getString("Status")
                        , itemObj.getString("SO2")
                        , itemObj.getString("CO")
                        , itemObj.getString("O3")
                        , itemObj.getString("PM10")
                        , itemObj.getString("PM2.5")
                        , itemObj.getString("NO2")
                        , itemObj.getString("WindSpeed")
                        , itemObj.getString("WindDirec")
                        , itemObj.getString("FPMI")
                        , itemObj.getString("NOx")
                        , itemObj.getString("NO")
                        , itemObj.getString("PublishTime"));
                mAqxItems.add(item);
            }
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
