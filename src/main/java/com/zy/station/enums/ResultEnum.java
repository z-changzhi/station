package com.zy.station.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),
    STATION_ERROR(1, "变电站创建失败"),
    COLLECTOR_IS_NULL(2,"采集端不存在"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
