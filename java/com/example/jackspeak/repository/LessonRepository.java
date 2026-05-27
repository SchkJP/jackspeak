package com.example.jackspeak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jackspeak.entity.Lesson;

import jakarta.persistence.LockModeType;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
	// 悲観ロック
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT l FROM Lesson l WHERE l.lessonId = :id")
    Lesson findByIdForUpdate(@Param("id") Integer id);

}
