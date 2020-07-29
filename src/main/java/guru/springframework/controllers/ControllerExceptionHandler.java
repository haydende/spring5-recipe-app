package guru.springframework.controllers;

import exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumFormat(Exception e) {
        log.error("Handling number format exception");
        ModelAndView mav = new ModelAndView("400error");
        mav.addObject("exception", e);
        return mav;
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling not found exception");
        ModelAndView mav = new ModelAndView("404error");
        mav.addObject("exception", e);
        log.error(e.getLocalizedMessage());
        return mav;
    }
}
