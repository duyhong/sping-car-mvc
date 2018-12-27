package com.spring.web.mvc.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author Duy
 *This is global Exception handler!
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public String handleGeneralError(Exception ex, HttpServletRequest request, Model model) {
		model.addAttribute("cause", ex.getMessage());
		model.addAttribute("requestedURI", request.getRequestURI());
		return "error";
	}

}
