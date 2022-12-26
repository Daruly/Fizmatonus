package Forum;

public class Post {
    private String postId;
    private String category;
    private String desc;


    //private User opUser;
    private int likes;
    private String imageBit64;
    private long unixTime;
    /*public Post(String postId, String title, String category, User opUser, int likes, String imageBit64) {
        this.postId = postId;
        this.title = title;
        this.category = category;
        this.opUser = opUser;
        this.likes = likes;
        this.imageBit64 = imageBit64;
    }*/

    public Post(String postId, String category, int likes, String imageBit64, long unixTime, String desc) {
        this.postId = postId;
        this.category = category;
        this.likes = likes;
        this.imageBit64 = imageBit64;
        this.unixTime = unixTime;
        this.desc=desc;
    }

    public Post() {

    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getImageBit64() {
        return imageBit64;
    }

    public void setImageBit64(String imageBit64) {
        this.imageBit64 = imageBit64;
    }

    /*public void setUser(User user){
        this.user=user;
    }

    public User getUser(User user){
        return user;
    }*/
}
