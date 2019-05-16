package com.zy.station.controller;

import com.google.gson.Gson;
import com.zy.station.VO.ResultVO;
import com.zy.station.dto.CmdDTO;
import com.zy.station.service.Impl.CollectorServiceImpl;
import com.zy.station.util.IpUtil;
import com.zy.station.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 向采集端发送数据
 * 2019-05-9 19:54:25
 */
@RestController
@RequestMapping("/control")
public class CmdController {
    @Autowired
    private CollectorServiceImpl collectorService;

    @PostMapping("/setpower") // 开关机路由
    public ResultVO demo(@RequestParam("bdz_id") Integer bdzId,
                         @RequestParam("device_id") Integer deviceId,
                         @RequestParam("control_type_id") Integer controlTypeId,
                         @RequestParam("control_code") Integer controlCode) {
        /* 测试 */
        //System.out.println("bdz_id=" + bdzId + "device_id=" + deviceId + "control_type_id=" + controlTypeId + "control_code=" + controlCode);
        // 1.变电站ID后查询数据库采集端表 得到id地址
        Integer ip = collectorService.findByStationId(bdzId).getIp();
        // TODO 数据库ip地址转换

        CmdDTO cmdDTO = new CmdDTO();
        cmdDTO.setDevice_id(deviceId);
        cmdDTO.setControl_type_id(controlTypeId);
        cmdDTO.setControl_code(controlCode);
        String target = "http://"+ IpUtil.int2ip(ip)+":8080/sell/testcontrol/setpower";
        System.out.println(target);
        String response = postForJson(target, cmdDTO);
        //System.out.println(response); // 测试
        return ResultVOUtil.success(response);
    }

    public static String postForJson(String url, CmdDTO cmdDTO) {
        Gson gson = new Gson();// 将对象转换成json用的
        //System.out.println(gson.toJson(cmdDTO)); // 测试json
        RestTemplate restTemplate = new RestTemplate();
        //设置 Http Header
        HttpHeaders headers = new HttpHeaders();
        //设置请求媒体数据类型
        headers.setContentType(MediaType.APPLICATION_JSON);
        //设置返回媒体数据类型
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(gson.toJson(cmdDTO), headers);
        return restTemplate.postForObject(url, formEntity, String.class );
    }

}
