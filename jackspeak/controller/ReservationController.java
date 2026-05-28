package com.example.jackspeak.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jackspeak.entity.Lesson;
import com.example.jackspeak.form.ReservationInputForm;
import com.example.jackspeak.service.LessonService;
import com.example.jackspeak.service.ReservationService;

@Controller
public class ReservationController {
	private final ReservationService reservationService;
	private final LessonService lessonSer;
	
	public ReservationController(ReservationService reservationService, 
									LessonService lessonService) {
		this.reservationService = reservationService;
		this.lessonSer = lessonService;
	}
	
	@GetMapping("/lessons")
	public String show(Model model) {
		List<Lesson> lessons = lessonSer.findAllLessons();
		
		model.addAttribute("lessons", lessons);
		
		return "lesson/index";
	}
	
	@GetMapping("/reservation")
	public String reservationForm(@RequestParam("lessonId") Integer id, Model model) {
		
		Lesson lesson = reservationService.findLessonById(id);
		LocalDate lessonDate = lesson.getLessonDate().toLocalDate();
		LocalTime lessonTime = lesson.getLessonDate().toLocalTime();
		
		ReservationInputForm form = new ReservationInputForm();
		form.setDate(lessonDate);
		form.setTime(lessonTime);
		form.setLessonId(id);
		
		model.addAttribute("reservationInputForm", form);
		model.addAttribute("lesson", lesson);
		
		return "lesson/reservation";
	}
	
	@PostMapping("/reservation")
	public String makeReservation(@RequestParam Integer lessonId, 
								  @ModelAttribute @Validated ReservationInputForm reservationInputForm, 
								  BindingResult bindingResult,
								  @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable,
								  RedirectAttributes redirectAttributes, Model model) {
		
//		if (reservationService.lessonAlreadyBooked(reservationInputForm.getDate(), 
//													reservationInputForm.getTime())) {
//			
//			return "redirect:/reservation";
//		}
//		System.out.println(reservationInputForm.getDate());
//		System.out.println(reservationInputForm.getTime());
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("reservationInputForm", reservationInputForm);
			
			return "lesson/reservation";
		}
		
		reservationService.createReservation(lessonId, reservationInputForm);
		redirectAttributes.addFlashAttribute("successMessage", "予約が完了しました！");
		
		return "redirect:/yourLessonList?name=" + reservationInputForm.getName();
	}
	

	

}
