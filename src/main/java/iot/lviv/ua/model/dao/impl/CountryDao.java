package iot.lviv.ua.model.dao.impl;

import iot.lviv.ua.model.dao.AbstractDao;
import iot.lviv.ua.model.entity.Country;
import net.sf.saxon.expr.instruct.Actor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static iot.lviv.ua.Connector.getConnection;

public class CountryDao implements AbstractDao<Country> {
    private static final String GET_ALL_QUERY = "SELECT * FROM leszczynski.country;";
    private static final String GET_ONE_QUERY = "SELECT * FROM leszczynski.country WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO leszczynski.country (name, president) VALUES (?, ?);";
    private static final String UPDATE_QUERY = "UPDATE leszczynski.country SET name = ?, president = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM leszczynski.country WHERE id = ?;";

    @Override
    public ArrayList<Country> findAll() throws SQLException {
        ArrayList<Country> countries = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("president")
                );
                countries.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public Country findOne(Integer id) throws SQLException {
        Country country = null;

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                country = new Country(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("president")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public void create(Country entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPresident());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Country entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPresident());
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
