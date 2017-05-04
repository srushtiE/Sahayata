package sjsu.com.sahayta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by keya on 4/28/17.
 */

public class ReportIncident extends AppCompatActivity {
    private Button button;
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
                    System.out.println("inside Action_Down");
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

}
