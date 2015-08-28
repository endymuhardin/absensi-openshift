package com.artivisi.absensi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HaloController {

    @RequestMapping("/halo")
    public Map<String, String> halo() {
        Map<String, String> data = new HashMap<>();
        data.put("waktu", new Date().toString());
        return data;
    }
}
