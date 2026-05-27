package com.example.jackspeak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jackspeak.entity.Lesson;
import com.example.jackspeak.form.LessonRegisterForm;
import com.example.jackspeak.repository.LessonRepository;

@Service
public class LessonService {
	
	private final LessonRepository lessonRepo;
	
	public LessonService(LessonRepository lessonR) {
		this.lessonRepo = lessonR;
	}
	
	@Value("${adminPassword}")
	private String password;
	
	// DB lessons取得
	public List<Lesson> findAllLessons(){
		return lessonRepo.findAll();
	}
	
	// password確認
	public boolean checkPassword(LessonRegisterForm lessonRegisterForm) {
		return lessonRegisterForm.getPassword().equals(password);
	}
	
	@Transactional
	public void createLesson(LessonRegisterForm lessonRegisterForm) {
		
		Lesson lesson = new Lesson();
		
		LocalDateTime dateTime = LocalDateTime.of(lessonRegisterForm.getLessonDate(), 
								 				  lessonRegisterForm.getLessonTime());
		
		lesson.setLessonDate(dateTime);
		lesson.setClassName(lessonRegisterForm.getClassName());
		lesson.setLevel(lessonRegisterForm.getLevel());
		lesson.setCapacity(lessonRegisterForm.getCapacity());
		lesson.setReservedCount(lessonRegisterForm.getReservedCount());
		
		lessonRepo.save(lesson);
		
	}

}
