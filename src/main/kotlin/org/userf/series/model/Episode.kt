package org.userf.series.model

import javax.persistence.*

@Entity
class Episode {
    @Id
    @GeneratedValue
    var episodeId: Long = -1
    var serieId: Long = -1
    var name: String = ""
    var season: Long = -1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private val tvSerie: TvSerie? = null
}
