package com.zy.station.service.Impl;


import com.zy.station.dataobject.Station;

import com.zy.station.enums.ResultEnum;
import com.zy.station.exception.StationException;
import com.zy.station.repository.StationRepository;

import com.zy.station.service.StationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StationServiceImpl implements StationService {
    @Autowired
    private StationRepository stationRepository;


    @Override
    public Station findById(Integer id) {
        return stationRepository.findById(id);
    }

    @Override
    public List<Station> findByIdIn(List<Integer> ideList) {
        return stationRepository.findByIdIn(ideList);
    }

    @Override
    public List<Station> findByRegionIn(List<String> ideList) {
        return stationRepository.findByRegionIn(ideList);
    }

    @Override
    public List<Station> findAll() {
        List<Station> result = stationRepository.findAll();
        return result;
    }

    @Override
    public Station saveStation(Station staion) {
        Station save = stationRepository.save(staion);
        if (save==null){
            throw new StationException(ResultEnum.STATION_ERROR);
        }
        return save;
    }
}
