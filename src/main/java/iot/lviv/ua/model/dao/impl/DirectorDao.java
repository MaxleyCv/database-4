package iot.lviv.ua.model.dao.impl;

import iot.lviv.ua.model.dao.AbstractDao;
import iot.lviv.ua.model.entity.Actor;
import iot.lviv.ua.model.entity.Director;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static iot.lviv.ua.Connector.getConnection;

public class DirectorDao implements AbstractDao<Director> {
    private static final String GET_ALL_QUERY = "SELECT * FROM leszczynski.director;";
    private static final String GET_ONE_QUERY = "SELECT * FROM leszczynski.director WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO leszczynski.director (name, surname) VALUES (?, ?);";
    private static final String UPDATE_QUERY = "UPDATE leszczynski.director SET name = ?, surname = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM leszczynski.director WHERE id = ?;";

    @Override
    public ArrayList<Director> findAll() throws SQLException {
        ArrayList<Director> directors = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Director director = new Director(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname")
                );
                directors.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directors;
    }

    @Override
    public Director findOne(Integer id) throws SQLException {
        Director director = null;

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                director = new Director(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return director;
    }

    @Override
    public void create(Director entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Director entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setInt(3, id);
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
