package vn.manage.taxiads.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import vn.manage.taxiads.modules.PlaceAdapter;
import vn.manage.taxiads.R;
import vn.manage.taxiads.modules.AsyncRespone;
import vn.manage.taxiads.modules.MyPlace;
import vn.manage.taxiads.modules.getNearPlace;

/**
 * Created by nhapt on 8/30/2016.
 *
 */
public class PlacesListFragment extends ListFragment implements AsyncRespone {

    getNearPlace temp = new getNearPlace();
    ArrayList<MyPlace> onCheckPlace;

    public static PlacesListFragment newInstance(int index, String catName){
        PlacesListFragment cf = new PlacesListFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putString("catName", catName);

        cf.setArguments(args);
        return cf;
    }

    public String getShownString() {
        // Trả về tên của category.
        return getArguments().getString("catName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.place_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(onCheckPlace != null) {
            setArrayPlaceAdapter();
            return;
        }

        String cat = getShownString();
        String urlAddr;
        temp.responed = this;

        switch (cat) {

            case "Hot":
                urlAddr = getString(R.string.get_place_url) + "?cat=all&hot=1";

                temp.execute(urlAddr);
                break;

            case "Bản đồ":
                break;

            default:
                String newcat = cat.replace(" ", "%20");
                urlAddr = getString(R.string.get_place_url) + "?cat=" + newcat;

                temp.execute(urlAddr);
                break;
        }
    }

    @Override
    public void processFinish(ArrayList<MyPlace> locationList) {

        onCheckPlace = locationList;
        setArrayPlaceAdapter();
    }

    private void setArrayPlaceAdapter() {
        ArrayAdapter<MyPlace> places = new PlaceAdapter(getActivity(), R.layout.sumary_info, onCheckPlace);
        setListAdapter(places);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        MyPlace myPlace = (MyPlace) getListAdapter().getItem(position);

        ContentFragment content = ContentFragment.newInstance(position, myPlace.getPlaceID(), myPlace.getTitle(), myPlace.getAddress(), myPlace.getLatLng());
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.mainContent, content, "Detail Fragment");

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

}
