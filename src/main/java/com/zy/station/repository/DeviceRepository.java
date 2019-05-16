package com.zy.station.repository;


import com.zy.station.dataobject.Device;
import com.zy.station.dataobject.Report;
import com.zy.station.dataobject.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,String>{
    List<Device> findByIdIn(List<Integer> idList);
    List<Device> findByStationIdIn(List<Integer> idList);

}