package org.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Account")
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
        @NamedQuery(name = Account.FIND_BY_LOGIN_PASSWORD, query = "SELECT u FROM Account u WHERE u.gebruikersnaam = :gebruikersnaam AND u.wachtwoord = :wachtwoord")

})
public class Account {

    public static final String FIND_BY_LOGIN_PASSWORD = "Account.findByLoginAndPassword";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String gebruikersnaam;
    private String wachtwoord;
    private String jwt;

    public Account(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.jwt = "";
    }


}
