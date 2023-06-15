package org.example.repositories;

import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.domain.Hero;

import java.util.List;

@Singleton
public class HeroDB {

    @PersistenceContext(name = "MySQL")
    private EntityManager em;

    public List<Hero> getAll() {
        TypedQuery<Hero> namedQuery = em.createNamedQuery("Hero.findAll", Hero.class);
        return namedQuery.getResultList();
    }

    public Hero get(int id) {
        return em.find(Hero.class, id);
    }

    @Transactional
    public void remove(int id) {
        Hero hero = em.find(Hero.class, id);
        em.remove(hero);
    }

    @Transactional
    public Hero add(Hero hero) {
        em.persist(hero);
        return hero;
    }

    @Transactional
    public Hero edit(Hero hero) {
        em.merge(hero);
        return hero;
    }


}
