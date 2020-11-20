package com.hotel.repository;

import com.hotel.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	Optional<Object> findByName(String hotelName);

	boolean existsByName(String name);
}
