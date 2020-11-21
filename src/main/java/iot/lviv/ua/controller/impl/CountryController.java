package iot.lviv.ua.controller.impl;

import iot.lviv.ua.controller.AbstractController;
import iot.lviv.ua.model.dao.impl.CountryDao;
import iot.lviv.ua.model.entity.Country;

import java.sql.SQLException;
import java.util.ArrayList;

public class CountryController implements AbstractController<Country> {
    private final CountryDao actorDao = new CountryDao();

    @Override
    public ArrayList<Country> findAll() throws SQLException {
        return actorDao.findAll();
    }

    @Override
    public Country findOne(Integer id) throws SQLException {
        return actorDao.findOne(id);
    }

    @Override
    public void create(Country entity) throws SQLException {
        actorDao.create(entity);
    }

    @Override
    public void update(Integer id, Country entity) throws SQLException {
        actorDao.update(id, entity);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        actorDao.delete(id);
    }
}
