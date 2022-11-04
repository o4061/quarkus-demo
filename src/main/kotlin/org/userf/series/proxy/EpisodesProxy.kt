package org.userf.series.proxy

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.userf.series.model.Episode
import org.userf.series.model.TvSerie
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/shows")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
interface EpisodesProxy {

    @GET
    @Path("/{id}/episodes")
    fun get(@PathParam("id") id: Long): List<Episode>
}