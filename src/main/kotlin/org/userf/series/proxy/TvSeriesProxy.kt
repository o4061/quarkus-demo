package org.userf.series.proxy

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.userf.series.model.TvSerie
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/singlesearch")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
interface TvSeriesProxy {

    @GET
    @Path("/shows")
    fun get(@QueryParam("q") name: String): TvSerie
}