package model.crud;

import model.SQLConnector;
import model.objects.Actors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorsCRUD implements ICRUD<Actors> {

    @Override
    public boolean create(Actors newObject) {
        String sql = String.format("INSERT INTO actors (name, photo_url, sex) VALUES ('%s','%s','%c')",
                newObject.getName(), newObject.getPhoto_url(), newObject.getSex());
        return SQLConnector.createRecord(sql);
    }

    @Override
    public List<Actors> read(String query) {
        List<Actors> actorsList = new ArrayList<>();
        String sql = String.format("SELECT * from actors");
        if (query != null) {
            sql += (" where " + query);
        }
        ResultSet resultSet = SQLConnector.fetchValues(sql);
        if (resultSet != null) {
            try {
                while(resultSet.next()){
                    Actors actor = new Actors();
                    actor.setActor_id(resultSet.getInt("actor_id"));
                    actor.setName(resultSet.getString("name"));
                    actor.setPhoto_url(resultSet.getString("photo_url"));
                    actor.setSex(resultSet.getString("sex").charAt(0));
                    actorsList.add(actor);
                }
            } catch (SQLException ignored) {}
        }
        return actorsList;
    }

    @Override
    public boolean update(Actors oldObject, Actors updatedObject) {
        String sql = String.format("UPDATE actors SET name = '%s', photo_url = '%s', sex = '%c' WHERE actor_id = %d",
                updatedObject.getName(), updatedObject.getPhoto_url(), updatedObject.getSex(), oldObject.getActor_id());
        return SQLConnector.updateRecord(sql);
    }

    @Override
    public boolean delete(Actors deleteObject) {
        String sql = String.format("DELETE FROM actors WHERE actor_id = %d", deleteObject.getActor_id());
        return SQLConnector.deleteRecord(sql);
    }
}
