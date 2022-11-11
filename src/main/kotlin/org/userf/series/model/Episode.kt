package org.userf.series.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Episode {
    @Id
    val id: Long = -1
    val name: String = ""
    val season: Long = -1
    val summary: String = ""
}
