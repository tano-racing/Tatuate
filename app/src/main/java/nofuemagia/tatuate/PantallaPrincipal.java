package nofuemagia.tatuate;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.ArrayList;

import nofuemagia.tatuate.model.Locales;


public class PantallaPrincipal extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        Iconify.with(new FontAwesomeModule());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setUpMap();
    }

    private void setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location myLocation = locationManager.getLastKnownLocation(provider);

        if (myLocation == null) {
            myLocation = new Location("Predeterminada");
            myLocation.setLatitude(-34.603684);
            myLocation.setLongitude(-58.381559);
        }

        double latitude = myLocation.getLatitude();
        double longitude = myLocation.getLongitude();

        ArrayList<Locales> locales = (ArrayList<Locales>) Locales.getLocales();

        LatLng latLng = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        mMap.setInfoWindowAdapter(new CustomInfoMapa(PantallaPrincipal.this, locales));

        for (Locales local : locales) {
            MarkerOptions options = new MarkerOptions()
                    .position(new LatLng(local.getLatitud(), local.getLongitud()))
                    .title(local.getNombre())
                    .snippet(local.getId().toString());
            //.icon(getCustomMarker(PantallaPrincipal.this, FontAwesomeIcons.fa_edit, 0.5, R.color.colorAccent));

            mMap.addMarker(options);
        }
    }

    public static BitmapDescriptor getCustomMarker(Context context, Icon icon, double scale, int color) {
        IconDrawable id = new IconDrawable(context, icon).actionBarSize();
        id.colorRes(color);
        Drawable d = id.getCurrent();
        Bitmap bm = Bitmap.createBitmap(id.getIntrinsicWidth(), id.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        if (scale != 1)
            bm = Bitmap.createScaledBitmap(bm, id.getIntrinsicWidth(), id.getIntrinsicHeight(), false);
        Canvas c = new Canvas(bm);
        d.draw(c);
        return BitmapDescriptorFactory.fromBitmap(bm);
    }
}
