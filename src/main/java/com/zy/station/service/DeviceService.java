package com.zy.station.service;

import com.zy.station.dataobject.Device;

import java.util.List;

public interface DeviceService {
    Device saveDevice(Device device);
    List<Device> findByIdIn(List<Integer> ideList);
    List<Device> findByStationIdIn(List<Integer> ideList);
    /** 查询变电站列表. */
    List<Device> findAll();

}
