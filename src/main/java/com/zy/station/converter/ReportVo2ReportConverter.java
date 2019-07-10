package com.zy.station.converter;

import com.zy.station.VO.ReportVO2;
import com.zy.station.dataobject.Report;

import java.util.Date;

public class ReportVo2ReportConverter {
    public static Report convert(ReportVO2 reportVO2) {
        Report report = new Report();
        report.setDeviceId(reportVO2.getDevice_id());

        report.setStationId(reportVO2.getStation_id());

        report.setReportTime(new Date(reportVO2.getTimestamp()));
        report.setSetPower(reportVO2.getPower());
        report.setSetTimer(reportVO2.getTimer());
        report.setSetTempe(reportVO2.getSet_tempe());
        report.setSetHumid(reportVO2.getSet_humid());
        report.setTempeEnv(reportVO2.getEnv_tempe());
        report.setHumidEnv(reportVO2.getEnv_humid());

        report.setTempe_1(reportVO2.getTempe_1());
        report.setTempe_2(reportVO2.getTempe_2());
        report.setTempe_3(reportVO2.getTempe_3());
        report.setTempe_4(reportVO2.getTempe_4());

        report.setOnload(reportVO2.getOnload());
        report.setError_0(reportVO2.getError_0());
        report.setError_1(reportVO2.getError_1());
        return report;

    }
}
