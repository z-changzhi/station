package com.zy.station.service;

import com.zy.station.dataobject.Collector;
import com.zy.station.dataobject.Device;

import java.util.List;

public interface CollectorService {
    /** 通过stationId查询Ip. */
    Collector findByStationId(Integer stationid);
    /** 通过stationId存储采集端数据. */
    Collector saveByStationId(Collector collector);
    List<Device> findAll();
}
