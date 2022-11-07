package org.userf.movies

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Movie {
    @Id
    @GeneratedValue
    val id: Long = -1
    @Column(length = 100)
    val title: String = ""
    @Column(length = 200)
    val description: String = ""
}