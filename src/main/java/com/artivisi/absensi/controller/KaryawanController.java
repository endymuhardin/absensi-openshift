package com.artivisi.absensi.controller;

import com.artivisi.absensi.dao.KaryawanDao;
import com.artivisi.absensi.entity.Karyawan;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
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
    public void create(@RequestBody @Valid Karyawan k){
        karyawanDao.save(k);
    }
    
    @RequestMapping(value = "/karyawan/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid Karyawan k){
        karyawanDao.save(k);
    }
    
    @RequestMapping(value = "/karyawan/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapus(@PathVariable("id") String id){
        karyawanDao.delete(id);
    }
}
