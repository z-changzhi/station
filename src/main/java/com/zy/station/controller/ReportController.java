package com.zy.station.controller;

import com.zy.station.VO.ResultVO;
import com.zy.station.dataobject.Report;
import com.zy.station.service.Impl.ReportServiceImpl;
import com.zy.station.service.ReportService;
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
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @GetMapping("/list")
    public ResultVO demo(@RequestParam("deviceId") Integer deviceId){
        List<Integer> devicelist = new ArrayList<>();
        devicelist.add(deviceId);
        List<Report> byDeviceId = reportService.findByDeviceId(devicelist);
        return ResultVOUtil.success(byDeviceId);
    }
}
