package com.springjpahibernate.cruddemo.DAO;

import com.springjpahibernate.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
        System.out.println("Initialization "+ getClass().getSimpleName());
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // From Student (All JPA syntax is based on entity name and entity fields)
//        TypedQuery<Student> theQuery = entityManager.createQuery("From Student", Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "From Student order by lastName asc", Student.class);

        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "From Student where lastName=:theData", Student.class);
        // set query parameters
        theQuery.setParameter("theData", theLastName);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // retrive the student
        Student theStudent = entityManager.find(Student.class, id);

        // remove
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsUpdated = entityManager.createQuery(
                "DELETE FROM Student")
                .executeUpdate();
        return numRowsUpdated;
    }

//    @Override
//    @Transactional
//    public int updateAll(Student theStudent) {
//        int numRowsUpdated = entityManager.createQuery(
//                "Update Student Set lastName='Tester'")
//                .executeUpdate();
//        return numRowsUpdated;
//    }

}
