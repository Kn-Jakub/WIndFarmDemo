package sk.fri.uniza.resources;


import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.sessions.Session;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sk.fri.uniza.api.Saying;
import sk.fri.uniza.auth.Role;
import sk.fri.uniza.core.User;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Hello World")
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("/hello-world")
    @Timed
    @RolesAllowed(Role.ADMIN)
    @ApiOperation(value = "Gets text of hello world with name if the name is specified in query. " +
            "If there is not name in the query, default name is returned")
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    @GET
    @Path("/hello-user")
    @Timed
    @RolesAllowed({Role.ADMIN, Role.USER_READ_ONLY})
    @ApiOperation(value = "Gets text of hello world with name of user from authentication. ")
    public Saying sayHello(@Auth User user) {
        final String value = String.format(template, user.getName());
        return new Saying(counter.incrementAndGet(), value);
    }

    @GET
    @Path("/sessiontest")
    @Timed
    @ExceptionMetered
    public Response getMain(@Context UriInfo info, @Session HttpSession session) {

        return Response.ok().build();
    }
}