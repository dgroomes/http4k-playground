# http4k-playground

📚 Learning and exploring http4.

> The Functional toolkit for Kotlin HTTP applications
> 
> --<cite>https://www.http4k.org</cite>


## Overview

http4k offers an ergonomic routing API for HTTP-based applications. This repository is me exploring it. 


## Instructions

Follow these instructions to build and run the demo program.

1. Pre-requisite: Java
   *  I used Java 21
2. Build the program distribution:
   * ```shell
     ./gradlew installDist
     ```
3. Start the server:
   * ```shell
     ./build/install/http4k-playground/bin/http4k-playground
     ```
4. Make a sample request:
   * ```shell
     curl http://localhost:8080
     ```
   * It should look like this:
     ```text
     $ curl http://localhost:8080
     Hello from an http4k program!
     
     This server is running on a Netty server. You made a request from an HTTP client with a user agent value of: curl/7.84.0
     ```
5. Stop the server
   * Issue a `SIGINT` signal using the `Ctrl + C` keyboard shortcut from the same shell you started the program.
   * Altogether, it should look like this from start to stop:
     ```text
     $ ./build/install/http4k-playground/bin/http4k-playground
     21:46:40 INFO main - Starting the server...
     21:46:40 INFO main - The server has started. Send an HTTP request to it!
     ^C21:46:54 INFO main - Shutting down the server...
     21:46:58 INFO main - The server is stopped. Goodbye.
     ```


## Reference

* [htt4pk website](https://www.http4k.org/)
* [htt4pk GitHub repo](](https://github.com/http4k/http4k))
* [GitHub issue #77 in the http4k repo](https://github.com/http4k/http4k/issues/77)
  * The conversation in this thread, although 5 years old, is still relevant. The http4k authors did not build the
    project for Java programs. As such, although you should be able to technically use http4k from a Java program, it
    might not be a pleasant experience. I have authored this playground repo in Kotlin for this reason. 
