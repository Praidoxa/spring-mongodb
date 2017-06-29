package com.travelfactory.test.servises;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.travelfactory.test.domain.Booking;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

@Service
public class ReaderService {

    public List csvReader(@NonNull String fileName) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.typedSchemaFor(Booking.class).withHeader();
        List list = null;
        try {
            list = new CsvMapper().readerFor(Booking.class)
                    .with(csvSchema.withColumnSeparator(CsvSchema.DEFAULT_COLUMN_SEPARATOR))
                    .readValues(ReaderService.class.getClassLoader().getResource(fileName))
                    .readAll();
        } catch (Throwable e) {
            throw new NullPointerException(fileName + " not found");
        }
        return list;
    }
}
