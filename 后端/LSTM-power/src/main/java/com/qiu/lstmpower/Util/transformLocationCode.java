package com.qiu.lstmpower.Util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class transformLocationCode {
    private final Map<String, String> ADCodeToLocation = new HashMap<>();
    @PostConstruct
    private void transformCode(){
        String inputFile = "classpath:static/China-City-List-latest.csv";
        try {
            File file = ResourceUtils.getFile(inputFile);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line=br.readLine()) !=null){
                String []Value = line.split(",");
                if (Value.length > 0){
                    String locationID = Value[0];
                    String Code = Value[Value.length - 1];
                    ADCodeToLocation.put(Code,locationID);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("未找到文件——China-City-List-latest.csv");
        } catch (IOException e) {
            System.out.println("IO错误");
        }
    }

    public String getLocationID(String ADCode){
        return ADCodeToLocation.get(ADCode);
    }
}
