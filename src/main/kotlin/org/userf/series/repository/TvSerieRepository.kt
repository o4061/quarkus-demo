package org.userf.series.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.userf.series.model.TvSerie
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TvSerieRepository : PanacheRepository<TvSerie> {
    fun getByName(name: String): TvSerie? {
        return find("name", name).firstResult()
    }
}