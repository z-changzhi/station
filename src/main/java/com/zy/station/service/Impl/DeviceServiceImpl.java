package com.zy.station.service.Impl;

import com.zy.station.dataobject.Device;
import com.zy.station.repository.DeviceRepository;
import com.zy.station.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> findByIdIn(List<Integer> ideList) {
        return deviceRepository.findByIdIn(ideList);
    }

    @Override
    public List<Device> findByStationIdIn(List<Integer> ideList) {
        return deviceRepository.findByStationIdIn(ideList);
    }

    @Override
    public List<Device> findAll() {
        List<Device> all = deviceRepository.findAll();
        return all;
    }
}
