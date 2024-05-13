package com.rest.webservices.restfulwebservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    /* Versioning using URL */

    @GetMapping("v1/person")
    public PersonV1 getFirstNameOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("v2/person")
    public PersonV2 getFirstLastNameOfPerson() {
        return new PersonV2("Bob", "Charlie");
    }

    /* Versioning using Request Parameters */

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstNameOfPersonRequestParameter() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getFirstLastNameOfPersonRequestParameter() {
        return new PersonV2("Bob", "Charlie");
    }

    /* Versioning using Headers */

    @GetMapping(path = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getFirstNameOfPersonRequestHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getFirstLastNameOfPersonRequestHeader() {
        return new PersonV2("Bob", "Charlie");
    }

    /* Versioning using Content Negotiation */

    @GetMapping(path = "/person", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstNameOfPersonAcceptHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getFirstLastNameOfPersonAcceptHeader() {
        return new PersonV2("Bob", "Charlie");
    }
}
