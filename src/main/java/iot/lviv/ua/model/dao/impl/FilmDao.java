package iot.lviv.ua.model.dao.impl;

import iot.lviv.ua.model.dao.AbstractDao;
import iot.lviv.ua.model.entity.Actor;
import iot.lviv.ua.model.entity.Film;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static iot.lviv.ua.Connector.getConnection;

public class FilmDao extends AbstractDaoImpl<Film> {

    public FilmDao() {
        super(Film.class);
    }
}
