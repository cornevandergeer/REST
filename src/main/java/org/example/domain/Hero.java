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
@Table(name = "Hero")
@NamedQuery(name = "Hero.findAll", query = "SELECT a FROM Hero a")

//@XmlRootElement
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name;
//    private String url;

    public Hero(String name) {
        this.name = name;
    }


}
