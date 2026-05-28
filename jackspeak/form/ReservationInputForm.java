package com.example.jackspeak.form;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	
	// これが最重要
    @NotNull
    private Integer lessonId;
	
	@NotNull(message = "予約日を選択してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@NotNull(message = "時間を選択してください。")
	private LocalTime time;
	
	@NotBlank(message = "名前を入力してください。")
	private String name;
	
	private String studentName;
	
	@NotNull(message = "年齢を入力してください。")
	@Max(value = 120, message = "年齢を確認してください")
	private Integer age;
	
	@NotNull(message = "年数を入力してください。")
	private String learningHistory;
	
	private String request;

}
