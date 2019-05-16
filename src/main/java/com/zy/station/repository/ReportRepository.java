package com.zy.station.repository;


import com.zy.station.dataobject.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,String>{
    List<Report> findByIdIn(List<Integer> idList);
    List<Report> findByDeviceIdIn(List<Integer> idList);
}
