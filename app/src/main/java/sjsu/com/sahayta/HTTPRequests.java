package sjsu.com.sahayta;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPRequests {
    OkHttpClient client;
    MediaType JSON;

    public HTTPRequests(){
        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
    }

    public void getRequest(String urlStr) {
        //Request request = new Request.Builder().url("http://192.168.0.108:5000/").build();
        String URL = String.format("%s/%s", "http://ec2-52-53-234-187.us-west-1.compute.amazonaws.com:5000", urlStr);
        Request request = new Request.Builder().url(URL).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed to call getRequest");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("response : " + response.body().toString());

            }
        });
    }

    public void postRequest(String urlStr, String value) {
        String URL = String.format("%s/%s", "http://ec2-52-53-234-187.us-west-1.compute.amazonaws.com:5000", urlStr);
        RequestBody formBody = new FormBody.Builder()
                .add("key", value)
                .build();

        Request request = new Request.Builder()
                .url(URL)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed to call postRequest");
            }

            @Override
            public void onResponse(Call call, Response response) {
                System.out.println("response : " + response.body().toString());
            }
        });
    }
}
