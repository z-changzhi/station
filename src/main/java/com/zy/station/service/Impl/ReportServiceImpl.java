package com.zy.station.service.Impl;

import com.zy.station.dataobject.Report;
import com.zy.station.repository.ReportRepository;
import com.zy.station.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportRepository reportRepository;

    /*@Override
    public List<Report> findByIdIn(List<Integer> idList) {
        return null;
    }*/

    @Override
    public Report saveReport(Report report) {
        return  reportRepository.save(report);
    }

    @Override
    public List<Report> findAll() {
        List<Report> all = reportRepository.findAll();
        return all;
    }

    @Override
    public List<Report> findByDeviceId(List<Integer> ideList) {
        return reportRepository.findByDeviceIdIn(ideList);
    }


}
