package sjsu.com.sahayta;

import android.os.AsyncTask;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class HTTPRequestsOld {
    OkHttpClient client;
    MediaType JSON;

    public HTTPRequestsOld(){
        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
    }

    public void makeGetRequest() throws IOException{
        GetTask task = new GetTask();
        task.execute();
    }

    public void makePostRequest() throws IOException {
        PostTask task = new PostTask();
        task.execute();
    }

    public class GetTask extends AsyncTask {
        private Exception exception;

        @Override
        protected String doInBackground(Object[] urls) {
            try {
                String getResponse = get("http://localhost:5000/");
                return getResponse;
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(String getResponse) {
            System.out.println("the response to the get request: " + getResponse);
        }

        public String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    public class PostTask extends AsyncTask {
        private Exception exception;

        @Override
        protected String doInBackground(Object[] urls) {
            try {
                //String getResponse = post("http://www.roundsapp.com/post", bowlingJson("Me", "You"));
                //return getResponse;
                return null;
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(String getResponse) {
            System.out.println(getResponse);
        }

        private String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

}
