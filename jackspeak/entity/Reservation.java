package com.example.jackspeak.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
	//  @Idがなくてエラー出たため追加
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;
//	 @Column(name = "lesson_id")
//	 private Integer lessonId;
	
	@Column(name = "date")
	private LocalDateTime dateTime;   //　日時はlessonが持ってるため不要
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "learning_history")
	private String learningHistory;
	
	@Column(name = "request")
	private String request;
	
//	@Column(name = "created_at")
//	private LocalDateTime createdAt;

}
