package vn.manage.taxiads.modules;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nhapt on 8/28/2016.
 * Class lấy địa diểm gần nhất.
 */
public class getNearPlace extends AsyncTask<String, Integer, ArrayList<HashMap<String, String>>> {

    public AsyncRespone responed;

    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(String... strings) {
        if(strings != null){
            try {
                return new WebRequest().parseLocationList(strings[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String>> result) {

        if(result != null) {
            ArrayList<MyPlace> locList = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                HashMap<String, String> temp = result.get(i);
                Integer placeid = Integer.parseInt(temp.get("id"));
                LatLng pll = new LatLng(
                        Double.parseDouble(temp.get("lat")),
                        Double.parseDouble(temp.get("lng")));
                String pName = temp.get("name");
                String pAddr = temp.get("address");

                locList.add(new MyPlace(placeid, pName, pAddr, pll));
            }

            responed.processFinish(locList);

        } else {
            Log.d("Error: ", "Result is empty");
        }
    }
}
