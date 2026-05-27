package com.example.jackspeak.form;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LessonRegisterForm {
	
	@NotNull(message = "日付を選択してください。")
	private LocalDate lessonDate;
	
	@NotNull(message = "時間を選択してください。")
	private LocalTime lessonTime;
	
	@NotNull(message = "クラスを入力してください。")
	private String className;
	
	@NotNull(message = "レベルを入力してください。")
	private String level;
	
	@NotNull(message = "人数を入力してください。")
	private Integer capacity;
	
//	@NotNull(message = "予約済み人数を入力してください。")
	private Integer reservedCount = 0;
	
	@NotNull(message = "passwordを入力してください。")
	private String password;

}
