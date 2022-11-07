package org.userf.series.model

import java.net.URL
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class TvSerie {
    @Id
    @Column(
        insertable = false,
        updatable = false,
    )

    val id: Long = -1
    val name: String = ""
    val url: URL? = null
    val status: String = ""
    val runtime: Int = -1

    @Column(length = 2500)
    val summary: String = ""
    val language: String = ""
    val premiered: LocalDate? = null
    val officialSite: URL? = null
//    val genres: List<String> = listOf()
//    var episodes: List<Episode>? = listOf()
}

