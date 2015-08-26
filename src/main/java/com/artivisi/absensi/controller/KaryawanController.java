package com.artivisi.absensi.controller;

import com.artivisi.absensi.dao.KaryawanDao;
import com.artivisi.absensi.entity.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KaryawanController {
    @Autowired
    private KaryawanDao karyawanDao;
    
    @RequestMapping("/karyawan/list")
    public Page<Karyawan> semua(Pageable page){
        return karyawanDao.findAll(page);
    }
    
    @RequestMapping(value = "/karyawan/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Karyawan k){
        karyawanDao.save(k);
    }
}
