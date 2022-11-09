package org.userf.series

import org.eclipse.microprofile.faulttolerance.Fallback
import org.eclipse.microprofile.faulttolerance.Timeout
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.userf.series.model.TvSerie
import org.userf.series.proxy.EpisodesProxy
import org.userf.series.proxy.TvSeriesProxy
import java.util.concurrent.TimeUnit
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
    @Fallback(fallbackMethod = "fallbackGet")
    fun get(@QueryParam("title") title: String): Response {
        val serie = tvSeriesProxy.get(title)
        val episodes = episodeProxy.get(serie.id)
        tvSeries.add(serie)
        return Response.ok(episodes).build()
    }


    private fun fallbackGet(title: String): Response {
        return Response.ok(ArrayList<Any>()).build()
    }

    @Path("timeout")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Fallback(fallbackMethod = "fallbackTimeOut")
    @Timeout(2000)
    fun fakeTimeOut(): String {
        TimeUnit.SECONDS.sleep(3)
        return "OK"
    }

    private fun fallbackTimeOut(): String{
        return "time out"
    }
}