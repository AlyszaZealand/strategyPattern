package com.example.strategypattern.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleBadRequestException(IllegalArgumentException ex, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("previousUrl", request.getHeader("Referer"));
        return "redirect:/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("previousUrl", request.getHeader("Referer"));
        return "redirect:/error";
    }

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        redirectAttributes.addFlashAttribute("previousUrl", request.getHeader("Referer"));
        return "redirect:/error";
    }

}
//--------------Url bliver ikke ændret til error med denne kode--------------------------------------------------------
//    @ExceptionHandler(ValidationException.class)
//    public ModelAndView handleValidationException(ValidationException ex, HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("errorMessage", ex.getMessage());
//        modelAndView.addObject("previousUrl", request.getHeader("Referer"));
//        return modelAndView;
//    }
//---------------------------------------------------------------------------------------------------------------------
