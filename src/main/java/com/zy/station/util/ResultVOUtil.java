package com.zy.station.util;

import com.zy.station.VO.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
       // resultVO.setCode(0);
        resultVO.setBdzlist(object);
        resultVO.setSuccess(true);
        return resultVO;
    }
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(true);
        return resultVO;
    }
}
