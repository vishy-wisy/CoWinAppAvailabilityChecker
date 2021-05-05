package com.vish.cowin.app.conwinapp.controller;

import com.vish.cowin.app.conwinapp.model.Center;
import com.vish.cowin.app.conwinapp.processor.CoWinAPIProcessor;
import com.vish.cowin.app.conwinapp.service.CowinAPIService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@Controller
public class SearchController
{

    @Autowired
    CoWinAPIProcessor coWinAPIProcessor;

    @Autowired
    CowinAPIService cowinAPIService;

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String searchAvailabilty(ModelMap modelMap, @RequestParam String cityName,
                                    @RequestParam int  age,
                                    @RequestParam String dateStr)
    {
        String resultStr="Hello";

        coWinAPIProcessor.validateAndPopulate(cityName,age,dateStr);
        cowinAPIService.populateMetaData(age,cityName);

        Map<String, List<Center>> map = coWinAPIProcessor.process();
        cowinAPIService.setCenterMap(map);
        List<String> rows = cowinAPIService.process();
        //System.out.println(resultStr);
        rows.removeIf( l -> l.contains("------"));
        modelMap.addAttribute("finalresult",rows);
        return "result";
    }
}
