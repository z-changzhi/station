package com.zy.station.service.Impl;

import com.zy.station.dataobject.Collector;
import com.zy.station.dataobject.Device;
import com.zy.station.repository.CollectorRepository;
import com.zy.station.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectorServiceImpl implements CollectorService {
    @Autowired
    private CollectorRepository collectorRepository;

    @Override
    public Collector findByStationId(Integer stationid) {
        Collector byStationIdIn = collectorRepository.findByStationIdIn(stationid);
        return byStationIdIn;
    }

    @Override
    public Collector saveByStationId(Collector collector) {
        Collector save = collectorRepository.save(collector);
        return save;
    }

    @Override
    public List<Device> findAll() {
        return null;
    }
}
