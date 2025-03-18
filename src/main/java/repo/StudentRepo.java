package repo;

import entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class StudentRepo {
    public StudentEntity save(StudentEntity entity, Session session) {
        Serializable save = session.save(entity);
        entity.setId((Integer) save);
        return entity;
    }

    public static StudentEntity getById(int id, Session session) {
        return session.get(StudentEntity.class, id);
    }

    public void delete(StudentEntity entity, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }


}
