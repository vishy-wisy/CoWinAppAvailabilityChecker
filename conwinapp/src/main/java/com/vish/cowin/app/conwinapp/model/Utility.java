package com.vish.cowin.app.conwinapp.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class Utility
{
    private Map<String,Integer> cityIdMap;

    public Utility()
    {
        cityIdMap = new HashMap<>();
        cityIdMap.put("Thane",392);
        cityIdMap.put("Nagpur",365);
        cityIdMap.put("Yavatmal",368);
        cityIdMap.put("Pune",363);
        cityIdMap.put("Mumbai",395);
        cityIdMap.put("Amravati",366);
        cityIdMap.put("Buldhana",367);
    }

}
