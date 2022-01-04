package com.example.tasks.rest_controller;

import com.example.tasks.service.DateTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/startTimer")
final class DateTimeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeController.class);

    private DateTimeService dateTimeService;

    DateTimeController(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public void processDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOGGER.info("Processing date: {}", date);
        dateTimeService.processDate(date);
    }

    @RequestMapping(value = "/datetime", method = RequestMethod.POST)
    public void processDateTime(@RequestParam("datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateAndTime) {
        LOGGER.info("Processing date and time: {}", dateAndTime);
        dateTimeService.processDateAndTime(dateAndTime);
    }
}