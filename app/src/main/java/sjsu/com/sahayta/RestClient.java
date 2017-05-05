package sjsu.com.sahayta;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by neha.khowala on 5/2/17.
 */

public class RestClient {

    private static OkHttpClient client = new OkHttpClient();
    private final static String BASE_URL = "http://ec2-52-53-234-187.us-west-1.compute.amazonaws.com:5000/";
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");



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
        RequestBody body = RequestBody.create(JSON, "{'lat':'37.3298','long':'-121.8889', 'userid':'11', 'incident':'Robbery'}");
        final Request request = new Request.Builder()
                .url(BASE_URL+"report")
                .post(body)
                .addHeader("Content-Type", "application/json") //Notice this request has header if you don't need to send a header just erase this part
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("HttpService", "onFailure() Request was: " + request);

                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();

                Log.e("response ", "onResponse(): " + res );
            }
        });

    }
}
