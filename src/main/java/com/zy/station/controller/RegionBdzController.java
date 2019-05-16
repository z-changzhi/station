package com.zy.station.controller;

import com.zy.station.VO.*;
import com.zy.station.dataobject.Collector;
import com.zy.station.dataobject.Device;
import com.zy.station.dataobject.Report;
import com.zy.station.dataobject.Station;
import com.zy.station.enums.ResultEnum;
import com.zy.station.exception.StationException;
import com.zy.station.service.CollectorService;
import com.zy.station.service.DeviceService;
import com.zy.station.service.ReportService;
import com.zy.station.service.StationService;
import com.zy.station.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取所有的 变电站 + 变电站详情
 * 用于 空调信息 温湿度 除湿机 的数据展示
 * 2019-05-10 21:11:43
 */
@RestController
@RequestMapping("/region/bdz")
public class RegionBdzController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private StationService stationService;

    @Autowired
    private CollectorService collectorService;


    @GetMapping("/list")
    public ResultVO list() {// 变电站 + 设备 + 设备详情列表
        List<Device> deviceInfoList = deviceService.findAll();// 找到所有的设备
        List<Integer> idList = deviceInfoList.stream() // 将它所有变电站的id作为一个列表
                .map(e -> e.getStationId())
                .collect(Collectors.toList());

        List<Report> reportList = reportService.findAll();// 找到所有的详情

        List<Station> stationList = stationService.findByIdIn(idList);// 拿这个id列表去找我所需要的变电站

        List<StationVO> stationVOList = new ArrayList<>();
        for (Station station : stationList) { // 遍历所有的类型 添加详情
            // 设备
            StationVO stationVO = new StationVO();
            stationVO.setId(station.getId());
            stationVO.setName(station.getName());
            stationVO.setAddress(station.getAddress());
            stationVO.setRegion(station.getRegion());
            stationVO.setProvince(station.getProvince());
            stationVO.setLon(station.getLon());
            stationVO.setLat(station.getLat());

            List<DeviceVO> deviceVOList = new ArrayList<>();
            for (Device device : deviceInfoList) {
                if (device.getStationId().equals(station.getId())) {
                    DeviceVO deviceVO = new DeviceVO();
                    BeanUtils.copyProperties(device, deviceVO);


                    List<ReportVO> reportVOList = new ArrayList<>();
                    for (Report report : reportList) {
                        if (report.getDeviceId().equals(device.getId())) {
                            ReportVO reportVO = new ReportVO();
                            BeanUtils.copyProperties(report, reportVO);
                            reportVOList.add(reportVO);
                        }
                    }
                    deviceVO.setReportsVOList(reportVOList);
                    deviceVOList.add(deviceVO);
                }
            }
            stationVO.setDevicesVOList(deviceVOList);
            stationVOList.add(stationVO);
        }
        return ResultVOUtil.success(stationVOList);
    }

    /**
     * 变电站数据回显
     */
    @PostMapping("/bdzData")
    public ResultVO bdzData(@RequestParam("stationId") Integer stationId) {
        // 根据变电站id 找到 变电站实体类并返回
        Station byStationId = stationService.findById(stationId);
        return ResultVOUtil.success(byStationId);
    }

    /**
     * 变电站数据 修改+存储
     */
    @PostMapping("/bdzSave")
    public ResultVO bdzSave(@RequestParam("id") Integer id,
                            @RequestParam("name") String name,
                            @RequestParam("address") String address,
                            @RequestParam("region") String region,
                            @RequestParam("province") String province) {
        Station station = stationService.findById(id);
        if (station == null) {// 没有这个变电站新建变电站 拿着id创建变电站
            // throw new StationException(ResultEnum.STATION_ERROR);
            // 新建采集端
            station = new Station();
            station.setId(id);
            station.setName(name);
            station.setAddress(address);
            station.setRegion(region);
            station.setProvince(province);
        }
        // 有变电站修改变电站数据
        station.setName(name);
        station.setAddress(address);
        station.setRegion(region);
        station.setProvince(province);
        stationService.saveStation(station);
        return ResultVOUtil.success(station);
    }

    /**
     * 采集端数据回显
     */
    @PostMapping("/collectorData")
    public ResultVO collectorData(@RequestParam("stationId") Integer stationId) {
        // 根据变电站id 找到采集端实体类 并返回
        Collector byStationId = collectorService.findByStationId(stationId);
        return ResultVOUtil.success(byStationId);
    }

    /**
     * 采集端数据存储
     */
    @PostMapping("/collectorSave")
    public ResultVO collectorSave(@RequestParam("stationId") Integer stationId,
                                  @RequestParam("ip") Integer ip,
                                  @RequestParam("status") Integer status
    ) {
        Collector byStationId = collectorService.findByStationId(stationId);
        if (byStationId == null) {
            // 没有这个采集端新建采集端
            // throw new StationException(ResultEnum.STATION_ERROR);
            // 新建采集端
            byStationId = new Collector();
            byStationId.setId(stationId);
            byStationId.setStationId(stationId);
        }
        // 有这个采集端 修改采集端数据
        byStationId.setIp(ip);
        byStationId.setStatus(status);
        collectorService.saveByStationId(byStationId);
        return ResultVOUtil.success(byStationId);
    }
}
