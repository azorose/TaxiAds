package vn.manage.taxiads;

//import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlacesListFragment placesList = PlacesListFragment.newInstance(0, "Hot");
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.add(R.id.mainContent, placesList);

    }

}
