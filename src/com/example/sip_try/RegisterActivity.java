package com.example.sip_try;

import java.text.ParseException;

//import com.example.android.sip.R;

import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
//import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
    public SipManager manager = null;
    public SipProfile me = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }*/
		
		Intent intent = getIntent();
	}

	public void click_register(View view){
		EditText aux = (EditText) findViewById(R.id.edit_username);
		String username = aux.getText().toString();
		aux = (EditText) findViewById(R.id.edit_password);
		String pass = aux.getText().toString();
		aux = (EditText) findViewById(R.id.edit_domain);
		String domain = aux.getText().toString();
		aux = (EditText) findViewById(R.id.edit_server);
		String server = aux.getText().toString();
		
        if (username.length() == 0 || domain.length() == 0 || pass.length() == 0 || server.length() == 0) {
            showDialog(3);
            return;
        }
		
        try {
            SipProfile.Builder builder = new SipProfile.Builder(username, domain);
            builder.setPassword(pass);
            
            Intent i = new Intent();
            i.setAction("android.SipDemo.INCOMING_CALL");
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, Intent.FILL_IN_DATA);
            manager.open(me, pi, null);
            
        }catch(ParseException pe){
        	//updateStatus("Connection Error.");
        }catch(SipException se){
        	//updateStatus("Connection Error.");
        }
        
		//Toast.makeText(this, "USER: " + username, Toast.LENGTH_SHORT).show();  
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
    /**
     * Updates the status box at the top of the UI with a messege of your choice.
     * @param status The String to display in the status box.
     */
    /*public void updateStatus(final String status) {
        // Be a good citizen.  Make sure UI changes fire on the UI thread.
        this.runOnUiThread(new Runnable() {
            public void run() {
                TextView labelView = (TextView) findViewById(R.id.sipLabel);
                labelView.setText(status);
            }
        });
    }*/

}
