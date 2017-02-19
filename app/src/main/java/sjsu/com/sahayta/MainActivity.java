package sjsu.com.sahayta;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity{
    private Button button;
    private TextView jsonTextView;
    String jsonURL = "http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b1b15e88fa797225412429c1c50c122a1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buttonCall);
        jsonTextView = (TextView) findViewById(R.id.jsonText);

        /*button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:6692526782"));

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });*/
        panicButtonFeature();
    }

    private void panicButtonFeature() {
        button.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
//                return true;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("inside Action_Down");
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url(jsonURL).build();
//
//                    Call call = client.newCall(request);
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Request request, IOException e) {
//                            Toast.makeText(MainActivity.this, getResources().getString(R.string.networkFailed), Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onResponse(Response response) throws IOException {
//                            final String json = response.body().string();
//                            MainActivity.this.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    jsonTextView.setText(json);
//                                }
//                            });
//                        }
//                    });
                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    //PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(MainActivity.this), 0);
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE},1);
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage("6692526782", null, "hello neha", null, null);
                    try {
                        TimeUnit.SECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:6692526782"));
                    if (ActivityCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }

                    startActivity(callIntent);
                }
                return true;
            }
        });
    }
}
