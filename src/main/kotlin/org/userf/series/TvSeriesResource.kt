package org.userf.series

import io.quarkus.runtime.Startup
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.userf.series.model.Episode
import org.userf.series.model.TvSerie
import org.userf.series.proxy.EpisodesProxy
import org.userf.series.proxy.TvSeriesProxy
import org.userf.series.repository.EpisodesRepository
import org.userf.series.repository.TvSerieRepository
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
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

    @Inject
    lateinit var episodesRepository: EpisodesRepository


    @Startup
    @Transactional
    fun initDatabase() {
        val series = tvSeriesProxy.getPage(1)
        val episodes = ArrayList<Episode>()
        tvSerieRepository.persist(series)

        series.forEach { tvSerie ->
            val allEpisodes = episodeProxy.get(tvSerie.id)
            allEpisodes.forEach {
                it.serieId = tvSerie.id
            }
            episodes.addAll(allEpisodes)
        }
        episodesRepository.persist(episodes)
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllTvSeries(): Response {
        return Response.ok(tvSerieRepository.listAll()).build()
    }

    @Path("/all/episodes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllEpisodes(): Response {
        return Response.ok(episodesRepository.listAll()).build()
    }

    @Path("/all/episodes/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllSerieEpisodes(
        @PathParam("id") id: Long
    ): Response {
//        return Response.ok(episodesRepository.findBySerieId(id)).build()
        return Response.ok(tvSerieRepository.findById(1)?.episode).build()
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun get(@QueryParam("title") title: String): Response {
        val serie = tvSerieRepository.getByName(title) ?: return Response.status(Response.Status.NOT_FOUND).build()
        return Response.ok(serie).build()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    fun create(tvSerie: TvSerie): Response {
        tvSerieRepository.persist(tvSerie)
        return Response.ok(tvSerie).build()
    }

}