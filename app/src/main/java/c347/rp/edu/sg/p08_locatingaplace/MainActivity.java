package c347.rp.edu.sg.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnNorth, btnCenter, btnEast;
    Spinner spLoc;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        final SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                map = googleMap;

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                LatLng poi_SG = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG, 11));

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                LatLng poi_North = new LatLng(1.461708, 103.813500);
                final Marker north = map.addMarker(new MarkerOptions()
                        .position(poi_North)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654\nOperating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.btn_star_big_on)));

                LatLng poi_Center = new LatLng(1.300542, 103.841226);
                final Marker center = map.addMarker(new MarkerOptions()
                        .position(poi_Center)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_East = new LatLng(1.350057, 103.934452);
                Marker east = map.addMarker(new MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        String msg = "";
                        if(marker.equals(north)){
                            msg = "North - HQ";
                        }else if (marker.equals(center)){
                            msg = "Central";
                        }else{
                            msg = "East";
                        }

                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                        return false;

                    }
                });
            }
        });


        btnNorth = findViewById(R.id.btnNorth);
        btnCenter = findViewById(R.id.btnCenter);
        btnEast = findViewById(R.id.btnEast);

        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        map = googleMap;

                        LatLng poi_North = new LatLng(1.461708, 103.813500);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North, 15));
                    }
                });
            }
        });

        btnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        map = googleMap;

                        LatLng poi_Center = new LatLng(1.300542, 103.841226);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Center, 15));
                    }
                });
            }
        });

        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        map = googleMap;

                        LatLng poi_East = new LatLng(1.350057, 103.934452);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East, 15));
                    }
                });
            }
        });

        spLoc = findViewById(R.id.spLoc);
        spLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1){
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map = googleMap;

                            LatLng poi_North = new LatLng(1.461708, 103.813500);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North, 15));
                        }
                    });
                }else if(position == 2){
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map = googleMap;

                            LatLng poi_Center = new LatLng(1.300542, 103.841226);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Center, 15));
                        }
                    });
                }else if (position == 3){
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map = googleMap;

                            LatLng poi_East = new LatLng(1.350057, 103.934452);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East, 15));
                        }
                    });
                }else{
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map = googleMap;

                            LatLng poi_SG = new LatLng(1.3521, 103.8198);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG, 11));

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }
}
