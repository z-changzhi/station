package com.zy.station.service;



import com.zy.station.dataobject.Report;

import java.util.List;

public interface ReportService {
    //List<Report> findByIdIn(List<Integer> idList);
    /** 查询变电站列表. */
    List<Report> findAll();
    /** 通过设备id 查找设备详情 */
    List<Report> findByDeviceId(List<Integer> ideList);
    /** 存储设备详情*/
    Report saveReport(Report report);
}
