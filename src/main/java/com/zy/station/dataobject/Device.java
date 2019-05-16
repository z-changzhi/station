package com.zy.station.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class Device {
    @Id
    private Integer id;
    /** 设备id. */
    private Integer typeId;
    /**设备类型. */
    private Integer stationId;
    /** 变电站id.*/
    private Integer roomId;
    /** 所在房间编号*/
    private Integer positionId;
    /** 房间内的序号 */
    private Integer status;
    /** 设备的状态 */
}
