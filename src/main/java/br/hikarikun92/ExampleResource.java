package br.hikarikun92;

import io.quarkus.runtime.Startup;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Startup
@Path("/hello")
public class ExampleResource {
    private final EntityManager entityManager;

    public ExampleResource(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> hello() {
        return entityManager.createQuery("select p from PersonEntity p", PersonEntity.class)
                .getResultStream()
                .map(entity -> new Person(entity.getId(), entity.getName(), entity.getAge()))
                .collect(Collectors.toList());
    }
}