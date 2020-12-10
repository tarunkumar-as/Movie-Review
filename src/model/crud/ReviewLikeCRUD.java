package model.crud;

import model.SQLConnector;
import model.objects.ReviewLike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewLikeCRUD implements ICRUD<ReviewLike> {

    @Override
    public boolean create(ReviewLike newObject) {
        String sql = String.format("INSERT INTO review_like (review_id, user_id, up_down_vote) VALUES (%d,%d,%d)",
                newObject.getReview_id(), newObject.getUser_id(), newObject.getUp_down_vote());
        return SQLConnector.createRecord(sql);
    }

    @Override
    public List<ReviewLike> read(String query) {
        List<ReviewLike> reviewLikeList = new ArrayList<>();
        String sql = String.format("SELECT * from review_like");
        if (query != null) {
            sql += (" where " + query);
        }
        ResultSet resultSet = SQLConnector.fetchValues(sql);
        if (resultSet != null) {
            try {
                while(resultSet.next()){
                    ReviewLike reviewLike = new ReviewLike();
                    reviewLike.setUser_id(resultSet.getInt("user_id"));
                    reviewLike.setReview_id(resultSet.getInt("review_id"));
                    reviewLike.setUp_down_vote(resultSet.getInt("up_down_vote"));
                    reviewLikeList.add(reviewLike);
                }
            } catch (SQLException ignored) {}
        }
        return reviewLikeList;
    }

    @Override
    public boolean update(ReviewLike oldObject, ReviewLike updatedObject) {
        String sql = String.format("UPDATE review_like SET up_down_vote = %d WHERE user_id = %d and review_id = %d",
                updatedObject.getUp_down_vote(), oldObject.getUser_id(), oldObject.getReview_id());
        return SQLConnector.updateRecord(sql);
    }

    @Override
    public boolean delete(ReviewLike deleteObject) {
        String sql = String.format("DELETE FROM review_like WHERE user_id = %d and review_id = %d",
                deleteObject.getUser_id(), deleteObject.getReview_id());
        return SQLConnector.deleteRecord(sql);
    }
}
