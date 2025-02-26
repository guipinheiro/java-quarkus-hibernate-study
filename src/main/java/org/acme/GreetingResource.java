package org.acme;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;
import java.util.stream.Collectors;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String hello(@QueryParam("name") String name) {
        Greeting greeting = new Greeting();
        greeting.name = name;
        greeting.persist();
        return "Hello " + name;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    @Path("names")
    public String names() {
        List<Greeting> greetings = Greeting.listAll();
        String names = greetings.stream().map(g -> g.name)
                .collect(Collectors.joining(", "));
        return "I've greeted " + names;
    }
}
