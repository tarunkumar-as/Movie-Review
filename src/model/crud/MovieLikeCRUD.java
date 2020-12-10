package model.crud;

import model.SQLConnector;
import model.objects.MovieLike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieLikeCRUD implements ICRUD<MovieLike> {

    @Override
    public boolean create(MovieLike newObject) {
        String sql = String.format("INSERT INTO movie_like (movie_id, user_id, up_down_vote) VALUES (%d,%d,%d)",
                newObject.getMovie_id(), newObject.getUser_id(), newObject.getUp_down_vote());
        return SQLConnector.createRecord(sql);
    }

    @Override
    public List<MovieLike> read(String query) {
        List<MovieLike> movieLikeList = new ArrayList<>();
        String sql = String.format("SELECT * from movie_like");
        if (query != null) {
            sql += (" where " + query);
        }
        ResultSet resultSet = SQLConnector.fetchValues(sql);
        if (resultSet != null) {
            try {
                while(resultSet.next()){
                    MovieLike movieLike = new MovieLike();
                    movieLike.setUser_id(resultSet.getInt("user_id"));
                    movieLike.setMovie_id(resultSet.getInt("movie_id"));
                    movieLike.setUp_down_vote(resultSet.getInt("up_down_vote"));
                    movieLikeList.add(movieLike);
                }
            } catch (SQLException ignored) {}
        }
        return movieLikeList;
    }

    @Override
    public boolean update(MovieLike oldObject, MovieLike updatedObject) {
        String sql = String.format("UPDATE movie_like SET up_down_vote = %d WHERE user_id = %d and movie_id = %d",
                updatedObject.getUp_down_vote(), oldObject.getUser_id(), oldObject.getMovie_id());
        return SQLConnector.updateRecord(sql);
    }

    @Override
    public boolean delete(MovieLike deleteObject) {
        String sql = String.format("DELETE FROM movie_like WHERE user_id = %d and movie_id = %d",
                deleteObject.getUser_id(), deleteObject.getMovie_id());
        return SQLConnector.deleteRecord(sql);
    }
}
