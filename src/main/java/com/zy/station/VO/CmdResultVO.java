package com.zy.station.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CmdResultVO {

    /** 变电站id  */
    @JsonProperty("station_id")
    private Integer station_id;
    /** 返回结果状态码 */
    @JsonProperty("return_code")
    private Integer return_code;
    /** 设备id */
    @JsonProperty("device_id")
    private Integer device_id;
    /** 操作类型  */
    @JsonProperty("control_type_id")
    private Integer control_type_id;
    /** 操作码 */
    @JsonProperty("control_code")
    private Integer control_code;
}
