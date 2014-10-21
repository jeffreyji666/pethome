package pethome.mobile.pethome.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pethome.mobile.pethome.R;
import pethome.mobile.pethome.model.Comment;
import pethome.mobile.pethome.model.Message;
import pethome.mobile.pethome.model.MessageModel;
import pethome.mobile.pethome.view.CommentAdapter;
import pethome.mobile.pethome.view.LoadListView;
import pethome.mobile.pethome.view.MessageAdapter;

/**
 * Created by you on 10/22/14.
 */
public class AnimalFragment extends Fragment implements LoadListView.ILoadListener {
    private List<Message> messages;

    MessageAdapter adapter;
    LoadListView listview;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.message, container, false);
        messages = MessageModel.getMessages();
        showListView(mView, messages);
        return mView;

    }

    private void showListView(View mView, List<Message> messages) {
        if (adapter == null) {
            listview = (LoadListView) mView.findViewById(R.id.listview);
            listview.setInterface(this);
            adapter = new MessageAdapter(this.getActivity(), messages);
            listview.setAdapter(adapter);
        } else {
            adapter.onDateChange(messages);
        }
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showListView(mView, messages);
                listview.loadComplete();
            }
        }, 2000);
    }
}
