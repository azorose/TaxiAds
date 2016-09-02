package vn.manage.taxiads;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by nhapt on 8/30/2016.
 *
 */
public class ContentFragment extends Fragment {

    public static ContentFragment newInstance(int index, int catID, String pName, String pAddr){
        ContentFragment cf = new ContentFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putInt("catID", catID);
        args.putString("pName", pName);
        args.putString("pAddr", pAddr);

        cf.setArguments(args);

        return cf;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }
    public int getCatID() { return getArguments().getInt("catID"); };
    public String getPlaceName() { return getArguments().getString("pName"); }
    public String getPlaceAddr() { return getArguments().getString("pAddr"); }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.content_detail, container, false);

        TextView pName = (TextView) v.findViewById(R.id.contentName);
        TextView pAddr = (TextView) v.findViewById(R.id.contentAddress);
        TextView pDetail = (TextView) v.findViewById(R.id.contentDetails);

        pName.setText(getPlaceName());
        pAddr.setText(getPlaceAddr());
        pDetail.setText("Testing");

        return v;
    }
}
