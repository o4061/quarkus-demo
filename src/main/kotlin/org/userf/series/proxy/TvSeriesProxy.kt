package org.userf.series.proxy

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.userf.series.model.TvSerie
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
interface TvSeriesProxy {

    @GET
    @Path("/singlesearch/shows")
    fun getByName(@QueryParam("q") name: String): TvSerie

    @GET
    @Path("shows/{id}")
    fun getById(@PathParam("id") id: Long): TvSerie

    @GET
    @Path("shows")
    fun getPage(@QueryParam("page") page: Int): List<TvSerie>
}