package model;

import com.google.gson.Gson;
import info.WeatherClass;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import javax.xml.ws.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

public class WeatherUtil {
    public static String region;

    public static WeatherClass getWeatherItems() throws IOException, IOException {

        HttpGet httpGet=new HttpGet("http://api.openweathermap.org/data/2.5/weather?q="+region+"&appid=244359e687806d4f61854a630a5c4922");
        HttpClient httpClient= HttpClients.createDefault();
        HttpResponse httpResponse=httpClient.execute(httpGet);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        Gson gson=new Gson();
        WeatherClass weatherClass=gson.fromJson(bufferedReader,WeatherClass.class);
        System.out.println(weatherClass);
        return weatherClass;
    }

}
