package com.zy.station.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StationVO {
    // 返回给前端要是name加注解
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("region")
    private String region;

    @JsonProperty("province")
    private String province;

    @JsonProperty("x")
    private Integer lon;
    @JsonProperty("y")
    private Integer lat;
    @JsonProperty("devices")
    private List<DeviceVO> devicesVOList;
}