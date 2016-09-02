package vn.manage.taxiads.modules;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by nhapt on 8/28/2016.
 * Class tìm đường đi từ A sang B.
 */
public class getDierctionPlace extends AsyncTask<String, String, List<LatLng>> {

    public AsyncRespone responed = null;

    @Override
    protected List<LatLng> doInBackground(String... strings) {
        if(strings != null){
            return new WebRequest().parseDirectionList(strings[0]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<LatLng> result){
        if(result != null){
            Log.d("Debug: ", "Start set polyline");
            //responed.processDirection(result);
        } else {
            Log.d("Error: ", "result is null");
        }

    }
}
