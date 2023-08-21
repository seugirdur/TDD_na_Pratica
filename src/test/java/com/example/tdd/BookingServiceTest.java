package com.example.tdd;


import com.example.tdd.model.BookingModel;
import com.example.tdd.repository.BookingRepository;
import com.example.tdd.service.BookingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BookingServiceTest {

    @TestConfiguration
    static class BookingServiceTestConfiguration {

        @Bean
        public BookingService bookingService() {
            return new BookingService();
        }
    }
    @Autowired
    BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void BookingTestServiceDaysCalculator() {
        String name = "Gabriel";
        int days = bookingService.daysCalculatorWithDatabase(name);

        Assert.assertEquals(10, days);
    }

    @Before
    public void setup() {
        LocalDate checkIn = LocalDate.parse("2023-07-11");
        LocalDate checkOut = LocalDate.parse("2023-07-16");

        BookingModel bookingModel = new BookingModel("1", "Gabriel", checkIn, checkOut, 2);

        Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName()))
                .thenReturn(Optional.of(bookingModel));
    }
}

