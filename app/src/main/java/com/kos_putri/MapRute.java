package com.kos_putri;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kos_putri.util.GpsUtil;
import com.kos_putri.util.RouteGenerator;

import java.util.ArrayList;

public class MapRute extends FragmentActivity implements OnMapReadyCallback, RoutingListener {

    private GoogleMap mMap;
    Bundle bundle;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private int locationRequestCode = 1000;
    private int id_penginapan;
    private String nama;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LatLng latLngBiru = new LatLng(Double.parseDouble("-8.669371"), Double.parseDouble("115.213271"));
    private boolean isContinue = false;
    private boolean isGPS = false;
    private LatLng llfrom, lldest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_rute);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bundle = this.getIntent().getExtras();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(20 * 1000);
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds


        id_penginapan = bundle.getInt("id_penginapan", 0);
        nama = bundle.getString("nama");
        wayLatitude = bundle.getDouble("latitude");
        wayLongitude = bundle.getDouble("longitude");

        new GpsUtil(this).turnGPSOn(new GpsUtil.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPS = isGPSEnable;
            }
        });

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
//                        wayLatitude = location.getLatitude();
//                        wayLongitude = location.getLongitude();
                        llfrom = new LatLng(location.getLatitude(), location.getLongitude());
                        lldest = new LatLng(MapRute.this.wayLatitude, MapRute.this.wayLongitude);

                        mMap.addMarker(new MarkerOptions()
                                .position(llfrom)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                                .title("Lokasi Anda"));

                        Marker destMarker = mMap.addMarker(new MarkerOptions()
                                .position(lldest)
                                .title("Penginapan " + MapRute.this.nama)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
                        );

//                        Routing routing = new Routing.Builder()
//                                .travelMode(AbstractRouting.TravelMode.DRIVING)
//                                .withListener(MapRute.this)
//                                .alternativeRoutes(true)
//                                .waypoints(llfrom, lldest)
//                                .build();
//                        routing.execute();

                        RouteGenerator RouteGen;
                        RouteGen = new RouteGenerator(llfrom, lldest, MapRute.this.mMap, getString(R.string.google_maps_key));
                        RouteGen.Generate();

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(lldest));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(llfrom, 15));
                        if (!isContinue && mFusedLocationClient != null) {
                            mFusedLocationClient.removeLocationUpdates(locationCallback);
                        }
                    }
                }
            }
        };

        isContinue = true;
        getLocation();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},1000);

        } else {
            if (isContinue) {
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            } else {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(MapRute.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location !=null){
                            llfrom = new LatLng(location.getLatitude(),location.getLongitude());
//                            wayLatitude = location.getLatitude();
//                            wayLongitude = location.getLongitude();
                        }else{
                            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                        }
                    }
                });
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CameraUpdate point = CameraUpdateFactory.newLatLngZoom(latLngBiru, 17);
        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
        lldest = new LatLng(MapRute.this.wayLatitude, MapRute.this.wayLongitude);
//        mMap.addMarker(new MarkerOptions()
//                .position(llfrom)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
//                .title("Lokasi Anda"));
//
//        Marker destMarker = mMap.addMarker(new MarkerOptions()
//                .position(lldest)
//                .title("Penginapan " + MapRute.this.nama)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
//        );
//
//        RouteGenerator RouteGen;
//        RouteGen = new RouteGenerator(llfrom, lldest, MapRute.this.mMap);
//        RouteGen.Generate();

        mMap.moveCamera(point);
        mMap.animateCamera(point);
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        Log.d("Route",e.getMessage());
        e.printStackTrace();
    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(latLngBiru);
        mMap.moveCamera(center);

    }

    @Override
    public void onRoutingCancelled() {

    }
}
