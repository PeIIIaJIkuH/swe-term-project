package com.hotel.repository;

import com.hotel.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
	boolean existsByName(String name);

	Season findByName(String season);
}
