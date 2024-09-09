package com.vincenzo.spring.microservice.controller;

public class Greeting {

    private Long counter;
    private String template;

    public Greeting(Long counter, String template) {
        this.counter = counter;
        this.template = template;
    }

    public Long getCounter() {
        return counter;
    }

    public String getTemplate() {
        return template;
    }
}
