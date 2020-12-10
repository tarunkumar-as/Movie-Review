package model.crud;

import model.SQLConnector;
import model.objects.Actors;
import model.objects.Movies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoviesCRUD implements ICRUD<Movies> {

    @Override
    public boolean create(Movies newObject) {
        String sql = String.format("INSERT INTO movies (movie_name, duration, certification, photo_url) VALUES ('%s','%d','%s','%s')",
                newObject.getMovie_name(), newObject.getDuration(), newObject.getCertification(), newObject.getPhoto_url());
        if (SQLConnector.createRecord(sql)) {
            Movies movie = read("movie_name = '" + newObject.getMovie_name() + "'").get(0);
            movie.setActorsList(newObject.getActorsList());
            if (!createMovieActorLink(movie)) {
                delete(movie);
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean createMovieActorLink(Movies newObject) {
        for (Actors actor : newObject.getActorsList()) {
            String sql = String.format("INSERT INTO movie_actor (movie_id, actor_id) VALUES (%d,%d)",
                    newObject.getMovie_Id(), actor.getActor_id());
            if (!SQLConnector.createRecord(sql)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Movies> read(String query) {
        List<Movies> moviesList = new ArrayList<>();
        String sql = String.format("SELECT * from movies");
        if (query != null) {
            sql += (" where " + query);
        }
        ResultSet resultSet = SQLConnector.fetchValues(sql);
        if (resultSet != null) {
            try {
                while(resultSet.next()){
                    Movies movieObject = new Movies();
                    movieObject.setMovie_Id(resultSet.getInt("movie_Id"));
                    movieObject.setMovie_name(resultSet.getString("movie_name"));
                    movieObject.setDuration(resultSet.getInt("duration"));
                    movieObject.setCertification(resultSet.getString("certification"));
                    movieObject.setPhoto_url(resultSet.getString("photo_url"));
                    movieObject.setActorsList(readMovieActorLink(movieObject));
                    moviesList.add(movieObject);
                }
            } catch (SQLException ignored) {}
        }
        return moviesList;
    }

    private List<Actors> readMovieActorLink(Movies movie) {
        List<Movies> moviesList = new ArrayList<>();
        String whereClause = String.format("actor_id in (select actor_id from movie_actor where movie_id = %d)", movie.getMovie_Id());
        return new ActorsCRUD().read(whereClause);
    }

    @Override
    public boolean update(Movies oldObject, Movies updatedObject) {
        String sql = String.format("UPDATE movies SET movie_name = '%s', duration = %d, certification = '%s', photo_url = '%s' WHERE movie_Id = %d",
                updatedObject.getMovie_name(), updatedObject.getDuration(), updatedObject.getCertification(), updatedObject.getPhoto_url(), oldObject.getMovie_Id());
        if (SQLConnector.updateRecord(sql)) {
            return updateMovieActorLink(oldObject, updatedObject);
        }
        return false;
    }

    private boolean updateMovieActorLink(Movies oldObject, Movies updatedObject) {
        if (deleteMovieActorLink(oldObject)) {
            updatedObject.setMovie_Id(oldObject.getMovie_Id());
            return createMovieActorLink(updatedObject);
        }
        return false;
    }

    @Override
    public boolean delete(Movies deleteObject) {
        String sql = String.format("DELETE FROM movies WHERE movie_Id = %d", deleteObject.getMovie_Id());
        return SQLConnector.deleteRecord(sql);
    }

    private boolean deleteMovieActorLink(Movies deleteObject) {
        String sql = String.format("DELETE FROM movie_actor WHERE movie_Id = %d", deleteObject.getMovie_Id());
        return SQLConnector.deleteRecord(sql);
    }
}
