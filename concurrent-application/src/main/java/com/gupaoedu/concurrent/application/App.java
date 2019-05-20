package com.gupaoedu.concurrent.application;

import com.gupaoedu.concurrent.application.old.BatchInsure;
import com.gupaoedu.concurrent.application.optimized.OptimizedBatchInsure;

import java.util.*;

/**
 * @Author laihui
 * @Date 2019/5/20
 * @Desc
 * @Version 1.0
 **/
public class App {
    public static void main(String[] args) {
        RequestDto dto = genDto();
        BatchInsure batchInsure = new BatchInsure();
        batchInsure.doInsure(dto);

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        OptimizedBatchInsure optimizedBatchInsure = new OptimizedBatchInsure();
        optimizedBatchInsure.doInsure(dto);
    }

    private static RequestDto genDto() {
        List<RequestElement> elementList = new ArrayList<RequestElement>();
        Random random = new Random();
        int num = random.nextInt(20) + 1;
        for (int i = 0; i < num; i++) {
            RequestElement element = new RequestElement("00000000" + i, new HashMap<String, Object>());
            elementList.add(element);
        }
        RequestDto dto = new RequestDto(new HashMap<String, Object>(), elementList);
        return dto;
    }
}
