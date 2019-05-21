package com.zy.station.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用于接受 设备发过来的 json
 * TODO json字段名不一致 前端页面 和 设备save
 * */

@Data
public class ReportVO2 {
    @JsonProperty("id")
    private Integer id;
    /** 上报记录流水号id. */

    @JsonProperty("device_id")
    private Integer device_id;
    /** 设备编号id. */
    @JsonProperty("station_id")
    private Integer station_id;
    @JsonProperty("timestamp")
    private Integer timestamp;
    /** 上报信息的时间. */

    @JsonProperty("power")
    private Integer power;
    /** 设置开关机. */

    @JsonProperty("timer")
    private Integer timer;
    /** 设置定时. */

    @JsonProperty("set_tempe")
    private Integer set_tempe;
    /** 设定温度值. */

    @JsonProperty("set_humid")
    private Integer set_humid;
    /** 设定湿度值. */

    @JsonProperty("env_tempe")
    private Integer env_tempe;
    /** 环境温度. */

    @JsonProperty("env_humid")
    private Integer env_humid;
    /** 环境湿度. */
    @JsonProperty("tempe_1")
    private Integer tempe_1;
    @JsonProperty("tempe_2")
    private Integer tempe_2;
    @JsonProperty("tempe_3")
    private Integer tempe_3;
    @JsonProperty("tempe_4")
    private Integer tempe_4;

    @JsonProperty("onload")
    private Integer onload;
    @JsonProperty("error_0")
    private Integer error_0;
    @JsonProperty("error_1")
    private Integer error_1;


}
