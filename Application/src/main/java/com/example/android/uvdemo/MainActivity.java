/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


package com.example.android.uvdemo;

import android.app.ActionBar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.common.activities.SampleActivityBase;
import com.example.android.common.logger.Log;
import com.example.android.common.logger.LogFragment;
import com.example.android.common.logger.LogWrapper;
import com.example.android.common.logger.MessageOnlyLogFilter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link android.support.v4.app.Fragment} which can display a view.
 * <p/>
 * For devices with displays with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */
public class MainActivity extends SampleActivityBase implements LocationListener {

    public static final String TAG = "MainActivity";

    // Whether the Log Fragment is currently shown
    private ViewPager mViewPager;
    private MyFragmentPageAdapter mAdapter;
    private ActionBar mAb;
    private double mLat = 0, mLng = 0;
    private LocationManager mLocMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.android.uvdemo.R.layout.activity_main);

//        if (savedInstanceState == null) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            UVFragment fragment = new UVFragment();
//            transaction.replace(com.example.android.uvdemo.R.id.sample_content_fragment, fragment);
//            transaction.commit();
//        }
        mAb = getActionBar();
        mAb.setTitle("紫外線指數");
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentManager fm = getSupportFragmentManager();
        mAdapter = new MyFragmentPageAdapter(fm);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);
        mViewPager.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        if (initLocationProvider()) {
            mLocMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 100, this);
            Location location = this.mLocMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null)
                this.onLocationChanged(location);
        }
        getAddressThread();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mLocMgr != null) mLocMgr.removeUpdates(this);
        super.onDestroy();
    }

    private void getAddressThread() {
        getAddressTask task = new getAddressTask();
        task.execute(null, null, null);
    }

    private class getAddressTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected String doInBackground(String... params) {
            if (mLat > 0 && mLng > 0) {
                getAddress(mLat, mLng);
            }
            return null;
        }

    }

    private boolean initLocationProvider() {
        mLocMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (mLocMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return true;
        }
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        mLng = location.getLongitude();
        mLat = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public class MyFragmentPageAdapter extends FragmentPagerAdapter {


        public MyFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new UVFragment();
                case 1:
                    return new AQXFragment();
                case 2:
                    return new RealtimeRainfallFragment();
                default:
                    return null;
            }
        }
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mAb.setTitle("紫外線指數");
                    break;
                case 1:
                    mAb.setTitle("空氣品質");
                    break;
                case 2:
                    mAb.setTitle("十分鐘雨量資料");
                    break;
                default:

            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);
//        logToggle.setVisible(findViewById(R.id.sample_output) instanceof ViewAnimator);
//        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.menu_toggle_log:
//                mLogShown = !mLogShown;
//                ViewAnimator output = (ViewAnimator) findViewById(R.id.sample_output);
//                if (mLogShown) {
//                    output.setDisplayedChild(1);
//                } else {
//                    output.setDisplayedChild(0);
//                }
//                supportInvalidateOptionsMenu();
//                return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Create a chain of targets that will receive log data
     */
    @Override
    public void initializeLogging() {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(com.example.android.uvdemo.R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());

        Log.i(TAG, "Ready");
    }

    private void getAddress(Double lat, Double lng) {
        Geocoder gc = new Geocoder(MainActivity.this, Locale.TRADITIONAL_CHINESE);
        List<Address> lstAddress;
        String countryname = "", adminarea = "", locality = "", postalcode = "";
        try {
            lstAddress = gc.getFromLocation(lat, lng, 1);
            countryname = lstAddress.get(0).getCountryName();
            adminarea = lstAddress.get(0).getAdminArea();// City
            locality = lstAddress.get(0).getLocality();// District
            postalcode = lstAddress.get(0).getPostalCode();
            Log.v(TAG, "=========================================");
            Log.v(TAG, "getAddress : " + "\nCountry : " + countryname + "\n City : " + adminarea +
                    "\n District : " + locality
                    + "\n PostalCode : " + postalcode + "\n LatLng : " + lat + " , " + lng);
            Log.v(TAG, "=========================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
