package vn.manage.taxiads.modules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.manage.taxiads.R;
import vn.manage.taxiads.modules.MyPlace;

/**
 * Created by nhapt on 8/30/2016.
 *
 */
public class PlaceAdapter extends ArrayAdapter<MyPlace> {

    ArrayList<MyPlace> locs = new ArrayList<>();

    public PlaceAdapter(Context context, int textViewResourceId, ArrayList<MyPlace> objects) {
        super(context, textViewResourceId, objects);
        locs = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater ifl = (LayoutInflater) getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = ifl.inflate(R.layout.sumary_info, null);
        TextView pName = (TextView) convertView.findViewById(R.id.placeName);
        TextView pAddr = (TextView) convertView.findViewById(R.id.placeAddr);

        pName.setText(locs.get(position).getTitle());
        pAddr.setText(locs.get(position).getAddress());

        return convertView;
    }
}
