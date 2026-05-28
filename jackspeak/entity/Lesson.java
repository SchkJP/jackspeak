package com.example.jackspeak.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lessons")
@Data
public class Lesson {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
	private Integer lessonId;
	
	@Column(name = "lesson_date")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime lessonDate;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "capacity")
	private Integer capacity;
	
	@Column(name = "reserved_count")
	private Integer reservedCount;
	
//	// 楽観ロック（超重要）
//	@Version
//	private Integer version;
	
	// 残席はDBに持たない
	public int getRestNumber() {
		return capacity - reservedCount;
	}

}
