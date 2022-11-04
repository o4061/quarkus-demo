package org.userf.series.model

import java.net.URL

data class TvSerie(
    val id: Long,
    val name: String,
    val url: URL,
    val summary: String,
    val language: String,
    val genres: Set<String>,
    val officialSite: URL,
    var episodes: List<Episode>?
)
