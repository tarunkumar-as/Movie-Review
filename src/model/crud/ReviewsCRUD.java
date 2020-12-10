package model.crud;

import model.objects.Reviews;

import java.util.List;

public class ReviewsCRUD implements ICRUD<Reviews> {

    @Override
    public boolean create(Reviews newObject) {
        return false;
    }

    @Override
    public List<Reviews> read(String query) {
        return null;
    }

    @Override
    public boolean update(Reviews oldObject, Reviews updatedObject) {
        return false;
    }

    @Override
    public boolean delete(Reviews deleteObject) {
        return false;
    }
}
