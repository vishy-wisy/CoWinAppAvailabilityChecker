package com.vish.cowin.app.conwinapp;

import com.vish.cowin.app.conwinapp.processor.CoWinAPIProcessor;
import com.vish.cowin.app.conwinapp.model.Center;
import com.vish.cowin.app.conwinapp.service.CowinAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class ConwinappApplication //implements CommandLineRunner
{

	@Autowired
	CoWinAPIProcessor coWinAPIController;

	@Autowired
	CowinAPIService cowinAPIService;


	public static void main(String[] args)
	{
		SpringApplication.run(ConwinappApplication.class, args);
	}


	public void run(String... args) throws Exception
	{
		/*boolean isThane =  true;
		boolean isYtl = false ;
		boolean is18 = false;
		boolean is45 = true;

		int Id=-1;
		String cityname = null;
		int minAge=18,maxAge=45;
		String dateStr = "05-05-2021";

		if ( isThane)
		{
			Id=392;//Thane
			cityname ="Thane";
		}
		if ( isYtl )
		{
			Id=368;//Yavatmal
			cityname ="Yavatmal";
		}

		if ( is18 )
		{
			minAge=18;
			maxAge=45;
		}
		if ( is45 )
		{
			minAge=45;
			maxAge=60;
		}

		int disId = Id;
		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=" + disId +"&date=" + dateStr;


		cowinAPIService.setCityName(cityname);
		cowinAPIService.setMaxAge(maxAge);
		cowinAPIService.setMinAge(minAge);

		coWinAPIController.setUrl(Optional.ofNullable(url));
		Map<String, List<Center>> map = coWinAPIController.process();
		cowinAPIService.setCenterMap(map);
		cowinAPIService.process();*/

	}
}

