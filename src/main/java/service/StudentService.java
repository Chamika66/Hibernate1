package service;

import DTO.StudentDto;
import entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.StudentRepo;
import util.FactoryConfiguration;

public class StudentService {

    private StudentRepo repo;
    public StudentService(StudentRepo repo) {
       // this.repo = new StudentRepo();
    }

    public void setStudentRepo(StudentRepo repo) {
        this.repo = repo;
    }

    public StudentDto saveStudent(StudentDto student) {

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress(student.getAddress());
        studentEntity.setContact(student.getContact());
        studentEntity.setName(student.getName());

        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            StudentEntity save = repo.save(studentEntity, session);
            student.setId(save.getId());
            transaction.commit();
            return student;
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }
}
