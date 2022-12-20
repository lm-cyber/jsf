package com.example.jsf.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SessionScoped
@Named
@Transactional
public class AttemptsManager implements Serializable {
    @PersistenceContext(unitName = "db")
    private EntityManager em;
    private final List<PointAttempt> attempts;

    public AttemptsManager() {
        attempts = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        attempts.addAll(em.createQuery("SELECT a FROM attempts a", PointAttempt.class).getResultList());
    }

    public List<PointAttempt> getAttempts() {
        List<PointAttempt> l = new ArrayList<>(attempts);
        Collections.reverse(l);
        return l;
    }

    public void addAttempt(PointAttempt attempt) {
        attempts.add(attempt);
        em.persist(attempt);
    }
}
