package org.example.resources;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.example.domain.Hero;
import org.example.domain.HeroInput;
import org.example.repositories.HeroDB;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Dependent
@Produces({APPLICATION_JSON})
@Consumes(APPLICATION_JSON)
public class HeroResource {

    @Inject
    HeroDB heroDB;
    private int id;

    public HeroResource() {
    }

    @GET
    public Hero get() {
        return this.heroDB.get(this.id);
    }

    @PUT
    public Hero edit(HeroInput input) {
        Hero editedHero = new Hero(id, input.name());
        this.heroDB.edit(editedHero);
        return editedHero;
    }

    @DELETE
    public void delete() {
        this.heroDB.remove(this.id);
    }


    public HeroResource with(int id) {
        this.id = id;
        return this;
    }


}
