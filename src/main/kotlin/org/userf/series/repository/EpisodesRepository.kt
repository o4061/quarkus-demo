package org.userf.series.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.userf.series.model.Episode
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class EpisodesRepository : PanacheRepository<Episode> {
    fun findBySerieId(serieId: Long): List<Episode> {
        return find("serieId", serieId).list()
    }
}