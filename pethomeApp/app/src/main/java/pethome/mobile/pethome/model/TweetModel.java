package pethome.mobile.pethome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by you on 10/22/14.
 */
public class TweetModel {

    public static List<Tweet> getMessages() {
        List<Tweet> tweets = new ArrayList<Tweet>();
        for (int i = 0; i < 100; i++) {
            Tweet tweet = new Tweet();
            tweet.setAvatar("http://img4.imgtn.bdimg.com/it/u=480316896,321062080&fm=23&gp=0.jpg");
            tweet.setNickName("test" + i);
            tweet.setText("messagegfdsfddddddddddddd基dddddddddddddddddddddd夺城城城霜" + i);
            tweet.setImage("http://img4.imgtn.bdimg.com/it/u=276752150,2278137470&fm=21&gp=0.jpg");
            tweet.setVotingUp(i);
            tweet.setVotingDown(i);
            tweet.setComments(i);
            tweets.add(tweet);
        }
        return tweets;
    }
}