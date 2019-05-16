package com.zy.station.repository;

import com.zy.station.dataobject.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StationRepositoryTest {
    @Autowired
    private StationRepository stationRepository;

    @Test
    public void findByOrderId() {
        List<Station> result = stationRepository.findAll();
        for (Station list : result) {
            System.out.println(list.getName());
        }
    }
    @Test
    public void saveTest() {
        Station station = new Station();
        station.setId(9);
        station.setName("220V测试变电站");
        station.setAddress("东湖区");
        station.setRegion("南昌市");
        station.setProvince("江西省");
        Station save = stationRepository.save(station);
    }
}