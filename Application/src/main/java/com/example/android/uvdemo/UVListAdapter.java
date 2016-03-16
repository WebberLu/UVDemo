package com.example.android.uvdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by wen on 2015/7/9.
 */
public class UVListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<UvItem> uvItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public UVListAdapter(Activity activity, List<UvItem> uvItems) {
        this.activity = activity;
        this.uvItems = uvItems;
    }

    @Override
    public int getCount() {
        return uvItems.size();
    }

    @Override
    public Object getItem(int location) {
        return uvItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(com.example.android.uvdemo.R.layout.feed_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(com.example.android.uvdemo.R.id.name);
        TextView timestamp = (TextView) convertView
                .findViewById(com.example.android.uvdemo.R.id.timestamp);
        TextView statusMsg = (TextView) convertView
                .findViewById(com.example.android.uvdemo.R.id.txtStatusMsg);

        UvItem item = uvItems.get(position);

        name.setText("測站 : " + item.getSiteName() + "\n" + "縣市 : " + item.getCounty());

        // Converting timestamp into x ago format
//        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
//                Long.parseLong(item.getTimeStamp()),
//                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText("發布時間 : " + item.getPublishTime());
        statusMsg.setText("紫外線指數 : " + item.getUVI() + "\n" + "經緯 : " + dmsToddd(item.getTWD97Lat
                ()) + " , " + dmsToddd(item.getTWD97Lon()) + "\n" + "發布機關 : " + item
                .getPublishAgency());
        statusMsg.setVisibility(View.VISIBLE);

//        // Checking for null feed url
//        if (item.getUrl() != null) {
//            url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
//                    + item.getUrl() + "</a> "));
//
//            // Making url clickable
//            url.setMovementMethod(LinkMovementMethod.getInstance());
//            url.setVisibility(View.VISIBLE);
//        } else {
//            // url is null, remove from the view
//            url.setVisibility(View.GONE);
//        }
        // user profile pic
//        profilePic.setImageUrl(item.getProfilePic(), imageLoader);
//
//        // Feed image
//        if (item.getImge() != null) {
//            feedImageView.setImageUrl(item.getImge(), imageLoader);
//            feedImageView.setVisibility(View.VISIBLE);
//            feedImageView
//                    .setResponseObserver(new FeedImageView.ResponseObserver() {
//                        @Override
//                        public void onError() {
//                        }
//
//                        @Override
//                        public void onSuccess() {
//                        }
//                    });
//        } else {
//            feedImageView.setVisibility(View.GONE);
//        }

        return convertView;
    }

    private double dmsToddd(String dms) {
        double result = 0;
        double degrees = 0;
        double minutes = 0;
        double seconds = 0;
        String[] array = dms.split(",");
        degrees = Double.valueOf(array[0]);
        minutes = Double.valueOf(array[1]);
        seconds = Double.valueOf(array[2]);
        result = degrees + ((minutes * 60) + seconds) / (60 * 60);
        return result;
    }

    public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double EARTH_RADIUS = 6378137;
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

}
