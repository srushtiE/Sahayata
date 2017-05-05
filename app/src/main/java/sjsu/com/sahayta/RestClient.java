package sjsu.com.sahayta;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by neha.khowala on 5/2/17.
 */

public class RestClient {

    private static OkHttpClient client = new OkHttpClient();
    private final static String BASE_URL = "http://ec2-52-53-234-187.us-west-1.compute.amazonaws.com:5000/";


    public static JSONObject getRequest(double longitude, double latitude) {
        JSONObject result = null;
        String urlz = "classify/" + round(longitude) + "/" + round(latitude);
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + urlz).newBuilder();
        String url = urlBuilder.build().toString();
        System.out.println(url);
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = new JSONObject(response.body().string());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private static String round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return String.valueOf(bd.doubleValue());
    }

    public void postIncident(String incidentvalue, String s) {

    }
}
