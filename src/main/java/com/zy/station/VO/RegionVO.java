package com.zy.station.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zy.station.dataobject.Station;
import lombok.Data;

import java.util.List;

@Data
public class RegionVO {

    @JsonProperty("region")
    private String region;
    @JsonProperty("bdz")
    private List<RegionBdzVO> bdzVOList;
}
