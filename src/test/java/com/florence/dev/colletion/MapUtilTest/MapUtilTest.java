package com.florence.dev.colletion.MapUtilTest;


import com.florence.dev.utils.colletion.MapUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapUtilTest {
    @Test
    public void testForEach(){
        Map<String, String> map = new HashMap<>();

        map.put("1","1");
        map.put("2","2");
        MapUtils.printKeys(map);
        MapUtils.printKeysAndValues(map);
        MapUtils.printValues(map);
    }
}
