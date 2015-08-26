package com.artivisi.absensi.dao;

import com.artivisi.absensi.entity.Karyawan;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface KaryawanDao extends PagingAndSortingRepository<Karyawan, String>{
    
}
