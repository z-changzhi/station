package com.zy.station.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeviceVO {

    @JsonProperty("id")
    private Integer id;
    /** 设备id. */

    @JsonProperty("typeId")
    private Integer typeId;
    /**设备类型. */

    @JsonProperty("stationId")
    private Integer stationId;
    /** 变电站id.*/

    @JsonProperty("roomId")
    private Integer roomId;
    /** 所在房间编号*/

    @JsonProperty("positionId")
    private Integer positionId;
    /** 房间内的序号 */

    @JsonProperty("status")
    private Integer status;
    /** 设备的状态 */

    @JsonProperty("detailList")
    private List<ReportVO> reportsVOList;
}
