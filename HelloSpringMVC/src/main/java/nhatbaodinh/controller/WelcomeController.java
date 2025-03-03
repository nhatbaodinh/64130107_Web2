package nhatbaodinh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
    @RequestMapping("/hello")
    public ModelAndView getPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        return mv;
    }
}
