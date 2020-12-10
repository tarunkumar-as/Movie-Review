package model.crud;

import model.SQLConnector;
import model.objects.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersCRUD implements ICRUD<Users> {

    @Override
    public boolean create(Users newObject) {
        String sql = String.format("INSERT INTO users (role, password, userName, is_verified) VALUES ('%s','%s','%s',%b)",
                newObject.getRole(), newObject.getPassword(), newObject.getUserName(), newObject.isVerified());
        return SQLConnector.createRecord(sql);
    }

    @Override
    public List<Users> read(String query) {
        List<Users> usersList = new ArrayList<>();
        String sql = String.format("SELECT * from users");
        if (query != null) {
            sql += (" where " + query);
        }
        ResultSet resultSet = SQLConnector.fetchValues(sql);
        if (resultSet != null) {
            try {
                while(resultSet.next()){
                    Users userObject = new Users();
                    userObject.setUser_id(resultSet.getInt("user_id"));
                    userObject.setUserName(resultSet.getString("userName"));
                    userObject.setRole(resultSet.getString("role"));
                    userObject.setVerified(resultSet.getBoolean("is_verified"));
                    userObject.setPassword(resultSet.getString("password"));
                    usersList.add(userObject);
                }
            } catch (SQLException ignored) {}
        }
        return usersList;
    }

    @Override
    public boolean update(Users oldObject, Users updatedObject) {
        String sql = String.format("UPDATE users SET role = '%s', password = '%s', is_verified = %b WHERE user_id = %d",
                updatedObject.getRole(), updatedObject.getPassword(), updatedObject.isVerified(), oldObject.getUser_id());
        return SQLConnector.updateRecord(sql);
    }

    @Override
    public boolean delete(Users deleteObject) {
        String sql = String.format("DELETE FROM users WHERE user_id = %d", deleteObject.getUser_id());
        return SQLConnector.deleteRecord(sql);
    }
}
