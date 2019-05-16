package com.zy.station.util;

public class IpUtil {
    public static String int2ip(Integer ip) {
        String str;
        int tt[] = new int[4];
        tt[0] = (ip >>> 24) >>> 0;
        tt[1] = ((ip << 8) >>> 24) >>> 0;
        tt[2] = (ip << 16) >>> 24;
        tt[3] = (ip << 24) >>> 24;
        str = tt[0] + "." + tt[1]+ "." + tt[2] + "." + tt[3];
        return str;
    }
}
