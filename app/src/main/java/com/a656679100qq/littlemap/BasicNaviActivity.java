package com.a656679100qq.littlemap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amap.api.maps.MapView;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;

public class BasicNaviActivity extends AppCompatActivity {

    AMapNaviView navimap;
    //private MapView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_navi);
        navimap = (AMapNaviView) findViewById(R.id.navi_view);
        navimap.onCreate(savedInstanceState);
        /*navimap.setAMapNaviVie147wListener(BasicNaviActivity.this);*/
    }

}
