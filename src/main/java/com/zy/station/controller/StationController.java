package com.zy.station.controller;


import com.zy.station.VO.ResultVO;
import com.zy.station.dataobject.Station;
import com.zy.station.service.Impl.StationServiceImpl;
import com.zy.station.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationServiceImpl stationService;

    @GetMapping("/list")
    public ResultVO demo(@RequestParam("deviceId") Integer deviceId) {
        List<Integer> devicelist = new ArrayList<>();
        devicelist.add(deviceId);
        List<Station> byDeviceId = stationService.findAll();
        return ResultVOUtil.success(byDeviceId);
    }

    // get:变电站列表 用于 设备台账-变电站列表数据显示
    // 参数:  市
    // 返回: 变电站json
    @GetMapping("/listByRegion")
    public ResultVO listByRegion(@RequestParam("region") String region) {
        List<Station> byDeviceId = new ArrayList<Station>();
        if ("*".equals(region)){
            //查找所有
            byDeviceId = stationService.findAll();
        }else {
            List<String> devicelist = new ArrayList<>();
            devicelist.add(region);
            byDeviceId = stationService.findByRegionIn(devicelist);
        }
        return ResultVOUtil.success(byDeviceId);
    }
}