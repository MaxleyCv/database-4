package iot.lviv.ua.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AbstractDao<T> {
    ArrayList<T> findAll() throws SQLException;

    T findOne(Integer id) throws SQLException;

    void create(T entity) throws SQLException;

    void update(Integer id, T entity) throws SQLException;

    void delete(Integer id) throws SQLException;
}
