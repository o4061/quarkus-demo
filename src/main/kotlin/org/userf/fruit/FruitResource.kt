package org.userf.fruit

import io.quarkus.panache.common.Sort
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("fruits")
@ApplicationScoped

class FruitResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun get(): List<Fruit> {
        return Fruit.listAll(Sort.by("name"))
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getSingle(@PathParam("id") id: Long): Response {
        val fruit = Fruit.findById(id)
        return if (fruit != null) Response.ok(fruit).build() else
            Response.status(Response.Status.NOT_FOUND).build()
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun create(fruit: Fruit): Response {
        return if (Fruit.findByName(fruit.name) != null) {
            Response.status(Response.Status.BAD_REQUEST).build()
        } else {
            fruit.persist()
            Response.ok(fruit).status(201).build()
        }
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun update(@PathParam("id") id: Long, fruit: Fruit): Response {
        val newFruit = Fruit.findById(id) ?: return Response.status(Response.Status.NOT_FOUND).build()
        newFruit.name = fruit.name
        return Response.ok(newFruit).build()
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun delete(@PathParam("id") id: Long): Response {
        return if (Fruit.deleteById(id)) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.BAD_REQUEST).build()
        }
    }
}