package iot.lviv.ua.model.dao.impl;

import iot.lviv.ua.model.dao.AbstractDao;
import iot.lviv.ua.model.entity.Film;
import iot.lviv.ua.model.entity.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static iot.lviv.ua.Connector.getConnection;

public class ReviewDao implements AbstractDao<Review> {
    private static final String GET_ALL_QUERY = "SELECT * FROM leszczynski.review;";
    private static final String GET_ONE_QUERY = "SELECT * FROM leszczynski.review WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO leszczynski.review (points, text_of_review, film_id, user_id) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE leszczynski.review SET points = ?, text_of_review = ?, film_idr = ?, user_id = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM leszczynski.review WHERE id = ?;";

    @Override
    public ArrayList<Review> findAll() throws SQLException {
        ArrayList<Review> reviews = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review(
                        resultSet.getInt("id"),
                        resultSet.getInt("points"),
                        resultSet.getString("text_of_review"),
                        resultSet.getInt("film_id"),
                        resultSet.getInt("user_id")
                );
                reviews.add(review);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public Review findOne(Integer id) throws SQLException {
        Review review = null;

        try (PreparedStatement statement = getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                review = new Review(
                        resultSet.getInt("id"),
                        resultSet.getInt("points"),
                        resultSet.getString("text_of_review"),
                        resultSet.getInt("film_id"),
                        resultSet.getInt("user_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public void create(Review entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setInt(1, entity.getPoints());
            statement.setString(2, entity.getText());
            statement.setInt(3, entity.getFilmId());
            statement.setInt(4, entity.getUserId());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Review entity) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY)){
            statement.setInt(1, entity.getPoints());
            statement.setString(2, entity.getText());
            statement.setInt(3, entity.getFilmId());
            statement.setInt(4, entity.getUserId());
            statement.setInt(5, id);
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
