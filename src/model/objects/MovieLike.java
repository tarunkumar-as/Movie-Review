package model.objects;

public class MovieLike {

    private int movie_id;
    private int user_id;
    private int up_down_vote;

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUp_down_vote() {
        return up_down_vote;
    }

    public void setUp_down_vote(int up_down_vote) {
        this.up_down_vote = up_down_vote;
    }
}
