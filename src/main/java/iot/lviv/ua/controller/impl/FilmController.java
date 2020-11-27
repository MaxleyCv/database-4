package iot.lviv.ua.controller.impl;

import iot.lviv.ua.controller.AbstractController;
import iot.lviv.ua.model.dao.impl.FilmDao;
import iot.lviv.ua.model.entity.Film;

import java.sql.SQLException;
import java.util.ArrayList;

public class FilmController implements AbstractController<Film> {
    private final FilmDao filmDao = new FilmDao();

    @Override
    public ArrayList<Film> findAll() throws SQLException {
        return filmDao.findAll();
    }

    @Override
    public Film findOne(Integer id) throws SQLException {
        return filmDao.findOne(id);
    }

    @Override
    public void create(Film entity) throws SQLException {
        filmDao.create(entity);
    }

    @Override
    public void update(Integer id, Film entity) throws SQLException {
        filmDao.update(id, entity);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        filmDao.delete(id);
    }
}
