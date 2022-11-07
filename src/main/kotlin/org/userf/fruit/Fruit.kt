package org.userf.fruit

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Cacheable
import javax.persistence.Column
import javax.persistence.Entity

@Entity
@Cacheable
class Fruit : PanacheEntity() {
    companion object : PanacheCompanion<Fruit> {
        fun findByName(name: String) = find("name", name).firstResult()
    }

    @Column(length = 40, unique = true)
    lateinit var name: String
}