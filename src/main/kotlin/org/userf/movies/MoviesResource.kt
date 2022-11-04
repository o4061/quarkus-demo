package org.userf.movies

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/movies")
class MoviesResource {

    var movies = ArrayList<Movie>()

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getMovies(): Response {
        return Response.ok(movies).build()
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    fun countMovies(): Int {
        return movies.size
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createMovie(newMovie: Movie): Response {
        return Response.ok(newMovie).build()
    }

    @PUT
    @Path("{oldTitle}/{newTitle}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateMovie(
        @PathParam("oldTitle") oldTitle: String,
        @PathParam("newTitle") newTitle: String
    ): Response {
        movies.find { it.title == oldTitle }?.apply {
            this.title = newTitle
        }
        return Response.ok(movies).build()
    }

    @DELETE
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteMovie(
        @PathParam("name") name: String
    ): Response {
        return if (movies.remove(Movie(name)))
            Response.noContent().build()
        else
            Response.status(Response.Status.BAD_REQUEST).build()
    }

}