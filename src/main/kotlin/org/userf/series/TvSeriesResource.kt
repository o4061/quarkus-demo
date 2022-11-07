package org.userf.series

import io.quarkus.runtime.Startup
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.userf.series.proxy.EpisodesProxy
import org.userf.series.proxy.TvSeriesProxy
import org.userf.series.repository.TvSerieRepository
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/series")
class TvSeriesResource {

    @RestClient
    lateinit var tvSeriesProxy: TvSeriesProxy

    @RestClient
    lateinit var episodeProxy: EpisodesProxy

    @Inject
    lateinit var tvSerieRepository: TvSerieRepository


    @Startup
    @Transactional
    fun initDatabase() {
        tvSerieRepository.persist(tvSeriesProxy.getPage(1))
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): Response {
        return Response.ok(tvSerieRepository.listAll()).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun get(@QueryParam("title") title: String): Response {
        val serie = tvSerieRepository.getByName(title) ?: return Response.status(Response.Status.NOT_FOUND).build()
        return Response.ok(serie).build()
    }
}