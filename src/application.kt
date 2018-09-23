package com.example

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.gson.gson

fun main(args: Array<String>): Unit = io.ktor.server.netty.DevelopmentEngine.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    val client = HttpClient(Apache) {
    }

    install(io.ktor.features.ContentNegotiation){
        gson{
        }
    }

    val example = SongList().apply {makeList()}

    routing {
        get ("/list"){
            call.respond(example.songs)
        }
    }
}