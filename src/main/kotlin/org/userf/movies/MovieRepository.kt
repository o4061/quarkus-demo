package org.userf.movies

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MovieRepository: PanacheRepository<Movie> {
    fun findByCountry(country: String?): List<Movie?>? {
        return list("SELECT m FROM Movie m WHERE m.country = ?1 ORDER BY id DESC", country!!)
    }
}