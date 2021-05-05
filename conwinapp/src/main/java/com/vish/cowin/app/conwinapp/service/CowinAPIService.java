package com.vish.cowin.app.conwinapp.service;


import com.vish.cowin.app.conwinapp.model.Center;
import com.vish.cowin.app.conwinapp.model.Session;
import com.vish.cowin.app.conwinapp.model.Utility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CowinAPIService
{
    private String cityName;

    private int minAge;

    private int maxAge;

    private Map<String, List<Center>> centerMap = new HashMap<>();

    public void populateMetaData(long age ,String city)
    {
        if ( age == 18 )
        {
            minAge=18;
            maxAge=45;
        }
        if ( age == 45 )
        {
            minAge=45;
            maxAge=60;
        }

        cityName = city;
    }

    public List<String> process()
    {
        List<String> stringList = new ArrayList<>();
        List<Center> centerList = centerMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        centerList.stream()
               // .filter(c1 -> c1.getBlock_name().contains(cityName))
                .forEach( c ->
                {
                    List<Session> sessionList = c.getSessions();

                    List<Session> avalaibleSessionList = sessionList.stream()
                            .filter(l ->
                            {
                                long availabilty = Long.parseLong(l.getAvailable_capacity());
                                return availabilty > 0;
                            })
                            .filter( l1 ->
                            {
                                long age = Long.parseLong(l1.getMin_age_limit());
                                return age >= minAge && age < maxAge ;
                            })
                            .collect(Collectors.toList());

                    if ( avalaibleSessionList.size() > 0 )
                    {
                        String message = "Available at " + c.getBlock_name() + "|" + c.getCenter_id();
                        System.out.println(message);
                        stringList.add(message);
                        stringList.add("\n");

                        String dashLine = "-------------------------------------------------------";
                        System.out.println(dashLine);
                        /*stringList.add(dashLine);
                        stringList.add("\n");*/

                        avalaibleSessionList.forEach( item ->
                                {
                                    StringJoiner stringJoiner = new StringJoiner(",");
                                    stringJoiner.add(item.getDate());
                                    stringJoiner.add(item.getMin_age_limit());
                                    stringJoiner.add(item.getAvailable_capacity());
                                    stringJoiner.add(item.getVaccine());
                                    System.out.println(stringJoiner);
                                    stringList.add(stringJoiner.toString());
                                    stringList.add("\n");
                                }
                        );

                    }
                });

        System.out.println("Exiting program");
        return stringList;
    }

}


