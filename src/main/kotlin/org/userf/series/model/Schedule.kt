package org.userf.series.model

import javax.persistence.Embeddable

@Embeddable
class Schedule {
    val time: String = ""
    val days: ArrayList<String>? = null
}