package model.crud;

import java.util.List;

public interface ICRUD<M> {

    public boolean create(M newObject);

    public List<M> read(String query);

    public boolean update(M oldObject, M updatedObject);

    public boolean delete(M deleteObject);
}
