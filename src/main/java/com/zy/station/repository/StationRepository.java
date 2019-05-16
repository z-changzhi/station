package com.zy.station.repository;


import com.zy.station.dataobject.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station,String>{
    Station findById(Integer id);
    List<Station> findByIdIn(List<Integer> idList);
    List<Station> findByRegionIn(List<String> idList);
}
