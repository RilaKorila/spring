package com.example.kotolinapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {

    @Autowired
    lateinit var userRepository: UserRepository;

    @GetMapping("/")
    fun index(model: Model):String{
        val users = userRepository.findAll()
        model.addAttribute("users", users)
        return "index"
//        mav.setViewName("index");
//        mav.addObject("title", "メインページ");
//        mav.addObject("users", userRepository.findAll());
//        return mav;
    }

}