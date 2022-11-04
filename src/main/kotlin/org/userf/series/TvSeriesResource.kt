package org.userf.series

import org.eclipse.microprofile.rest.client.inject.RestClient
import org.userf.series.model.TvSerie
import org.userf.series.proxy.EpisodesProxy
import org.userf.series.proxy.TvSeriesProxy
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/tvseries")
class TvSeriesResource {
    private val tvSeries = ArrayList<TvSerie>()

    @RestClient
    lateinit var tvSeriesProxy: TvSeriesProxy

    @RestClient
    lateinit var episodeProxy: EpisodesProxy

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun get(@QueryParam("title") title: String): Response {
        val serie = tvSeriesProxy.get(title)
        serie.episodes = episodeProxy.get(serie.id)
        tvSeries.add(serie)
        return Response.ok(tvSeries).build()
    }
}