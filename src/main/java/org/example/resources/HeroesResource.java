package org.example.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.example.domain.Hero;
import org.example.domain.HeroInput;
import org.example.repositories.HeroDB;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("heroes")
public class HeroesResource {


    @Inject
    private HeroDB heroDB;
    @Inject
    private HeroResource heroResource;


    @GET
    @Produces({APPLICATION_JSON})
    public List<Hero> search(@QueryParam("q") String q) {
        return q != null ?
                this.heroDB.getAll().stream()
                        .filter(s -> s.getName().toLowerCase().contains(q.toLowerCase()))
                        .toList() :
                this.heroDB.getAll();
    }


    @POST
    @Produces({APPLICATION_JSON})
    @Consumes(APPLICATION_JSON)
    public Hero add(HeroInput input) {
        Hero newHero = new Hero(input.name());
        this.heroDB.add(newHero);
        return newHero;
    }

    @Path("{id}")
    public HeroResource get(@PathParam("id") int id) {
        return heroResource.with(id);
    }

    @Path("{id}")
    public HeroResource edit(@PathParam("id") int id) {
        return heroResource.with(id);
    }


    @Path("{id}")
    public HeroResource delete(@PathParam("id") int id) {
        return heroResource.with(id);
    }


}
