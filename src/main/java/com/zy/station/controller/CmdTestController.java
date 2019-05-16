package com.zy.station.controller;

import com.google.gson.Gson;
import com.zy.station.VO.CmdResultVO;
import com.zy.station.VO.ResultVO;
import com.zy.station.dto.CmdDTO;
import com.zy.station.service.Impl.CollectorServiceImpl;
import com.zy.station.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 用于采集端发送数据的测试
 */
@RestController
@RequestMapping("/testcontrol")
public class CmdTestController {

    @PostMapping("/setpower") // 开关机路由
    public CmdResultVO demo(@RequestBody String jsonString) {
        Gson gson = new Gson();
        CmdResultVO cmdResultVO = gson.fromJson(jsonString, CmdResultVO.class);
        // 只是做个测试 非真实数据
        cmdResultVO.setStation_id(1);
        cmdResultVO.setReturn_code(1);
        System.out.println("*****************接收命令成功****************模拟响应数据:"+cmdResultVO.toString());
        return cmdResultVO;
    }
}
