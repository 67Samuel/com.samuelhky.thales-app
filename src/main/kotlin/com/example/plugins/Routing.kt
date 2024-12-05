package com.example.plugins

import com.example.repositories.ProductRepository
import io.ktor.http.*
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.utils.io.readRemaining
import kotlinx.io.IOException
import kotlinx.io.readByteArray
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/products") {
            val products = ProductRepository.generateListOfProducts()
            call.respond(products)
        }

        get("/image/{id}") {
            try {
                val imageId = call.parameters["id"]
                val file = File("src/main/resources/static/product$imageId.png")
                if (file.exists()) {
                    call.respondFile(file)
                }
                else call.respond(HttpStatusCode.NotFound)
            } catch (e: IOException) {
                log.error(e.message)
                call.respond(HttpStatusCode.NotFound)
            } catch (e: Exception) {
                log.error(e.message)
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        post("/product") {
            var fileDescription = ""
            var fileName = ""

//            val contentLength = call.request.headers[HttpHeaders.ContentLength]
//            log.debug("contentLength=$contentLength")

            val multipartData = call.receiveMultipart()

            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        fileDescription = part.value
                        log.debug("fileDesc=$fileDescription")
                    }

                    is PartData.FileItem -> {
//                        fileName = "product${ProductRepository.runningImageId++}"
                        fileName = buildString {
                            append("product")
                            append(ProductRepository.runningImageId++)
                            append(".png")
                        }
                        val file = File("static/$fileName")
                        file.createNewFile()
                        val fileBytes = part.provider().readRemaining().readByteArray()
                        file.writeBytes(fileBytes)
                    }

                    else -> {}
                }
                part.dispose()
            }

            call.respondText("$fileDescription is uploaded")
        }
    }
}
