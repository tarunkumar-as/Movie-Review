package model.crud;

import model.objects.UserDetail;

import java.util.List;

public class UserDetailCRUD implements ICRUD<UserDetail> {

    @Override
    public boolean create(UserDetail newObject) {
        return false;
    }

    @Override
    public List<UserDetail> read(String query) {
        return null;
    }

    @Override
    public boolean update(UserDetail oldObject, UserDetail updatedObject) {
        return false;
    }

    @Override
    public boolean delete(UserDetail deleteObject) {
        return false;
    }
}
