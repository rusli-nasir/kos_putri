package com.kos_putri;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kos_putri.map.PenginapanInfoWindowGoogleMap;
import com.kos_putri.model.PenginapanModel;
import com.kos_putri.util.InputFilterMinMax;
import com.kos_putri.util.JsonParseVolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MapRadius extends FragmentActivity implements
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMyLocationChangeListener {

    private GoogleMap map;
    private Circle circle;
    private Marker myMarker;

    private LatLng latLngBiru = new LatLng(Double.parseDouble("-8.669371"), Double.parseDouble("115.213271"));
    List<PenginapanModel> penginapanModels;
    PenginapanModel penginapanModel;
    EditText txtZoomLvl, txtRadius;
    JSONArray lokasi_json = null;
    int intRadius, intZoom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_radius);
        txtZoomLvl = (EditText) findViewById(R.id.txtZoomLvl);
        txtRadius = (EditText) findViewById(R.id.txtRadius);
        txtZoomLvl.setFilters(new InputFilter[]{new InputFilterMinMax("1", "20")});
        txtRadius.setFilters(new InputFilter[]{new InputFilterMinMax("1", "20")});

        penginapanModels = new ArrayList<PenginapanModel>();
        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFrag != null) {
            mapFrag.getMapAsync(this);
        }

        txtRadius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    LoadMapMarker(latLngBiru);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtZoomLvl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    LoadMapMarker(latLngBiru);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if(checkPermissions()) {
            float zoomLvl = Float.valueOf(txtZoomLvl.getText().toString());
            CameraUpdate point = CameraUpdateFactory.newLatLngZoom(latLngBiru,zoomLvl);
            Log.d("point", latLngBiru.toString());
            map.moveCamera(point);
            map.animateCamera(point);
            map.setMyLocationEnabled(true);
            map.setOnMyLocationChangeListener(this);

            map.setOnCameraMoveCanceledListener(new GoogleMap.OnCameraMoveCanceledListener() {
                @Override
                public void onCameraMoveCanceled() {
                    int zoomPos = (int) map.getCameraPosition().zoom;
                    int inputZoomPos = Integer.valueOf(txtZoomLvl.getText().toString());
                    if(zoomPos != inputZoomPos){
                        txtZoomLvl.setText(String.valueOf(zoomPos));
                    }
                    LoadMapMarker(latLngBiru);
                }
            });

            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    LoadMapMarker(latLng);
                }
            });

            LoadMapMarker(latLngBiru);
        }
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }else{
            requestPermissions();
            return false;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
    }

    @Override
    public void onMyLocationChange(Location location) {

    }

    public void LoadMapMarker(LatLng latLng){
        if(map !=null){
            map.clear();
        }
        intRadius = !txtRadius.getText().toString().equals("") ?Integer.parseInt(txtRadius.getText().toString()):1;
        intZoom = !txtZoomLvl.getText().toString().equals("")?Integer.parseInt(txtZoomLvl.getText().toString()):0;

        LatLng loc = new LatLng(latLng.latitude, latLng.longitude);

        String link_url = getString(R.string.server_url)+ "penginapan_terdekat.php?"+
                "lat=" + latLng.latitude +
                "&long=" + latLng.longitude +
                "&rad=" + intRadius;
        Log.d("url", link_url);
        JsonParseVolley jsonParse = new JsonParseVolley(this.getApplicationContext());
        JSONObject jsonObject;
        jsonObject =  jsonParse.getJsonURL(link_url);
        Log.d("jsonObject", String.valueOf(jsonObject));
        map.addMarker(new MarkerOptions()
                .position(loc)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                .title("Lokasi Anda"));

        if(jsonObject !=null){
            int id_penginapan, id_kategori_penginapan;
            double latitude, longitude, distance;
            float harga;
            String nama_kategori, nama, fasilitas, status, alamat, telepon, gambar;
            try {
                lokasi_json = jsonObject.getJSONArray("lokasi");
                if(lokasi_json != null){
                    for (int i = 0; i < lokasi_json.length(); i++) {
                        JSONObject ar = lokasi_json.getJSONObject(i);
                        gambar = null;
                        try {
                            gambar = getString(R.string.image_server_url) + URLEncoder.encode(ar.getString("gambar"),"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        penginapanModel = new PenginapanModel(
                                ar.getInt("id_penginapan"),
                                ar.getInt("id_kategori_penginapan"),
                                ar.getString("nama_kategori"),
                                0,"",
                                ar.getString("nama"),
                                ar.getString("fasilitas"),
                                ar.getString("telepon"),
                                ar.getString("status"),
                                ar.getString("alamat"),
                                ar.getDouble("latitude"),
                                ar.getDouble("longitude"),
                                ar.getString("telepon"),
                                gambar
                        );

//                        id_penginapan = ar.getInt("id_penginapan");
//                        id_kategori_penginapan = ar.getInt("id_kategori_penginapan");
//                        nama_kategori = ar.getString("nama_kategori");
//                        nama = ar.getString("nama");
//                        fasilitas = ar.getString("fasilitas");
//                        status = ar.getString("status");
//                        alamat = ar.getString("alamat");
//                        telepon = ar.getString("telepon");
//
//
//                        latitude = ar.getDouble("latitude");
//                        longitude = ar.getDouble("longitude");
                        LatLng snowqualmie = new LatLng(penginapanModel.getLatitude(), penginapanModel.getLongitude());
                        MarkerOptions markerOptions = new MarkerOptions();

                        markerOptions.position(snowqualmie)
                                .title(penginapanModel.getNama())
                                .snippet(penginapanModel.getAlamat())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));

                        PenginapanInfoWindowGoogleMap infoWindow = new PenginapanInfoWindowGoogleMap(this);
                        map.setInfoWindowAdapter(infoWindow);

                        Marker m = map.addMarker(markerOptions);
                        m.setTag(penginapanModel);

//                        myMarker = map.addMarker(markerOptions);

                    }
                    jsonParse.stopRequest("VolleyBlockingRequestActivity");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        if(map != null){
            int rad=intRadius * 1000;
            Log.d("radius", String.valueOf(rad));
//        	while(rad<=500)
//        	{
            CircleOptions circleOptions = new CircleOptions()
                    .center(loc)
                    .radius(rad)
                    .strokeWidth(1)
                    .fillColor(Color.TRANSPARENT)  //default
                    .strokeColor(Color.BLUE);
            // Supported formats are: #RRGGBB #AARRGGBB
            //   #AA is the alpha, or amount of transparency

            circle = map.addCircle(circleOptions);
//        		rad+=100;
//        	}

            if(intZoom >= 0){
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc,intZoom));
            }
            Log.d("client location", loc.toString());
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return false;
    }
}