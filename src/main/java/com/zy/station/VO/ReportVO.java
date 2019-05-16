package com.zy.station.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReportVO {
    @JsonProperty("id")
    private Integer id;
    /** 上报记录流水号id. */

    @JsonProperty("deviceId")
    private Integer deviceId;
    /** 设备编号id. */

    @JsonProperty("reportTime")
    private Date reportTime;
    /** 上报信息的时间. */

    @JsonProperty("setPower")
    private Integer setPower;
    /** 设置开关机. */

    @JsonProperty("setTimer")
    private Integer setTimer;
    /** 设置定时. */

    @JsonProperty("setTempe")
    private Integer setTempe;
    /** 设定温度值. */

    @JsonProperty("setHumid")
    private Integer setHumid;
    /** 设定湿度值. */

    @JsonProperty("tempeEnv")
    private Integer tempeEnv;
    /** 环境温度. */

    @JsonProperty("humidEnv")
    private Integer humidEnv;
    /** 环境湿度. */


}
