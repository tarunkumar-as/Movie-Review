package model.crud;

import model.objects.Movies;

import java.util.List;

public class MoviesCRUD implements ICRUD<Movies> {

    @Override
    public boolean create(Movies newObject) {
        return false;
    }

    @Override
    public List<Movies> read(String query) {
        return null;
    }

    @Override
    public boolean update(Movies oldObject, Movies updatedObject) {
        return false;
    }

    @Override
    public boolean delete(Movies deleteObject) {
        return false;
    }
}
