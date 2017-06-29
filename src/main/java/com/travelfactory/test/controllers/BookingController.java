package com.travelfactory.test.controllers;

import com.travelfactory.test.domain.Booking;
import com.travelfactory.test.servises.BookingService;
import com.travelfactory.test.servises.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/catalog/{vendor}/{date}/{id}", method = RequestMethod.GET)
    public Booking getBooking(@PathVariable String vendor, @PathVariable String date, @PathVariable Integer id) {

        bookingService.readAndSave(vendor, date);

        return bookingService.getBooking(id);
    }

}
