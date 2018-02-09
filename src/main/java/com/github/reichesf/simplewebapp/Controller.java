package com.github.reichesf.simplewebapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public final class Controller
{
    @RequestMapping("/")
    public String index()
    {
        return "Greetings from Spring Boot!";
    }

    // Illustrates the following:
    // - Exact specification of the method (GET) on the Request Mapping.
    // - Multiple "produces" on the RequestMapping to handle both JSON and XML.
    // - Use of the ResponseEntity when producing a response.

    @RequestMapping(value = "/greeting", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
    public ResponseEntity<Greeting> greeting(@RequestParam(value="name", defaultValue="World") String sName)
    {
        Greeting greeting = new Greeting();

        greeting.setId(nCounter.incrementAndGet());
        greeting.setMessage(String.format(sTemplate, sName));

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

    private static final String sTemplate = "Hello, %s!";
    private final AtomicLong nCounter = new AtomicLong();
}