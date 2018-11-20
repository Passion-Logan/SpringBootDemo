package cn.com.scitc.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RootController {

    @RequestMapping("/index")
    public String test1() {

        return "index";
    }

}
