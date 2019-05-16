package com.zy.station.dto;

import lombok.Data;

@Data
public class CmdDTO {
    /** 设备ID. */
    private Integer device_id;
    /** 操作类型 . */
    private Integer control_type_id;
    /**  操作码.*/
    private Integer control_code;
}
