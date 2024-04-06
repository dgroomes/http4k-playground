package dgroomes

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.*
import org.http4k.server.ApacheServer
import org.http4k.server.asServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log: Logger = LoggerFactory.getLogger("dgroomes.main")

data class Message(val id: Int, val content: String)

val messages = mutableListOf<Message>()

/**
 * Please see the README for more information about this program.
 */
fun main() {
    val routes = routes(
        "/" bind Method.GET to ::rootHandler,
        "/messages" bind Method.GET to ::getAllMessages,
        "/messages" bind routes(

            // I can't get this to work. If I send a request with the "limit" query parameter it doesn't match this route.
            // Also, I thought that there is some precedence problem so I tried to swap the order of the routes but that
            // didn't have any effect.
            Method.GET.and(queries("limit")) bind ::getSomeMessages,

            Method.GET bind ::getAllMessages),
        "/message/{id}" bind Method.GET to ::getMessage,
        "/messages" bind Method.POST to ::createMessage
    )

    val server = routes.asServer(ApacheServer(8080))

    log.info("Starting the server...")
    server.start()
    log.info("The server has started. Send an HTTP request to it!")

    Runtime.getRuntime().addShutdownHook(Thread {
        log.info("Shutting down the server...")
        server.stop()
        log.info("The server is stopped. Goodbye.")
    })
}

fun rootHandler(request: Request): Response {
    val userAgent: String = request.header("user-agent") ?: "unknown"

    val msg = """
    Hello from an http4k program!
    
    This server is powered by Apache HttpComponents. You made a request from an HTTP client with a user agent value of: $userAgent
    """.trimIndent()

    return Response(OK).body(msg)
}

fun getAllMessages(@Suppress("UNUSED_PARAMETER") request: Request): Response {
    println("getAllMessages")
    val msg = messages.joinToString("\n") { it.content }
    return Response(OK).body(msg)
}

fun getSomeMessages(request: Request): Response {
    println("getSomeMessages")
    val limitStr: String = request.query("limit")
        ?: return Response(BAD_REQUEST).body("Expected a value for the 'limit' query parameter")

    val limit = limitStr.toIntOrNull()
        ?: return Response(BAD_REQUEST).body("The 'limit' query parameter must be an integer but was: '$limitStr'")

    val msg = messages.take(limit).joinToString("\n") { it.content }
    return Response(OK).body(msg)
}

fun getMessage(request: Request): Response {
    val idStr: String = request.path("id")
        ?: return Response(BAD_REQUEST).body("Expected a value for the 'id' path parameter")

    val id = idStr.toIntOrNull()
        ?: return Response(BAD_REQUEST).body("The 'id' path parameter must be an integer but was: '$idStr'")

    val message = messages.find { it.id == id }
    if (message == null) {
        return Response(NOT_FOUND).body("No message found for ID $id")
    }

    return Response(OK).body(message.content)
}

fun createMessage(request: Request): Response {
    val content = request.bodyString()
    val id = messages.size + 1
    messages.add(Message(id, content))
    return Response(OK).body("Message created (ID: $id)")
}

