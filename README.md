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
     
     This server is powered by Apache HttpComponents. You made a request from an HTTP client with a user agent value of: curl/8.4.0
     ```
5. Try these other requests and see what you get:
   * ```shell
     curl http://localhost:8080/message/1
     ```
   * This will return a 404 response because there are no messages in the system. Let's create some messages.
   * ```shell
     curl -X POST -d 'Hello, world!' http://localhost:8080/messages
     curl -X POST -d 'Hello, world, again!' http://localhost:8080/messages
     curl -X POST -d 'Hello, world, one more time!' http://localhost:8080/messages
     ```
   * ```shell
     curl http://localhost:8080/messages
     ```
   * This will list all the messages. Now, let's limit the messages to just the first two.
   * ```shell
     curl http://localhost:8080/messages?limit=2
     ```
   * Now, let's just review the second message alone.
   * ```shell
     curl http://localhost:8080/messages/2
     ```
6. Stop the server
   * Issue a `SIGINT` signal using the `Ctrl + C` keyboard shortcut from the same shell you started the program.
   * Altogether, it should look like this from start to stop:
     ```text
     $ ./build/install/http4k-playground/bin/http4k-playground
     18:35:37 INFO main - Starting the server...
     18:35:37 INFO main - The server has started. Send an HTTP request to it!
     ^C18:36:08 INFO main - Shutting down the server...
     18:36:08 INFO main - The server is stopped. Goodbye.
     ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Do something with path variables, query parameters and content negotiation. These use-cases are why we want
  routing.
* [ ] SKIP (no. now we're in the business of control flow via runtime exceptions. This is no longer "functional". If I
  write my own handler code then I still get strong types, plus we get intentional error messaging) Look at the "lens" feature


## Reference

* [htt4pk website](https://www.http4k.org/)
* [htt4pk GitHub repo](](https://github.com/http4k/http4k))
* [GitHub issue #77 in the http4k repo](https://github.com/http4k/http4k/issues/77)
  * The conversation in this thread, although 5 years old, is still relevant. The http4k authors did not build the
    project for Java programs. As such, although you should be able to technically use http4k from a Java program, it
    might not be a pleasant experience. I have authored this playground repo in Kotlin for this reason. 
