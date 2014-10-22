package pethome.mobile.pethome.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pethome.mobile.pethome.R;

/**
 * Created by you on 10/19/14.
 */
public class CatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.cat_layout, container, false);
        return mView;
    }
}
