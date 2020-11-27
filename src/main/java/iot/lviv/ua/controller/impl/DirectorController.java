package iot.lviv.ua.controller.impl;

import iot.lviv.ua.controller.AbstractController;
import iot.lviv.ua.model.dao.impl.DirectorDao;
import iot.lviv.ua.model.entity.Director;

import java.sql.SQLException;
import java.util.ArrayList;

public class DirectorController implements AbstractController<Director> {
    private final DirectorDao directorDao = new DirectorDao();

    @Override
    public ArrayList<Director> findAll() throws SQLException {
        return directorDao.findAll();
    }

    @Override
    public Director findOne(Integer id) throws SQLException {
        return directorDao.findOne(id);
    }

    @Override
    public void create(Director entity) throws SQLException {
        directorDao.create(entity);
    }

    @Override
    public void update(Integer id, Director entity) throws SQLException {
        directorDao.update(id, entity);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        directorDao.delete(id);
    }
}
