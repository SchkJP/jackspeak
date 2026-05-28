package com.example.jackspeak.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jackspeak.entity.Lesson;
import com.example.jackspeak.entity.Reservation;
import com.example.jackspeak.form.ReservationInputForm;
import com.example.jackspeak.repository.LessonRepository;
import com.example.jackspeak.repository.ReservationRepository;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final LessonRepository lessonRepo;

	public ReservationService(ReservationRepository reservationRepository,
							  LessonRepository lessonRepository){
		this.reservationRepository = reservationRepository;
		this.lessonRepo = lessonRepository;
	}
	
//	//予約がかぶっていないかチェック
//	public boolean lessonAlreadyBooked(LocalDate date, LocalTime time) {
//		LocalDateTime dateTime = LocalDateTime.of(date, time);
//		return reservationRepository.existsByDateTime(dateTime);
//	}
	
	
	
	public Optional<Reservation> findReservationById(Integer id) {
		return reservationRepository.findById(id);
	}
	
	public Lesson findLessonById(Integer id) {
		return lessonRepo.findById(id).orElseThrow();
	}
	
	@Transactional
	public void createReservation(Integer lessonId, 
								  ReservationInputForm reservationInputForm) {
		// ロックして取得
		Lesson lesson = lessonRepo.findByIdForUpdate(lessonId);
		// 満席チェック
		if(lesson.getReservedCount() >= lesson.getCapacity()) {
			throw new RuntimeException("満席です");
		}
		
		// 予約作成
		Reservation reservation = new Reservation();
		reservation.setLesson(lesson);
//		LocalDateTime datetime = LocalDateTime.of(
//				reservationInputForm.getDate(), 
//				reservationInputForm.getTime());
		reservation.setDateTime(lesson.getLessonDate());
//		reservation.setLessonId(lessonId);
		reservation.setName(reservationInputForm.getName());
		reservation.setAge(reservationInputForm.getAge());
		reservation.setLearningHistory(reservationInputForm.getLearningHistory());
		reservation.setRequest(reservationInputForm.getRequest());
//		reservation.setCreatedAt(LocalDateTime.now());
		reservation.setStudentName(reservationInputForm.getStudentName());
		
		if (reservationInputForm.getStudentName() == null 
				|| reservationInputForm.getStudentName().isBlank()) {
			
			reservation.setStudentName(reservationInputForm.getName());
		}
		
		reservationRepository.save(reservation);
		// 人数更新
		lesson.setReservedCount(lesson.getReservedCount() + 1 );
	}
	
	public Page<Reservation> findReservationByNameOrderByLessonAsc(String name,
																	Pageable pageable){
		return reservationRepository.findByNameOrderByLessonAsc(name, pageable);
	}
	
	
	
	//　予約キャンセル
	@Transactional
	public void cancel(Reservation reservation) {
		
		Lesson lesson = reservation.getLesson();
		
		reservationRepository.delete(reservation);
		
		lesson.setReservedCount(lesson.getReservedCount() - 1);
	}

}
