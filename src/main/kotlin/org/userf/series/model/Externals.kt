package org.userf.series.model

import javax.persistence.Embeddable

@Embeddable
class Externals {
    val tvrage: Long = -1
    val thetvdb: Long = -1
    val imdb: String = ""
}