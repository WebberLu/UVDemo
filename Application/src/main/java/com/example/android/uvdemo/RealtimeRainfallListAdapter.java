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
 * Created by user on 2016/3/16.
 */
public class RealtimeRainfallListAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<RealtimeRainfallItem> mRealtimeRainfallItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public RealtimeRainfallListAdapter(Activity activity, List<RealtimeRainfallItem> RealtimeRainfallItems) {
        this.activity = activity;
        this.mRealtimeRainfallItems = RealtimeRainfallItems;
    }

    @Override
    public int getCount() {
        return mRealtimeRainfallItems.size();
    }

    @Override
    public Object getItem(int location) {
        return mRealtimeRainfallItems.get(location);
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

        RealtimeRainfallItem item = mRealtimeRainfallItems.get(position);

        name.setText("測站代碼 : " + item.getSiteId() + "\n" + "測站名稱 : " + item.getSiteName()
                +"\n"+"縣市 : "+item.getCounty()+"\n"+"鄉鎮 : "+item.getTownship());
        timestamp.setText("發布時間 : "+item.getPublishTime());
        statusMsg.setText("TWD67經度 : "+item.getTWD67Lat()
                +"\n"+"TWD67緯度 : "+item.getTWD67Lon()
                +"\n"+"10分鐘累積雨量 : "+item.getRainfall10min()
                +"\n"+"1小時累積雨量 : "+item.getRainfall1hr()
                +"\n"+"3小時累積雨量 : "+item.getRainfall3hr()
                +"\n"+"6小時累積雨量 : "+item.getRainfall6hr()
                +"\n"+"12小時累積雨量 : "+item.getRainfall12hr()
                +"\n"+"24小時累積雨量 : "+item.getRainfall24hr()
                +"\n"+"日累積雨量 : "+item.getNow()
                +"\n"+"設置單位 : "+item.getUnit());

        return convertView;
    }
}
