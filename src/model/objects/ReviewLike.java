package model.objects;

public class ReviewLike {

    private int user_id;
    private int review_id;
    private int up_down_vote;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getUp_down_vote() {
        return up_down_vote;
    }

    public void setUp_down_vote(int up_down_vote) {
        this.up_down_vote = up_down_vote;
    }
}
