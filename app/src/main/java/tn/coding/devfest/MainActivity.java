package tn.coding.devfest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.renderscript.Double2;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private LocationManager locationManager;
    Double lat,lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET
                }, 10);
            }
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,2000, 0, this);
        /* houni heki el 2000 = 2s ya3ni yab9a kol mara yaamel f request de notif a chaque changeent d'emplacement kol 2 second
        w 0 c'est la distance minimale donc tnajem tbaddelha kima yse3dek ;)
        w NETWORK_PROVIDER héki maanéha yjib fel localisation mel internet mch mel gps donc tnajjem tbaddalha GPS_PRovider
        mais nans7ek khalliha Network khater asra3







         */

    }

    @Override
    public void onLocationChanged(Location location) {
    /*houni lmethode li fi kol changement mte3 el lattitude w longi hya bch ysir fiha traitement li houa
    logiquement bch ykoun anna n7ottouhom fel base
        */
        lat=location.getLatitude();
        Log.e("MyLat",Double.toString(lat));
        lng=location.getLongitude();
        Log.e("Mylongitude",Double.toString(lng));
        /* houni c'est bon khdhina el lat w longitude
        donc just taamel el requete li bch tarmiha fel base
         */
        Toast.makeText(this,Double.toString(lng),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();

    }
}
