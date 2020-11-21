package iot.lviv.ua.model.dao.impl;

import iot.lviv.ua.model.dao.AbstractDao;
import iot.lviv.ua.model.entity.Actor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static iot.lviv.ua.Connector.getConnection;

public class ActorDao implements AbstractDao<Actor> {
    private static final String GET_ALL_QUERY = "SELECT * FROM leszczynski.actor;";
    private static final String GET_ONE_QUERY = "SELECT * FROM leszczynski.actor WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO leszczynski.actor (name, surname, appendix) VALUES (?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE leszczynski.actor SET name = ?, surname = ?, appendix = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM leszczynski.actor WHERE id = ?;";

    @Override
    public ArrayList<Actor> findAll() throws SQLException {
        ArrayList<Actor> actors = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Actor actor = new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("appendix")
                );
                actors.add(actor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actors;
    }

    @Override
    public Actor findOne(Integer id) throws SQLException {
        Actor actor = null;

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                actor = new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("appendix")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actor;
    }

    @Override
    public void create(Actor entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getAppendix());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Actor entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getAppendix());
            statement.setInt(4, id);
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
