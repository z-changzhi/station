package com.zy.station.repository;


import com.zy.station.dataobject.Collector;
import com.zy.station.dataobject.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectorRepository extends JpaRepository<Collector,String>{
    List<Collector> findByIdIn(List<Integer> idList);
    Collector findByStationIdIn(Integer stationId);
}