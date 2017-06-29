package com.travelfactory.test.servises;

import com.travelfactory.test.domain.Booking;
import com.travelfactory.test.persistence.BookingRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository repository;

    @Autowired
    private ReaderService readerService;

    public void readAndSave(@NonNull String vendor, @NonNull String date) {
        String fileName = vendor + "-" + date + ".csv";
        readerService.csvReader(fileName).forEach(b -> repository.save((Booking) b));
    }

    public Booking getBooking(Integer id) {
        Optional<Booking> result = Optional.ofNullable(repository.findOne(id));
        return result.orElseThrow(() -> new NullPointerException("Booking not found"));
    }
}
