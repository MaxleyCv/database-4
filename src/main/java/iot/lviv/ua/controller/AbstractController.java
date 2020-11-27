package iot.lviv.ua.controller;
import java.util.ArrayList;

import java.sql.SQLException;

public interface AbstractController<T> {
    ArrayList<T> findAll() throws SQLException;

    T findOne(Integer id) throws SQLException;

    void create(T entity) throws SQLException;

    void update(Integer id, T entity) throws SQLException;

    void delete(Integer id) throws SQLException;

}
