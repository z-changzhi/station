package com.zy.station.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class Collector {
    @Id
    /** 采集端编号. */
    private Integer id;
    /** 所属变电站的编号. */
    private Integer stationId;
    /** 采集端的IP地址. */
    private Integer ip;
    /** 状态默认值设1表示开机.*/
    private Integer status;
}
