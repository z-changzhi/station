package com.zy.station.service.Impl;

import com.google.gson.Gson;
import com.zy.station.dataobject.Station;
import com.zy.station.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StationServiceImplTest {
    @Autowired
    private StationServiceImpl stationService;
    @Test
    public void findAll() {
        List<Station> all = stationService.findAll();
        for (Station list : all) {
            System.out.println(list.getAddress());
        }
    }

    @Test
    public void saveStation() {
        //Gson gson = new Gson();
        //Station station = gson.fromJson(jsonString, Station.class);//转成Station 对象
        Station station = new Station();
        station.setId(10);
        station.setName("220V测试变电站");
        station.setAddress("东湖区");
        station.setRegion("南昌市");
        station.setProvince("江西省");
        Station station1 = stationService.saveStation(station);

    }

    @Test
    public void RegionTest() {

        List<String> devicelist = new ArrayList<>();
        devicelist.add("南昌市");

        List<Station> all = stationService.findByRegionIn(devicelist);
        for (Station list : all) {
            System.out.println(list.getAddress());
        }
    }
}