package pethome.mobile.pethome.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pethome.mobile.pethome.R;
import pethome.mobile.pethome.model.TweetModel;

/**
 * Created by you on 10/23/14.
 */
public class DogFragment extends AnimalFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dog_layout, container, false);

        tweets = TweetModel.getMessages();
        showListView(mView, tweets);
        return mView;
    }

}
