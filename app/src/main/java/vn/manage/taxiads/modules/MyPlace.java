package vn.manage.taxiads.modules;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by nhapt on 8/30/2016.
 * Class khai báo về thông tin vị trí. Bao gồm
 * Tên địa diểm -> title.
 * Địa chỉ của địa điểm -> address.
 */
public class MyPlace {

    private int placeID;
    private String title;
    private String address;
    private LatLng llPlace;

    public MyPlace(int placeID, String title, String address, LatLng ll){
        this.placeID = placeID;
        this.title = title;
        this.address = address;
        this.llPlace = ll;
    }

    public LatLng getLatLng(){
        return llPlace;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public int getPlaceID() {
        return placeID;
    }
}
