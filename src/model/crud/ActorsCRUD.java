package model.crud;

import model.objects.Actors;

import java.util.List;

public class ActorsCRUD implements ICRUD<Actors> {

    @Override
    public boolean create(Actors newObject) {
        return false;
    }

    @Override
    public List<Actors> read(String query) {
        return null;
    }

    @Override
    public boolean update(Actors oldObject, Actors updatedObject) {
        return false;
    }

    @Override
    public boolean delete(Actors deleteObject) {
        return false;
    }
}
