package org.userf.series.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL
import java.time.LocalDate
import javax.persistence.*

@Entity
class TvSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        insertable = false,
        updatable = false,
    )
    val serieId: Long = -1

    val id: Long = -1
    var name: String = ""
    val url: URL? = null
    val status: String = ""
    val runtime: Int = -1

    @Column(length = 2500)
    val summary: String = ""
    val language: String = ""
    val premiered: LocalDate? = null
    val officialSite: URL? = null
    val externals: Externals? = null

    @ElementCollection
    val genres: List<String>? = null

    @JsonProperty("schedule")
    val mySchedule: Schedule? = null

    @OneToMany(mappedBy = "tvSerie", orphanRemoval = true)
    var episode: List<Episode>? = null
}

