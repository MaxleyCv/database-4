package iot.lviv.ua.model.dao.impl;
import java.util.ArrayList;
import java.util.List;

import iot.lviv.ua.HibernateUtil;
import iot.lviv.ua.model.dao.AbstractDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDaoImpl<E> implements AbstractDao<E> {

    private final Class<E> currentClass;
    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public AbstractDaoImpl(Class<E> currentClass) {
        this.currentClass = currentClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<E> findAll() {
        List<E> entities = new ArrayList<>();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            entities = session.createQuery("from " + currentClass.getName()).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<E>) entities;
    }

    @Override
    public E findOne(Integer id) {
        E entity = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            entity = session.get(currentClass, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void create(E entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, E entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            E entity = session.get(currentClass, id);
            if (entity != null) {
                session.delete(entity);
            }
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
