package com.artivisi.absensi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HaloController {

    @RequestMapping("/halo")
    public Map<String, String> halo() {
        Map<String, String> data = new HashMap<>();
        data.put("waktu", new Date().toString());
        return data;
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public void loginForm(){
        
    }
}
