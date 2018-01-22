package com.a656679100qq.littlemap;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;

import java.text.SimpleDateFormat;
import java.util.Date;



public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private AMap aMap;
    private AMapLocationClient locationClientSingle;
    AMapLocationListener locationSingleListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            updatePosition(location);
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map);
        // 必须回调MapView的onCreate()方法
        mapView.onCreate(savedInstanceState);
        //创建单次定位客户端：
        locationClientSingle = new AMapLocationClient(this.getApplicationContext());
        locationClientSingle.startLocation();
        final AMapLocationClientOption locationClientSingleOption = new AMapLocationClientOption();
        //声明定位回调监听器
        init();

        //图层切换实现
        final ToggleButton tb = (ToggleButton) findViewById(R.id.tb);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 设置使用卫星地图
                    tb.setBackgroundResource(R.drawable.satellite);
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                } else {
                    // 设置使用普通地图
                    tb.setBackgroundResource(R.drawable.map);
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                }
            }
        });

        //定位按钮监听
        final Button loca =(Button) findViewById(R.id.loca);
        loca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation=new AlphaAnimation(1.0f,0.0f);
                animation.setDuration(150);
                loca.startAnimation(animation);

                locationClientSingle.setLocationListener(locationSingleListener);
                //该方法默认为false。
                locationClientSingleOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//关闭缓存机制
                locationClientSingleOption.setOnceLocation(true);

                //locationClientSingleOption.setInterval(2000);
//给定位客户端对象设置定位参数
                locationClientSingle.setLocationOption(locationClientSingleOption);
//启动定位
                locationClientSingle.startLocation();
            }
        });

        //步行按钮监听
        final Button walk = (Button)findViewById(R.id.walk);
        walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation=new AlphaAnimation(1.0f,0.0f);
                animation.setDuration(150);
                walk.startAnimation(animation);
                Intent intent = new Intent(MainActivity.this,BasicNaviActivity.class);
                startActivity(intent);
            }
        });

        //驾车按钮监听
        final Button car =(Button)findViewById(R.id.car);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation=new AlphaAnimation(1.0f,0.0f);
                animation.setDuration(150);
                car.startAnimation(animation);
                Intent intent = new Intent(MainActivity.this,CustomCarActivity.class);
                startActivity(intent);
            }
        });

        //路线按钮监听
        final Button rout =(Button)findViewById(R.id.route);
        rout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation=new AlphaAnimation(1.0f,0.0f);
                animation.setDuration(150);
                rout.startAnimation(animation);
            }
        });


        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(new LatLng(39.955192,116.140092),new LatLng(39.900430,116.265061));
        polylineOptions.color(Color.BLUE);
        polylineOptions.setDottedLine(true);
        polylineOptions.width(10);
        aMap.addPolyline(polylineOptions);
    }

    // 初始化AMap对象
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 必须回调MapView的onResume()方法
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 必须回调MapView的onPause()方法
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 必须回调MapView的onSaveInstanceState()方法
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 必须回调MapView的onDestroy()方法
        mapView.onDestroy();
    }

    private void updatePosition(Location location){
        LatLng pos =new LatLng(location.getLatitude(),location.getLongitude());
        Toast.makeText(MainActivity.this,"Location Success!"+location.getLatitude()+" "+location.getLongitude() ,Toast.LENGTH_SHORT).show();
        CameraUpdate ca= CameraUpdateFactory.changeLatLng(pos);
        aMap.moveCamera(ca);
        aMap.clear();
        MarkerOptions markerOptions =new MarkerOptions();
        markerOptions.position(pos);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car));
        markerOptions.draggable(true);
        Marker marker=aMap.addMarker(markerOptions);
    }

}
