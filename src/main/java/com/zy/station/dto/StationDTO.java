package com.zy.station.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zy.station.dataobject.Device;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-06-11 18:30
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationDTO {
    /** 采集端编号. */
    private Integer id;
    /** 所属变电站的编号. */
    private Integer stationId;
    /** 采集端的IP地址. */
    private Integer ip;
    /** 状态默认值设1表示开机.*/
    private Integer status;
}
