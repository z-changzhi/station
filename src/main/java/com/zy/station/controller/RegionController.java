package com.zy.station.controller;

import com.google.gson.Gson;
import com.zy.station.VO.*;
import com.zy.station.dataobject.Device;
import com.zy.station.dataobject.Report;
import com.zy.station.dataobject.Station;
import com.zy.station.service.DeviceService;
import com.zy.station.service.ReportService;
import com.zy.station.service.StationService;
import com.zy.station.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取 省份 + 变电站  ----- 用于 html 侧边栏的下拉列表
 * 创建变电站
 */
@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private StationService stationService;


    /** 获取 省份 + 变电站*/
    @GetMapping("/list")
    public ResultVO list() {
        String province = "江西省";
        // 1. 找出所有的变电站筛选出他们的 所在市的 名称
        List<Station> stationServiceAll = stationService.findAll();// 找到所有的变电站

        List<Station> stationsInfoList = stationServiceAll.stream()// 筛选江西省 这里也可以把 省份筛选 写在service层
                .filter(e -> province.equals(e.getProvince()))
                .collect(Collectors.toList());

        List<String> regionList = stationsInfoList.stream() // 将它所有变电站所在市区的名字作为一个 List
                .map(e -> e.getRegion())
                .collect(Collectors.toList());

        // 通过hashset剔除重复元素 lambda也可以 但是还是用简单的方法吧
        HashSet h = new HashSet(regionList);
        regionList.clear();
        regionList.addAll(h);
        Collections.reverse(regionList);// 颠倒顺序

        // 2. 通过省的名称 查找出他们的变电站 list
        List<Station> stationList = stationService.findByRegionIn(regionList);
        // 3. 开始视图层的封装
        List<RegionVO> regionVOList = new ArrayList<>();
        for (String regionlist : regionList) {
            RegionVO regionVO = new RegionVO();
            regionVO.setRegion(regionlist);
            List<RegionBdzVO> regionStationList = new ArrayList<>();
            for (Station stationlist:stationList){
                if(stationlist.getRegion().equals(regionlist)){
                    RegionBdzVO regionBdzVO = new RegionBdzVO();
                    regionBdzVO.setId(stationlist.getId());
                    regionBdzVO.setName(stationlist.getName());
                    regionStationList.add(regionBdzVO);
                }
            }
            regionVO.setBdzVOList(regionStationList);
            regionVOList.add(regionVO);
        }
        // 以 省份(id,region,变电站(id.name)) +  的json形式返回
        return ResultVOUtil.success(regionVOList);
    }

    /** 获取参数 创建变电站*/
    @PostMapping("/create") // 开关机路由
    public ResultVO demo(@RequestParam("id") Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("address") String address,
                         @RequestParam("region") String region,
                         @RequestParam("province") String province
                         ) {
        Station station = new Station();
        station.setId(id);
        station.setName(name);
        station.setAddress(address);
        station.setRegion(region);
        station.setProvince(province);
        Station station1 = stationService.saveStation(station);
        return ResultVOUtil.success(station1);
    }
}
