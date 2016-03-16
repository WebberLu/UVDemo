/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.uvdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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
 * A sample which shows how to use {@link android.support.v4.widget.SwipeRefreshLayout} within a
 * {@link android.support.v4.app.ListFragment} to add the 'swipe-to-refresh' gesture to a
 * {@link android.widget.ListView}. This is provided through the provided re-usable
 * {@link SwipeRefreshListFragment} class.
 * <p/>
 * <p>To provide an accessible way to trigger the refresh, this app also provides a refresh
 * action item. This item should be displayed in the Action Bar's overflow item.
 * <p/>
 * <p>In this sample app, the refresh updates the ListView with a random set of new items.
 * <p/>
 * <p>This sample also provides the functionality to change the colors displayed in the
 * {@link android.support.v4.widget.SwipeRefreshLayout} through the options menu. This is meant to
 * showcase the use of color rather than being something that should be integrated into apps.
 */
public class UVFragment extends SwipeRefreshListFragment {

    private static final String LOG_TAG = UVFragment.class.getSimpleName();
    private UVListAdapter mFeedListAdapter;
    private List<UvItem> mUvItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";
    private String URL_UV_OPEN_DATA = "http://opendata.epa.gov" +
            ".tw/ws/Data/UV/?$orderby=PublishAgency&$skip=0&$top=1000&format=json";

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
        mUvItems = new ArrayList<UvItem>();
        mFeedListAdapter = new UVListAdapter(getActivity(), mUvItems);

        // Set the adapter between the ListView and its backing data.
        setListAdapter(mFeedListAdapter);

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
        Cache.Entry entry = cache.get(URL_UV_OPEN_DATA);
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
            JsonArrayRequest jsonReq = new JsonArrayRequest(URL_UV_OPEN_DATA, new Response.Listener<JSONArray>() {

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
            if(response.length()>0){
                mUvItems.clear();
            }
            for (int i = 0; i < response.length(); i++) {
                JSONObject itemObj = (JSONObject) response.get(i);
                UvItem item = new UvItem(itemObj.getString("SiteName"), itemObj.getString
                        ("UVI"), itemObj.getString("PublishAgency"), itemObj.getString("County"),
                        itemObj.getString("WGS84Lon"), itemObj.getString("WGS84Lat"), itemObj
                        .getString("PublishTime"));
                mUvItems.add(item);
            }
            mFeedListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
