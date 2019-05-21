package com.zy.station.controller;

import com.google.gson.Gson;
import com.zy.station.VO.ReportVO;
import com.zy.station.VO.ReportVO2;
import com.zy.station.VO.ResultVO;
import com.zy.station.converter.ReportVo2ReportConverter;
import com.zy.station.dataobject.Device;
import com.zy.station.dataobject.Report;
import com.zy.station.dataobject.Station;
import com.zy.station.service.Impl.ReportServiceImpl;
import com.zy.station.service.ReportService;
import com.zy.station.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通过 设备id 拿取 report
 * 结果返回Report json
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @GetMapping("/list")
    public ResultVO demo(@RequestParam("deviceId") Integer deviceId) {
        List<Integer> devicelist = new ArrayList<>();
        devicelist.add(deviceId);
        List<Report> byDeviceId = reportService.findByDeviceId(devicelist);
        return ResultVOUtil.success(byDeviceId);
    }

    /**
     * 接受设备传过来的 report 存到数据库中
     */
    @PostMapping("/save")
    public ResultVO save(@RequestBody String jsonString) {
        Gson gson = new Gson();
        ReportVO2 reportVo2 = gson.fromJson(jsonString, ReportVO2.class);
        System.out.println(jsonString);
        System.out.println("*************"+reportVo2);

        Report convert = ReportVo2ReportConverter.convert(reportVo2);
        Report report1 = reportService.saveReport(convert);
        return ResultVOUtil.success(report1);
    }
}
