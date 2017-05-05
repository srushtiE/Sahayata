package sjsu.com.sahayta;

/**
 * Created by Keya on 3/27/17.
 */


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class ShowEmergencyContacts extends AppCompatActivity {
    ListView listView ;
    HashMap<Integer, String> StoreContactPhone ;
    HashMap<Integer, String> StoreContactName ;
    Cursor cursor ;
    String name, phonenumber ;
    QuickContactBadge EmailPic;
    TextView tname,tnumber;
    Button button;
    LinearLayout v;
    BaseAdapter badapter;
    final Context context=this;

    public  static final int RequestPermissionCode  = 1 ;
    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.emergency_contacts);

        setTitle("Emergency Contacts");
        listView = (ListView)findViewById(R.id.listview1);

        StoreContactPhone = new HashMap<Integer, String>();
        StoreContactName = new HashMap<Integer, String>();
        EnableRuntimePermission();
        GetContactsIntoArrayList();

        badapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return StoreContactPhone.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;

                if (view == null) {
                    LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext()
                            .LAYOUT_INFLATER_SERVICE);
                    view = layoutInflater.inflate(R.layout.text_contacts, null);


                }
                tname = (TextView) view.findViewById(R.id.number);
                tname.setText(StoreContactPhone.get(position));
                tnumber = (TextView) view.findViewById(R.id.name);
                tnumber.setText(StoreContactName.get(position));
                String checkingindb="";
                try {
                     checkingindb = new DBHelper(getApplicationContext()).getData(StoreContactName.get(position).toString());
                    if(checkingindb.length()>0){
                        view.setBackgroundColor(Color.parseColor("#ff0000"));
                    }
                }
                catch (Exception e){
                     checkingindb="";
                }
                System.out.println("value in db "+checkingindb);

                return view;
            }
        };

        listView.setAdapter(badapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private boolean stateChanged;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ShowEmergencyContacts.this,"helo", Toast.LENGTH_LONG).show();
                TextView tvname=(TextView) listView.getChildAt(position).findViewById(R.id.name);
                TextView tvnum=(TextView) listView.getChildAt(position).findViewById(R.id.number);
                String fromdbname = new DBHelper(getApplicationContext()).getData( tvname.getText().toString());
                System.out.println("value"+fromdbname);
                if(fromdbname!=null && fromdbname.equals(tvnum.getText().toString())){
                    listView.getChildAt(position).setBackgroundResource(android.R.color.holo_green_dark);
                    int s = new DBHelper(getApplicationContext()).deleteData( tvname.getText().toString());

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Sahayata For You");
                    alertDialogBuilder.setMessage("Removed from Emergency Contacts")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // FIRE ZE MISSILES!
                                    Log.d("Delete", "Fire");
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it

                    alertDialog.show();

                }else{
                    Boolean cursor1 = new DBHelper(getApplicationContext()).addDatabase( tvname.getText().toString(),tvnum.getText().toString());
                    listView.getChildAt(position).setBackgroundColor(Color.parseColor("#ff0000"));
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Sahayata For You");
                    alertDialogBuilder.setMessage("Added to Emergency Contacts")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // FIRE ZE MISSILES!
                                    Log.d("Delete", "Fire");
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it

                    alertDialog.show();
                }





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
    public void GetContactsIntoArrayList(){

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
        int count=0;

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            StoreContactPhone.put(count,phonenumber);
            StoreContactName.put(count,name);
            count++;
        }

        cursor.close();

    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                ShowEmergencyContacts.this,
                Manifest.permission.READ_CONTACTS))
        {

            Toast.makeText(ShowEmergencyContacts.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(ShowEmergencyContacts.this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(ShowEmergencyContacts.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(ShowEmergencyContacts.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }



}