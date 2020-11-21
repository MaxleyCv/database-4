package iot.lviv.ua.controller.impl;

import iot.lviv.ua.controller.AbstractController;
import iot.lviv.ua.model.dao.impl.ActorDao;
import iot.lviv.ua.model.entity.Actor;

import java.sql.SQLException;
import java.util.ArrayList;

public class ActorController implements AbstractController<Actor> {
    private final ActorDao actorDao = new ActorDao();

    @Override
    public ArrayList<Actor> findAll() throws SQLException {
        return actorDao.findAll();
    }

    @Override
    public Actor findOne(Integer id) throws SQLException {
        return actorDao.findOne(id);
    }

    @Override
    public void create(Actor entity) throws SQLException {
        actorDao.create(entity);
    }

    @Override
    public void update(Integer id, Actor entity) throws SQLException {
        actorDao.update(id, entity);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        actorDao.delete(id);
    }
}
