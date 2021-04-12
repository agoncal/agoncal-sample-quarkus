package org.agoncal.sample.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @GET
    @Path("/without/{id}")
    public Book findBookByIdWithoutOptional(@PathParam("id") Long id) {
        // 204 if not found
        return Book.findById(id);
    }

    @GET
    @Path("/with/{id}")
    public Optional<Book> findBookByIdWithOptiona(@PathParam("id") Long id) {
        // 200 if not found
        return Book.findByIdOptional(id);
    }

    @GET
    @Path("/response/{id}")
    public Response findBookByIdWithResponse(@PathParam("id") Long id) {
        return Book
            .findByIdOptional(id)
            .map(book -> Response.ok(book).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
