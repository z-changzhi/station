package com.zy.station.service;

import com.zy.station.dataobject.Station;

import java.util.List;

public interface StationService {
    /** 通过变电站id( 查找出他们的变电站 list*/
    Station findById(Integer id);
    /** 通过变电站id列表 查找出他们的变电站 list*/
    List<Station> findByIdIn(List<Integer> ideList);
    /** 通过省的名称 查找出他们的所有的变电站 list*/
    List<Station> findByRegionIn(List<String> ideList);
    /** 查询变电站列表. */
    List<Station> findAll();
    Station saveStation(Station stion);

}
