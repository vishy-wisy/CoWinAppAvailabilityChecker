package com.vish.cowin.app.conwinapp.processor;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vish.cowin.app.conwinapp.model.Center;
import com.vish.cowin.app.conwinapp.model.Utility;
import lombok.Data;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@Data
public class CoWinAPIProcessor
{
    private Optional<String> url;

    private Map<String, List<Center>> centerMap = new HashMap<>();

    @Autowired
    private Utility utility;

    public boolean validateAndPopulate(String lcityName,int age,String dateStr)
    {
        boolean isValid = false;

        if ( ! utility.getCityIdMap().containsKey(lcityName) || age < 18  || age > 118)
        {
            System.err.println("Something wrong with input " + lcityName + "|" + age);
            return isValid;
        }

        int disId = utility.getCityIdMap().get(lcityName);
        url = Optional.ofNullable("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=" + disId +"&date=" + dateStr);

        return isValid;
    }

    public JSONObject connect()
    {
        JSONObject jsonObject = null;
        HttpURLConnection urlConn = null;
        BufferedReader reader = null;
        URL urlObj = null;
        try
        {

            if (! url.isPresent())
            {
                System.err.println("Error ....... Blank URI");
            }

            urlObj = new URL(this.url.get());
            urlConn = (HttpURLConnection) urlObj.openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.setConnectTimeout(5000);
            urlConn.setReadTimeout(5000);
            urlConn.setRequestProperty("Accept", "application/json");

            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                System.err.println("Unable to connect to the URL...");
                return null;
            }
            System.out.println("Connected to the server...");

            InputStream is = urlConn.getInputStream();
            reader = new BufferedReader(new InputStreamReader((is)));
            System.out.println("Reading data from server...");
             jsonObject = convertToJSON(reader);

        } catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }

                if (urlConn != null)
                {
                    urlConn.disconnect();
                }

            }
            catch (Exception ex)
            {

            }
        }
        return jsonObject;
    }

    public Map<String, List<Center>> process()
    {
        // String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=392&date=04-05-2021";
        Map<String, List<Center>> map = null;
        try
        {
            JSONObject jsonObject = connect();
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            try
            {
                //Convert Map to JSON
                map = mapper.reader()
                        .forType(new TypeReference<Map<String, List<Center>>>()
                        {
                        })
                        .readValue(jsonObject.toString());

            } catch (JsonGenerationException e)
            {
                e.printStackTrace();
            } catch (JsonMappingException e)
            {
                e.printStackTrace();
            }

        }
        catch (Exception e)
        {

        }
        return map;
    }

    private JSONObject convertToJSON(BufferedReader bufferedReader)
    {
        return new JSONObject(new JSONTokener(bufferedReader));
    }

}
