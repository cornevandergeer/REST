package org.example.repositories;

import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.domain.Account;

import java.util.List;

@Singleton
public class AccountDB {

    @PersistenceContext(name = "MySQL")
    private EntityManager em;


    public List<Account> getAll() {
        TypedQuery<Account> namedQuery = em.createNamedQuery("Account.findAll", Account.class);
        return namedQuery.getResultList();
    }

    public Account get(int id) {
        return this.em.find(Account.class, id);
    }

    @Transactional
    public void update(Account account) {
        em.merge(account);
    }

    public Account findByUsernameAndPassword(String gebruikersnaam, String wachtwoord) {
        TypedQuery<Account> query = em.createNamedQuery(Account.FIND_BY_LOGIN_PASSWORD, Account.class);
        query.setParameter("gebruikersnaam", gebruikersnaam);
        query.setParameter("wachtwoord", wachtwoord);
        Account account = query.getSingleResult();
        return account;
    }

}
