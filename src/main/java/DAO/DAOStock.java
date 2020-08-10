package DAO;

import Model.Stock;

import java.util.List;
import java.util.Optional;

public class DAOStock implements DAO{

    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(Object o) {

    }
}
