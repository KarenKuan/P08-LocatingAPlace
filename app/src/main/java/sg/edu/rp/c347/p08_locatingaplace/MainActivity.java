package sg.edu.rp.c347.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    Button btnnormal, btnterrain, btnsatelite;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                final LatLng north = new LatLng(1.461708, 103.813500);

                final LatLng central = new LatLng(1.300542, 103.841226);

                final LatLng east = new LatLng(1.350057, 103.934452);

                final LatLng singapore = new LatLng(1.3521, 103.8198);

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);

                UiSettings ui2 = map.getUiSettings();
                ui2.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                final Marker n = map.addMarker(new
                        MarkerOptions()
                        .position(north)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                final Marker c = map.addMarker(new
                        MarkerOptions()
                        .position(central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                Marker e = map.addMarker(new
                        MarkerOptions()
                        .position(east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.equals(n)) {
                            Toast.makeText(MainActivity.this, "North - HQ", Toast.LENGTH_SHORT).show();
                        } else if (marker.equals(c)) {
                            Toast.makeText(MainActivity.this, "Central", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "East", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(north, 15));
            }
        });

        btnnormal = findViewById(R.id.btnnormal);
        btnterrain = findViewById(R.id.btnterrain);
        btnsatelite = findViewById(R.id.btnsatelite);

        btnnormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });

        btnterrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map !=null) {
                    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }
        });

        btnsatelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
    }
}
