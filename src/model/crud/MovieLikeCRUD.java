package model.crud;

import model.objects.MovieLike;

import java.util.List;

public class MovieLikeCRUD implements ICRUD<MovieLike> {

    @Override
    public boolean create(MovieLike newObject) {
        return false;
    }

    @Override
    public List<MovieLike> read(String query) {
        return null;
    }

    @Override
    public boolean update(MovieLike oldObject, MovieLike updatedObject) {
        return false;
    }

    @Override
    public boolean delete(MovieLike deleteObject) {
        return false;
    }
}
