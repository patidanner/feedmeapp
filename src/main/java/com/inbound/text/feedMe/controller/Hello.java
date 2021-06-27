package com.inbound.text.feedMe.controller;

import com.inbound.text.feedMe.model.Staging;
import com.inbound.text.feedMe.service.StagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class Hello {

    @Autowired
    private StagingService readFromFile;

    @GetMapping("/feedme")
    public Iterable<Staging> readAll(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException, ParseException {
        return readFromFile.readAll();
    }
}
