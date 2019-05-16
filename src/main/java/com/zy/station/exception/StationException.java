package com.zy.station.exception;

import com.zy.station.enums.ResultEnum;

public class StationException extends RuntimeException{

    private Integer code;

    public StationException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public StationException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}