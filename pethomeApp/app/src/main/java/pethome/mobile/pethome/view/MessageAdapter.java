package pethome.mobile.pethome.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pethome.mobile.pethome.R;
import pethome.mobile.pethome.model.Message;

/**
 * Created by you on 10/22/14.
 */
public class MessageAdapter extends BaseAdapter {
    List<Message> messages;
    LayoutInflater inflater;

    public MessageAdapter(Context context, List<Message> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }

    public void onDateChange(List<Message> messages) {
        this.messages = messages;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = messages.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.message, null);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.nickName = (TextView) convertView
                    .findViewById(R.id.nickName);
            holder.text = (TextView) convertView
                    .findViewById(R.id.text);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nickName.setText(message.getNickName());
        holder.text.setText(message.getText());
        return convertView;
    }

    class ViewHolder {
        ImageView avatar;
        TextView nickName;
        TextView text;
        ImageView image;
    }
}
