package com.example.android.uvdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 15/9/8.
 */
public class AQXListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<AQXItem> aqxItems;

    public AQXListAdapter(Activity activity, List<AQXItem> aqxItems) {
        this.activity = activity;
        this.aqxItems = aqxItems;
    }
    @Override
    public int getCount() {
        return aqxItems.size();
    }

    @Override
    public Object getItem(int position) {
        return aqxItems.get(position);
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

        TextView name = (TextView) convertView.findViewById(com.example.android.uvdemo.R.id.name);
        TextView timestamp = (TextView) convertView
                .findViewById(com.example.android.uvdemo.R.id.timestamp);
        TextView statusMsg = (TextView) convertView
                .findViewById(com.example.android.uvdemo.R.id.txtStatusMsg);

        AQXItem item = aqxItems.get(position);

        name.setText("測站 : " + item.getSiteName() + "\n" + "縣市 : " + item.getCounty());

        timestamp.setText("發布時間 : " + item.getPublishTime());
        statusMsg.setText("空氣污染指標 : " + item.getPSI()
                        + "\n" + "指標污染物 : " + item.getMajorPollutant()
                        + "\n" + "狀態 : " + item.getStatus()
                + "\n" + "二氧化硫濃度 : " + item.getSO2()
                + "\n" + "一氧化碳濃度 : " + item.getCO()
                + "\n" + "臭氧濃度 : " + item.getO3()
                + "\n" + "懸浮顆粒濃度 : " + item.getPM10()
                + "\n" + "細懸浮顆粒濃度 : " + item.getPM25()
                + "\n" + "二氧化氮濃度 : " + item.getNO2()
                + "\n" + "風速 : " + item.getWindSpeed()
                + "\n" + "風向 : " + item.getWindDirec()
                + "\n" + "細懸浮顆粒指標 : " + item.getFPMI()
                + "\n" + "氮氧化物 : " + item.getNOx()
                + "\n" + "一氧化氮 : " + item.getNO()
        );
        statusMsg.setVisibility(View.VISIBLE);

        return convertView;
    }
}
