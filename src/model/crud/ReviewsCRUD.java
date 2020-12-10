package model.crud;

import model.SQLConnector;
import model.objects.Reviews;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewsCRUD implements ICRUD<Reviews> {

    @Override
    public boolean create(Reviews newObject) {
        String sql = String.format("INSERT INTO reviews (date, comment, movie_id, user_id) VALUES ('%s','%s',%d,%d)",
                newObject.getDate(), newObject.getComment(), newObject.getMovie_id(), newObject.getUser_id());
        return SQLConnector.createRecord(sql);
    }

    @Override
    public List<Reviews> read(String query) {
        List<Reviews> reviewsList = new ArrayList<>();
        String sql = String.format("SELECT * from reviews");
        if (query != null) {
            sql += (" where " + query);
        }
        ResultSet resultSet = SQLConnector.fetchValues(sql);
        if (resultSet != null) {
            try {
                while(resultSet.next()){
                    Reviews review = new Reviews();
                    review.setReview_id(resultSet.getInt("review_id"));
                    review.setUser_id(resultSet.getInt("user_id"));
                    review.setComment(resultSet.getString("comment"));
                    review.setDate(resultSet.getDate("date"));
                    review.setMovie_id(resultSet.getInt("movie_id"));
                    reviewsList.add(review);
                }
            } catch (SQLException ignored) {}
        }
        return reviewsList;
    }

    @Override
    public boolean update(Reviews oldObject, Reviews updatedObject) {
        String sql = String.format("UPDATE reviews SET date = '%s', comment = '%s', movie_id = %d, user_id = %d WHERE review_id = %d",
                updatedObject.getDate(), updatedObject.getComment(), updatedObject.getMovie_id(), updatedObject.getUser_id(), oldObject.getReview_id());
        return SQLConnector.updateRecord(sql);
    }

    @Override
    public boolean delete(Reviews deleteObject) {
        String sql = String.format("DELETE FROM reviews WHERE review_id = %d", deleteObject.getReview_id());
        return SQLConnector.deleteRecord(sql);
    }
}
