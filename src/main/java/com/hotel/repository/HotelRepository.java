package com.hotel.repository;

import com.hotel.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Object> findByName(String hotelName);
}
