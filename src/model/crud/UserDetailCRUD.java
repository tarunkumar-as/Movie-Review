package model.crud;

import model.SQLConnector;
import model.objects.UserDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDetailCRUD implements ICRUD<UserDetail> {

    @Override
    public boolean create(UserDetail newObject) {
        String sql = String.format("INSERT INTO user_detail (user_id, fname, lname, dob, phone, user_photo) VALUES (%d,'%s','%s','%s',%s,'%s')",
                newObject.getUser_id(), newObject.getFname(), newObject.getLname(), newObject.getDob(), newObject.getPhone(), newObject.getUser_photo());
        return SQLConnector.createRecord(sql);
    }

    @Override
    public List<UserDetail> read(String query) {
        List<UserDetail> usersList = new ArrayList<>();
        String sql = String.format("SELECT * from user_detail");
        if (query != null) {
            sql += (" where " + query);
        }
        ResultSet resultSet = SQLConnector.fetchValues(sql);
        if (resultSet != null) {
            try {
                while(resultSet.next()){
                    UserDetail userObject = new UserDetail();
                    userObject.setUser_id(resultSet.getInt("user_id"));
                    userObject.setFname(resultSet.getString("fname"));
                    userObject.setLname(resultSet.getString("lname"));
                    userObject.setDob(resultSet.getDate("dob"));
                    userObject.setPhone(resultSet.getLong("phone"));
                    userObject.setUser_photo(resultSet.getString("user_photo"));
                    usersList.add(userObject);
                }
            } catch (SQLException ignored) {}
        }
        return usersList;
    }

    @Override
    public boolean update(UserDetail oldObject, UserDetail updatedObject) {
        String sql = String.format("UPDATE user_detail SET fname = '%s', lname = '%s', dob = '%s', phone = %s, user_photo = '%s' WHERE user_id = %d",
                updatedObject.getFname(), updatedObject.getLname(), updatedObject.getDob(), updatedObject.getPhone(), updatedObject.getUser_photo(), oldObject.getUser_id());
        return SQLConnector.updateRecord(sql);
    }

    @Override
    public boolean delete(UserDetail deleteObject) {
        String sql = String.format("DELETE FROM user_detail WHERE user_id = %d", deleteObject.getUser_id());
        return SQLConnector.deleteRecord(sql);
    }
}
