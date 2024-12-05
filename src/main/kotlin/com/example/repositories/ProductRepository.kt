package com.example.repositories

import com.example.models.Product
import com.example.models.ProductType
import kotlinx.io.IOException
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.InputStream
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.uuid.ExperimentalUuidApi

object ProductRepository {

    var runningProductId = 0
    var runningImageId = 0

    @OptIn(ExperimentalUuidApi::class)
    fun generateListOfProducts(): List<Product> {
        return listOf(
            Product(
                id = runningProductId++,
                name = "Product1",
                type = ProductType.Type1,
                imageId = runningImageId++,
                price = 2.6,
                description = "Product1 description"
            ),
            Product(
                id = runningProductId++,
                name = "Product2",
                type = ProductType.Type2,
                imageId = runningImageId++,
                price = 4.2,
                description = "Product2 description"
            ),
            Product(
                id = runningProductId++,
                name = "Product3",
                type = ProductType.Type3,
                imageId = runningImageId++,
                price = 1.5,
                description = "Product3 description"
            ),
            Product(
                id = runningProductId++,
                name = "Product4",
                type = ProductType.Type1,
                imageId = runningImageId++,
                price = 2.6,
                description = "Product4 description"
            )
        )
    }

//    @OptIn(ExperimentalEncodingApi::class)
//    private fun InputStream.base64Encoding(): String {
//        var bytesRead: Int
//        val bytes: ByteArray
//        val buffer = ByteArray(8192)
//        val output = ByteArrayOutputStream()
//
//        try {
//            bytesRead = this.read(buffer)
//            while (bytesRead != -1) {
//                output.write(buffer, 0, bytesRead)
//                bytesRead = this.read(buffer)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        bytes = output.toByteArray()
//        return Base64.encodeToByteArray(bytes).toString()
//    }

}