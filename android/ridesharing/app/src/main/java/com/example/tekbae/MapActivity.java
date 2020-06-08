package com.example.tekbae;


import android.app.Activity;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ZoomButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.Fragment;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationSource;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;
import com.naver.maps.map.widget.CompassView;
import com.naver.maps.map.widget.LocationButtonView;
import com.naver.maps.map.widget.ZoomControlView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private FragmentManager fm = getSupportFragmentManager();
    private MapFragment mapFragment;
    private LatLng coord;
    private NaverMap naverMap;
    private NaverMapOptions naverMapOptions;
    private CameraPosition cameraPosition;
    private ZoomControlView zoomControlView;
    private LocationButtonView locationButtonView;
    private CompassView compassView;
    private FusedLocationSource locationSource;
    private HashMap<Post,Marker> info=new HashMap<Post,Marker>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View v = View.inflate(getApplicationContext(),R.layout.post_info,null);
        mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        setContentView(R.layout.activity_map);
        coord=new LatLng(37,127);
        //declare variable
        final Geocoder geocoder=new Geocoder(this);
        naverMapOptions=new NaverMapOptions();
        locationSource=new FusedLocationSource(this,1000);
        cameraPosition=new CameraPosition(coord,16);
        String getList=LoginActivity.map.get("list");
        String[] arr=getList.split("/");
        for (int i=0;i<arr.length;i++) {
            String[] arr2 = arr[i].split(",");
            if(arr2.length>1)
                if(!arr2[2].equals("") &&arr2[2]!=null) {
                    Log.e("test",arr2[2]);
                    Marker marker=new Marker();
                    marker.setMap(naverMap);
                    marker.setPosition(getCoord(arr2[2], geocoder));
                    marker.setIcon(MarkerIcons.BLACK);
                    marker.setIconTintColor(Color.RED);
                    marker.setCaptionText("수령자 :"+arr2[0]+"\n 주소 : "+arr2[2]+"\n물건 :"+arr2[3]+"\n수령자 번호 : "+"0"+arr2[1]);
                    //"수령자 이름","수령자 번호", "수령자 주소","물건", "배송자 주소","배송자 번호",false,false,"날짜"
                    //this.ReceiverNumber+","+this.ReceiverAddress+this.Thing;
                    info.put((new Post(arr2[0], Integer.parseInt(arr2[1]), arr2[2], arr2[3], arr2[4], Integer.parseInt(arr2[5]), false, false, arr2[9])), marker);
                }
                else{
                    Log.e("test","address : "+arr2[2]);
                }
        }
        naverMapOptions.camera(cameraPosition);

        //setting map
        if (mapFragment == null) {
            naverMapOptions.locationButtonEnabled(true);
            naverMapOptions.zoomControlEnabled(true);
            naverMapOptions.zoomGesturesEnabled(true);
            naverMapOptions.compassEnabled(true);
            mapFragment = MapFragment.newInstance(naverMapOptions);
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        //setting Mapstart
        mapFragment.getMapAsync(naverMap-> {
            zoomControlView=findViewById(R.id.zoom_control);
            locationButtonView=findViewById(R.id.location_button);
            compassView=findViewById(R.id.compass);
            zoomControlView.setMap(naverMap);
            locationButtonView.setMap(naverMap);
            naverMap.setLocationSource(locationSource);
            compassView.setMap(naverMap);
            Iterator<Post> iter=info.keySet().iterator();
            while(iter.hasNext()){
                    info.get(iter.next()).setMap(naverMap);
            }
            this.naverMap=naverMap;
            Log.e("text","navermap2 : "+this.naverMap.toString());
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }
    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        if(naverMap==null)
            Log.e("text","naverMap==null");
        this.naverMap=naverMap;
        Log.e("text","navermap1 : "+this.naverMap.toString());
    }

    public LatLng getCoord(String address,Geocoder geocoder){
        List<Address> list=null;
        try {
            list=geocoder.getFromLocationName(address,10);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test","주소가 잘못 되었습니다.");
            return null;
        }
        Address addr=list.get(0);
        double lat=addr.getLatitude();
        double lng=addr.getLongitude();
        return new LatLng(lat,lng);
    }
}