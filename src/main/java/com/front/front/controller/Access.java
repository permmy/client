package com.front.front.controller;


import ch.qos.logback.core.net.server.Client;
import org.springframework.boot.autoconfigure.elasticsearch.jest.HttpClientConfigBuilderCustomizer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Access {

    @GetMapping(value = "/")
    public ModelAndView index(@RequestParam (required = false) String res)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("test",res);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping(value = "/get/results")
    public ModelAndView results(@RequestParam (required = false) String test, RedirectAttributes redirectAttributes)
    {
        ModelAndView modelAndView = new ModelAndView();
        String result="";
        RestTemplate restTemplate = new RestTemplate();

        if(test.isEmpty())
        {
            result = restTemplate.getForObject("http://localhost:9000/get/results",String.class);
        }else
        {
            result = restTemplate.getForObject("http://localhost:9000/get/results?test="+test,String.class);
        }

        if (result != null) {
            redirectAttributes.addAttribute("res",result);
        }
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }
}
