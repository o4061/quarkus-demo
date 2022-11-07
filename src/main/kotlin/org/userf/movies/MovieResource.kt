package org.userf.movies

import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MovieResource {

    @Inject
    var movieRepository: MovieRepository? = null

    @GET
    fun getAll(): Response? {
        val movies = movieRepository!!.listAll()
        return Response.ok(movies).build()
    }
}