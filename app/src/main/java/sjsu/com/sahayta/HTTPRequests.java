package sjsu.com.sahayta;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTPRequests {
    OkHttpClient client;
    MediaType JSON;

    public HTTPRequests(){
        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
    }

    public void getRequest(String url) throws IOException {
        Request request = new Request.Builder().url("http://192.168.0.108:5000/").build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed to call getRequest");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("response : " + response.body().toString());
            }
        });
    }
}
