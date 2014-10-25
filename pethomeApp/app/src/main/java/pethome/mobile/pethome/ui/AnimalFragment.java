package pethome.mobile.pethome.ui;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.List;

import pethome.mobile.pethome.R;
import pethome.mobile.pethome.model.Tweet;
import pethome.mobile.pethome.view.LoadListView;
import pethome.mobile.pethome.view.TweetAdapter;

/**
 * Created by you on 10/25/14.
 */
public class AnimalFragment extends Fragment implements LoadListView.ILoadListener {
    protected List<Tweet> tweets;

    protected TweetAdapter adapter;
    protected LoadListView listview;
    protected View mView;

    protected void showListView(View mView, List<Tweet> tweets) {
        if (adapter == null) {
            listview = (LoadListView) mView.findViewById(R.id.tweets);
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
