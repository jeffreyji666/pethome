package pethome.mobile.pethome.model;

/**
 * Created by you on 10/22/14.
 */
public class Tweet {
    private Long id = 0L;
    private String avatar;
    private String nickName;
    private String text;
    private String image;
    private Integer votingUp;
    private Integer votingDown;
    private Integer comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getVotingUp() {
        return votingUp;
    }

    public void setVotingUp(Integer votingUp) {
        this.votingUp = votingUp;
    }

    public Integer getVotingDown() {
        return votingDown;
    }

    public void setVotingDown(Integer votingDown) {
        this.votingDown = votingDown;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }
}
