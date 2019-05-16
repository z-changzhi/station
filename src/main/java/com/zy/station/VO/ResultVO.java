package com.zy.station.VO;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

@Data
public class ResultVO<T> {
    //private Integer code;

    private T bdzlist;
    private boolean success;
}
