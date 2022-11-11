package org.userf.series.model

import java.net.URL
import java.time.LocalDate
import javax.persistence.*

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
    val externals: Externals? = null

    @ElementCollection
    val genres: List<String>? = null
    val schedule: Schedule? = null
}

