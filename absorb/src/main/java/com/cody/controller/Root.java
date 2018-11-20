package com.cody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Cody_
 * @date 18/9/18
 */
@Controller
public class Root {

    @RequestMapping(value = "/index")
    public String root() {
        return "index";
    }

}
