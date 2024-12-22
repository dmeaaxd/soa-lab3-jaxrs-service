package org.example.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.Session;
import org.example.config.DatabaseSessionManager;
import org.example.entity.Person;
import org.example.exception.DatabaseException;

@ApplicationScoped
public class PersonRepository {

    @Inject
    private DatabaseSessionManager sessionManager;

    public Person findById(Integer id) throws DatabaseException {
        if (id == null) {
            throw new DatabaseException("Id cannot be null");
        }
        Person person;
        Session session = sessionManager.getSession();
        try {
            session.beginTransaction();
            person = session.createQuery("FROM Person WHERE id = :id", Person.class)
                    .setParameter("id", id)
                    .getSingleResultOrNull();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new DatabaseException(e.getMessage());
        } finally {
            sessionManager.closeSession(session);
        }
        return person;
    }
}
