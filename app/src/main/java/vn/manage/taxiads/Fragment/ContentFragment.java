package vn.manage.taxiads.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// import FloatButton
import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import vn.manage.taxiads.R;

/**
 * Created by nhapt on 8/30/2016.
 *
 */
public class ContentFragment extends Fragment implements View.OnClickListener {

    // Khai báo Content Fragment có chứa vị trí nút nhấn (index), địa điểm ID (placeID),
    // tên địa điểm (pName), địa chỉ điểm đến (pAddr), tham số Lat, Lng (ll).
    public static ContentFragment newInstance(int index, int placeID, String pName, String pAddr, LatLng ll) {
        ContentFragment cf = new ContentFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putInt("catID", placeID);
        args.putString("pName", pName);
        args.putString("pAddr", pAddr);
        args.putDouble("lat", ll.latitude);
        args.putDouble("long",ll.longitude);

        cf.setArguments(args);

        return cf;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    public int getCatID() {
        return getArguments().getInt("placeID");
    }

    public String getPlaceName() {
        return getArguments().getString("pName");
    }

    public String getPlaceAddr() {
        return getArguments().getString("pAddr");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Khai báo Content Fragment sử dung layout content_detail.xml
        View view = inflater.inflate(R.layout.content_detail, container, false);

        // Set tên, địa chỉ của địa điểm vào ID contentName, contentAddress bên trong content_detail.xml
        TextView pName = (TextView) view.findViewById(R.id.contentName);
        TextView pAddr = (TextView) view.findViewById(R.id.contentAddress);

        // Set detail text.
        TextView pDetail = (TextView) view.findViewById(R.id.contentDetails);

        pName.setText(getPlaceName());
        pAddr.setText(getPlaceAddr());
        pDetail.setText(String.valueOf(getArguments().getDouble("lat")));

        //click FloatButton
        FloatingActionButton fb = (FloatingActionButton) view.findViewById(R.id.fab);
        fb.setOnClickListener(this);
        return view;
    }

    //action click FloatButton
    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(),
        getPlaceName(), Toast.LENGTH_LONG).show();

        LatLng ll = new LatLng(getArguments().getDouble("lat"), getArguments().getDouble("long"));

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        MapsFragment nMap = MapsFragment.newInstance(ll);
        ft.replace(R.id.mainContent, nMap);
        ft.commit();
    }

}
