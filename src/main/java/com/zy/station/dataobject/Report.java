package com.zy.station.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Report {
    @Id
    private Integer id;
    /** 上报记录流水号id. */
    private Integer deviceId;
    /** 设备编号id. */
    private Date reportTime;
    /** 上报信息的时间. */
    private Integer setPower;
    /** 设置开关机. */
    private Integer setTimer;
    /** 设置定时. */
    private Integer setTempe;
    /** 设定温度值. */
    private Integer setHumid;
    /** 设定湿度值. */
    private Integer tempeEnv;
    /** 环境温度. */
    private Integer humidEnv;
    /** 环境湿度. */



}