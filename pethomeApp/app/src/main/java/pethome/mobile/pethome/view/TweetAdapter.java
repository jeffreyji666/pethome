package pethome.mobile.pethome.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

import pethome.mobile.pethome.R;
import pethome.mobile.pethome.model.Tweet;

/**
 * Created by you on 10/22/14.
 */
public class TweetAdapter extends BaseAdapter {
    List<Tweet> tweets;
    LayoutInflater inflater;

    public TweetAdapter(Context context, List<Tweet> tweets) {
        this.tweets = tweets;
        this.inflater = LayoutInflater.from(context);
    }

    public void onDateChange(List<Tweet> tweets) {
        this.tweets = tweets;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tweets.size();
    }

    @Override
    public Object getItem(int position) {
        return tweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet tweet = tweets.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.dog_item, null);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.nickName = (TextView) convertView.findViewById(R.id.nickName);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.votingComment = (TextView) convertView.findViewById(R.id.votingComment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Ion.with(holder.avatar).placeholder(R.drawable.smile).load(tweet.getAvatar());
        holder.nickName.setText(tweet.getNickName());
        holder.text.setText(tweet.getText());
        Ion.with(holder.image).placeholder(R.drawable.smile).load(tweet.getImage());
        holder.votingComment.setText(tweet.getVotingUp() + "赞," + tweet.getVotingDown() + "鄙视," + tweet.getComments() + "评论");
        return convertView;
    }

    class ViewHolder {
        ImageView avatar;
        TextView nickName;
        TextView text;
        ImageView image;
        TextView votingComment;
    }
}
