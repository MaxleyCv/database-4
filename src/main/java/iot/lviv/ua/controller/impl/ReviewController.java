package iot.lviv.ua.controller.impl;

import iot.lviv.ua.controller.AbstractController;
import iot.lviv.ua.model.dao.impl.ReviewDao;
import iot.lviv.ua.model.entity.Review;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewController implements AbstractController<Review> {
    private final ReviewDao reviewDao = new ReviewDao();

    @Override
    public ArrayList<Review> findAll() throws SQLException {
        return reviewDao.findAll();
    }

    @Override
    public Review findOne(Integer id) throws SQLException {
        return reviewDao.findOne(id);
    }

    @Override
    public void create(Review entity) throws SQLException {
        reviewDao.create(entity);
    }

    @Override
    public void update(Integer id, Review entity) throws SQLException {
        reviewDao.update(id, entity);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        reviewDao.delete(id);
    }
}
