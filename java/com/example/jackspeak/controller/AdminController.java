package com.example.jackspeak.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jackspeak.entity.Lesson;
import com.example.jackspeak.form.LessonRegisterForm;
import com.example.jackspeak.service.LessonService;

@Controller
public class AdminController {
	private final LessonService lessonSer;
	
	public AdminController(LessonService lessonService) {
		this.lessonSer = lessonService;
	}
	
	// あきレッスン登録（管理者）// 登録した日時をカレンダーに渡す
		@GetMapping("/admin/register")
		public String register(Model model) {
			
			List<Lesson> lessons = lessonSer.findAllLessons();
			
			model.addAttribute("lessonRegisterForm", new LessonRegisterForm());
			model.addAttribute("lessons", lessons);
			
			return "admin/lesson-register";
		}
		
		@PostMapping("/admin/register")
		public String lessonRegister(@ModelAttribute @Validated LessonRegisterForm form,
									 BindingResult result,
									 RedirectAttributes redirectAttributes,
									 Model model) {
			
//			System.out.println(form);
//			System.out.println(lessonSer.checkPassword(form));
			
			if (lessonSer.checkPassword(form) == false) {
				redirectAttributes.addFlashAttribute("errorMessage", "パスワードが違います。");
				return "redirect:/admin/register";
			}
			
			if (result.hasErrors()) {
				model.addAttribute("lessonRegisterForm", form);
				result.getAllErrors().forEach(error -> System.out.println(error));
				return "admin/lesson-register";
			}
			
			lessonSer.createLesson(form);
			redirectAttributes.addFlashAttribute("successMessage", "登録しました！");
			
			return "redirect:/admin/register";
		}
		

}
