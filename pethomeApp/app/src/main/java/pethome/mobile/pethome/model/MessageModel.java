package pethome.mobile.pethome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by you on 10/22/14.
 */
public class MessageModel {

    public static List<Message> getMessages() {
        List<Message> messages = new ArrayList<Message>();
        for (int i = 0; i < 100; i++) {
            Message message = new Message();
            message.setAvatar("http://img4.imgtn.bdimg.com/it/u=480316896,321062080&fm=23&gp=0.jpg");
            message.setNickName("test" + i);
            message.setText("messagegfdsfddddddddddddd基dddddddddddddddddddddd夺城城城霜" + i);
            message.setImage("http://img4.imgtn.bdimg.com/it/u=276752150,2278137470&fm=21&gp=0.jpg");
            messages.add(message);
        }
        return messages;
    }
}