package com.zy.station.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zy.station.dataobject.Station;
import lombok.Data;

import java.util.List;

@Data
public class RegionBdzVO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private Integer status;
}
