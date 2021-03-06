package vn.manage.taxiads.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import vn.manage.taxiads.R;

/**
 * Created by nhapt on 9/5/2016.
 *
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;

    public static MapsFragment newInstance(LatLng ll){
        MapsFragment mm = new MapsFragment();
        Bundle args = new Bundle();

        args.putDouble("lat", ll.latitude);
        args.putDouble("long",ll.longitude);

        mm.setArguments(args);
        return mm;
    }

    private double getLat(){
        return getArguments().getDouble("lat");
    }

    private double getLng(){
        return getArguments().getDouble("long");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFrag);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng ll = new LatLng(getLat(), getLng());
        Log.d("Latitude vars: ", String.valueOf(getLat()));
        Log.d("Longtitue vars:", String.valueOf(getLng()));
        CameraUpdate cUp = CameraUpdateFactory.newLatLngZoom(ll, 14);
        mMap.animateCamera(cUp);
    }
}
