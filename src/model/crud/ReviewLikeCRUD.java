package model.crud;

import model.objects.ReviewLike;

import java.util.List;

public class ReviewLikeCRUD implements ICRUD<ReviewLike> {

    @Override
    public boolean create(ReviewLike newObject) {
        return false;
    }

    @Override
    public List<ReviewLike> read(String query) {
        return null;
    }

    @Override
    public boolean update(ReviewLike oldObject, ReviewLike updatedObject) {
        return false;
    }

    @Override
    public boolean delete(ReviewLike deleteObject) {
        return false;
    }
}
