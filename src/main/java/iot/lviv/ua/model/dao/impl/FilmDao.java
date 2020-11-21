package iot.lviv.ua.model.dao.impl;

import iot.lviv.ua.model.dao.AbstractDao;
import iot.lviv.ua.model.entity.Actor;
import iot.lviv.ua.model.entity.Film;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static iot.lviv.ua.Connector.getConnection;

public class FilmDao implements AbstractDao<Film> {
    private static final String GET_ALL_QUERY = "SELECT * FROM leszczynski.film;";
    private static final String GET_ONE_QUERY = "SELECT * FROM leszczynski.film WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO leszczynski.film (title, description, publish_year, origin_country, director_id) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE leszczynski.film SET title = ?, description = ?, publish_year = ?, origin_country = ?, director_id = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM leszczynski.film WHERE id = ?;";

    @Override
    public ArrayList<Film> findAll() throws SQLException {
        ArrayList<Film> films = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("publish_year"),
                        resultSet.getString("origin_country"),
                        resultSet.getInt("director_id")
                );
                films.add(film);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }

    @Override
    public Film findOne(Integer id) throws SQLException {
        Film film = null;

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("publish_year"),
                        resultSet.getString("origin_country"),
                        resultSet.getInt("director_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public void create(Film entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getPublishYear());
            statement.setString(4, entity.getCountryOfOrigin());
            statement.setInt(5, entity.getDirectorId());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Film entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY)){
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getPublishYear());
            statement.setString(4, entity.getCountryOfOrigin());
            statement.setInt(5, entity.getDirectorId());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
