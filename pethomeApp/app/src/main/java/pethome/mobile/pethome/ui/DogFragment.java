package pethome.mobile.pethome.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pethome.mobile.pethome.R;
import pethome.mobile.pethome.model.Tweet;
import pethome.mobile.pethome.model.TweetModel;
import pethome.mobile.pethome.view.LoadListView;
import pethome.mobile.pethome.view.TweetAdapter;

/**
 * Created by you on 10/23/14.
 */
public class DogFragment extends Fragment implements LoadListView.ILoadListener {
    private List<Tweet> tweets;

    TweetAdapter adapter;
    LoadListView listview;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dog_layout, container, false);

        tweets = TweetModel.getMessages();
        showListView(mView, tweets);
        return mView;
    }

    private void showListView(View mView, List<Tweet> tweets) {
        if (adapter == null) {
            listview = (LoadListView) mView.findViewById(R.id.listview);
            listview.setInterface(this);
            adapter = new TweetAdapter(this.getActivity(), tweets);
            listview.setAdapter(adapter);
        } else {
            adapter.onDateChange(tweets);
        }
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showListView(mView, tweets);
                listview.loadComplete();
            }
        }, 2000);
    }
}
