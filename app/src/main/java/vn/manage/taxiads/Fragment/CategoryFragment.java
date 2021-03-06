package vn.manage.taxiads.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Objects;

import vn.manage.taxiads.R;

/**
 * Created by nhapt on 8/30/2016.
 * Hàm xử lý các danh mục.
 */
public class CategoryFragment extends ListFragment {

    int mCurCheckPosistion = -1;


    // Thực thi khi Activity TaxiAds khởi chạy.
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Tạo list category với danh sách lấy từ String.xml, layout default từ android library.
        ArrayAdapter<String> categoryListView = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                getResources().getStringArray(R.array.category_List)
        );

        setListAdapter(categoryListView);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        if(savedInstanceState != null){
            mCurCheckPosistion = savedInstanceState.getInt("curChoice", 0);
            showListPlaces(mCurCheckPosistion);
        } else {
            showListPlaces(0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosistion);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showListPlaces(position);
    }

    // Hàm hiện danh sách địa điểm.
    private void showListPlaces(int index) {

        if(mCurCheckPosistion != index) {
            // lấy tên của category.
            String catName = (String) getListAdapter().getItem(index);
            FragmentTransaction ft = getFragmentManager().beginTransaction();

            getListView().setItemChecked(index, true);
            clearBackStack();

            if (Objects.equals(catName, "Bản đồ")) {

                //MapsFragment nMap = MapsFragment.newInstance();
                //ft.replace(R.id.mainContent, nMap);

            } else {
                // Khai báo PlaceListFragment và gán vào vị trí selected (index) và tên Category (catName).
                PlacesListFragment placeId = PlacesListFragment.newInstance(index, catName);
                // Thay đổi fragment có ID là mainContent thành danh sách địa điểm fragment.
                ft.replace(R.id.mainContent, placeId);
            }

            // Hiệu ứng thay đổi fragment là Fade.
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            // Thực thi việc chuyển đổi fragment.
            ft.commit();

            mCurCheckPosistion = index;

        } else {
            Log.d("Debug: ", "Same item onclick -> Inorge");
        }
    }

    // Clear Back Stack.
    private void clearBackStack() {
        FragmentManager manager = getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
