package dgroomes

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.ApacheServer
import org.http4k.server.asServer
import org.slf4j.LoggerFactory

val log = LoggerFactory.getLogger("dgroomes.main")

/**
 * Please see the README for more information about this program.
 */
fun main() {
    val server = ::requestHandler.asServer(ApacheServer(8080))

    log.info("Starting the server...")
    server.start()
    log.info("The server has started. Send an HTTP request to it!")

    Runtime.getRuntime().addShutdownHook(Thread {
        log.info("Shutting down the server...")
        server.stop()
        log.info("The server is stopped. Goodbye.")
    })
}

fun requestHandler(request: Request): Response {
    val userAgent: String = request.header("user-agent") ?: "unknown"

    val msg = """
    Hello from an http4k program!
    
    This server is powered by Apache HttpComponents. You made a request from an HTTP client with a user agent value of: $userAgent
    """.trimIndent()

    return Response(OK).body(msg)
}

