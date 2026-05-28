package com.example.jackspeak.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jackspeak.entity.Reservation;
import com.example.jackspeak.service.ReservationService;

@Controller
public class YourLessonController {
	private final ReservationService reservationService;
	
	public YourLessonController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	// 予約したレッスン表示
	@GetMapping("/yourLessonList")
	public String yourLessonList(String name, 
									@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable, 
									Model model) {
		
		Page<Reservation> yourLessonList = reservationService.findReservationByNameOrderByLessonAsc(name, pageable);
		
		model.addAttribute("yourLessonList", yourLessonList);
		
		return "lesson/yourLessonList";
	}
	
	// キャンセル
	@PostMapping("/cancelReservation")
	public String cancel(@RequestParam Integer reservationId, 
						 RedirectAttributes redirectAttributes) {
		
		Optional<Reservation> reservationOptional = reservationService.findReservationById(reservationId);
		Reservation reservation = reservationOptional.get();
		
		reservationService.cancel(reservation);
		redirectAttributes.addFlashAttribute("successMessage", "予約をキャンセルしました。");
		
		
		return "redirect:/yourLessonList?name=" + reservation.getName();
	}

}
