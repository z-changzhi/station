package com.zy.station.controller;

import com.zy.station.VO.ResultVO;
import com.zy.station.dataobject.Device;
import com.zy.station.service.Impl.DeviceServiceImpl;
import com.zy.station.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  通过 设备id 拿取 report
 *  结果返回Report json
 */
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceService;

    @GetMapping("/list")
    public ResultVO demo(@RequestParam("stationId") Integer stationId){
        List<Integer> devicelist = new ArrayList<>();
        devicelist.add(stationId);
        List<Device> byDeviceId = deviceService.findByStationIdIn(devicelist);
        return ResultVOUtil.success(byDeviceId);
    }

    // 参数: 变电站
    // 返回: 设备json
    @GetMapping("/listByRegion")
    public ResultVO listByRegion(@RequestParam("stationId") Integer stationId) {
        List<Device> byDeviceId = new ArrayList<Device>();
        if (stationId==-1){
            //查找所有
            byDeviceId = deviceService.findAll();
        }else {
            List<Integer> devicelist = new ArrayList<>();
            devicelist.add(stationId);
            byDeviceId = deviceService.findByStationIdIn(devicelist);
        }
        return ResultVOUtil.success(byDeviceId);
    }

    /** 获取参数 创建设备*/
    @PostMapping("/create") // 开关机路由
    public ResultVO demo(@RequestParam("id") Integer id,
                         @RequestParam("typeId") Integer typeId,
                         @RequestParam("stationId") Integer stationId,
                         @RequestParam("roomId") Integer roomId,
                         @RequestParam("positionId") Integer positionId,
                         @RequestParam("status") Integer status
    ) {

        Device device = new Device();
        device.setId(id);
        device.setTypeId(typeId);
        device.setStationId(stationId);
        device.setRoomId(roomId);
        device.setPositionId(positionId);
        device.setStatus(status);

        Device station1 = deviceService.saveDevice(device);
        return ResultVOUtil.success(station1);
    }
}
