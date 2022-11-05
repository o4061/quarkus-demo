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
        val fruits = Fruit.findById(id)
        return if (fruits != null) Response.ok(fruits).build() else
            Response.status(Response.Status.NOT_FOUND).build()
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun create(fruit: Fruit): Response {
        if (fruit.id != null) {
            throw WebApplicationException("Id was invalidly set on request.", 422)
        }
        fruit.persist()
        return Response.ok(fruit).status(201).build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun update(@PathParam("id") id: Long, fruit: Fruit): Fruit {
        val entity: Fruit = Fruit.findById(id)
            ?: throw WebApplicationException("Fruit with id of $id does not exist.", 404)
        entity.name = fruit.name
        return entity
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun delete(@PathParam("id") id: Long): Response {
        val entity: Fruit = Fruit.findById(id)
            ?: throw WebApplicationException("Fruit with id of $id does not exist.", 404)
        entity.delete()
        return Response.status(204).build()
    }
}