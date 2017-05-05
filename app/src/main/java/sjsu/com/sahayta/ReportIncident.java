package sjsu.com.sahayta;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * Created by keya on 4/28/17.
 */

public class ReportIncident extends AppCompatActivity {
    private Button button;
    private String incidentvalue="";
    final Context context=this;
    RestClient rc;
    GPSTracker gps=new GPSTracker(ReportIncident.this);
    double latitude = gps.getLatitude();
    double longitude = gps.getLongitude();
    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_report);
        setTitle("Report a  Incident");
        button = (Button) findViewById(R.id.reportbutton);
        button.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
//                return true;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //rc.postIncident(incidentvalue,"1");
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Sahayata For You");
                    alertDialogBuilder.setMessage("Your details have been submitted")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // FIRE ZE MISSILES!
                                    Log.d("Delete", "Fire");
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    // show it

                }
                return true;
            }

        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    public void onRGClick(View v) {
        RadioButton rbutton1=(RadioButton) findViewById(R.id.radioButton1);
         RadioButton rbutton2=(RadioButton) findViewById(R.id.radioButton2);

         RadioButton rbutton3=(RadioButton) findViewById(R.id.radioButton3);

         RadioButton rbutton4=(RadioButton) findViewById(R.id.radioButton4);
        int id = v.getId();
        if (id == R.id.radioButton1) {
            //Do something
            System.out.println("inside 1");
            incidentvalue="1";
            rbutton2.setChecked(false);
            rbutton3.setChecked(false);
            rbutton4.setChecked(false);


        }
        if (id == R.id.radioButton2) {
            //Do something
            System.out.println("inside 2");
            incidentvalue="2";
            rbutton1.setChecked(false);
            rbutton3.setChecked(false);
            rbutton4.setChecked(false);


        }
        if (id == R.id.radioButton3) {
            //Do something
            System.out.println("inside 3");
            incidentvalue="3";
            rbutton2.setChecked(false);
            rbutton1.setChecked(false);
            rbutton4.setChecked(false);


        }
        if (id == R.id.radioButton4) {
            //Do something
            System.out.println("inside 4");
            incidentvalue="4";
            rbutton2.setChecked(false);
            rbutton3.setChecked(false);
            rbutton1.setChecked(false);


        }
        System.out.println(incidentvalue+latitude+longitude);
    }

}
