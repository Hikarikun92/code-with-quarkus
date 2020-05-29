package br.hikarikun92;

import io.quarkus.runtime.Startup;
import org.jooq.DSLContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static br.hikarikun92.persistence.jooq.tables.Person.PERSON;

@Startup
@Path("/hello")
public class ExampleResource {
    private final DSLContext dsl;

    public ExampleResource(DSLContext dsl) {
        this.dsl = dsl;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> hello() {
        return dsl.selectFrom(PERSON)
                .fetch(record -> new Person(record.getId(), record.getName(), record.getAge()));
    }
}