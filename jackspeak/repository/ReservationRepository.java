package com.example.jackspeak.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jackspeak.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	public Page<Reservation> findByNameOrderByLessonAsc(String name, Pageable pageable);

//	public boolean existsByDateTime(LocalDateTime dateTime);

}
