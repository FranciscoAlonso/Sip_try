package com.example.sip_try;

//import android.os.Build;

import java.text.ParseException;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
//import android.app.Activity;
//import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.sip_try.UserSettingsFragment;
import com.example.sip_try.UserSettingsFragment.UserSettingsInterface;

public class HomeActivity extends FragmentActivity implements UserSettingsInterface{
	public static final String LCAT = "SIPClient";
	
	public final static int USER_SETTINGS = 1;
	public final static int ADVANCED_SETTINGS = 2;

	private String username;
	private String pass;
	private String domain;
	private String server;
	
    public SipManager manager = null;
    public SipProfile me = null;
    public SipAudioCall call = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
                
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }*/
        initializeManager();
        
    }

    @Override
    public void onStart() {
        super.onStart();
        // When we get back from the preference setting Activity, assume
        // settings have changed, and re-login with new auth info.
        initializeManager();
    }
    
    public void onMyButtonClick(View view){
    	//UserSettingsFragment fragment = (UserSettingsFragment) getSupportFragmentManager().findFragmentById(R.id.user_settings_view);
    	
    	Toast.makeText(this, "POTOI " + username, Toast.LENGTH_SHORT).show();
    }
    
    public void onRegisterClick(View view){
    	//UserSettingsFragment fragment = (UserSettingsFragment) getSupportFragmentManager().findFragmentById(R.id.user_settings_view);
    	initiateProfile();
    	//Toast.makeText(this, "POTOI " + username, Toast.LENGTH_SHORT).show();
    }
    
    public void click_register(View view){
    	//Toast.makeText(this, "Register clicked!", Toast.LENGTH_SHORT).show();
    	//Intent intent = new Intent(this, RegisterActivity.class);
    	//startActivity(intent);
    	
    	//Replace one fragment with another
    	
        // Create an instance of UserSettingsFragment
    	UserSettingsFragment usersettings = new UserSettingsFragment();

    	FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    	// Replace whatever is in the fragment_container view with this fragment,
    	// and add the transaction to the back stack so the user can navigate back
    	transaction.replace(R.id.container, usersettings);
    	transaction.addToBackStack(null);

    	// Commit the transaction
    	transaction.commit();
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
    	menu.add(0, USER_SETTINGS, 0, "User Settings");
    	menu.add(0, ADVANCED_SETTINGS, 0, "Advanced Settings");
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case USER_SETTINGS:
        	//
        	
        	break;
        case ADVANCED_SETTINGS:
        	//
        	break;
        }
        return true;
    }

    public void setData(String u, String  p, String  d , String s){
    	this.username = u;
    	this.pass = p;
    	this.domain = d;
    	this.server = s;
    }
    public void setUsername(String u){
    	this.username = u;
    }
    public void setPass(String p){
    	this.pass = p;
    }
    public void setDomain(String d){
    	this.domain = d;
    }
    public void setServer(String s){
    	this.server = s;
    }
    
    public void initializeManager() {
        if(manager == null) {
          manager = SipManager.newInstance(this);
        }
    }
    
    public void initiateProfile(){
    	/*if(username.length()==0 || pass.length()==0 || domain.length()==0 || server.length()==0){
    		Toast.makeText(this, "Update settings", Toast.LENGTH_SHORT).show();
    	}else{*/
	    	try {
	    		username = "alice";
	    		pass = "123456";
	    		domain = "192.168.1.6";
	    		server = "192.168.1.6";
	            SipProfile.Builder builder = new SipProfile.Builder(username, domain);
	            builder.setPassword(pass);
	            //builder.setAutoRegistration(false);
	            builder.setSendKeepAlive(true);
	            me = builder.build();
	            
	            Intent i = new Intent();
	            i.setAction("com.example.sip_try.INCOMING_CALL");
	            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, Intent.FILL_IN_DATA);
	            manager.open(me, pi, null);
	            
	            
	            manager.setRegistrationListener(me.getUriString(), new SipRegistrationListener() {
	                public void onRegistering(String localProfileUri) {
	                	Log.d(LCAT, "registrando");
	                	//Toast.makeText(getBaseContext(), "Registrando " + username, Toast.LENGTH_SHORT).show();
	                }
	
	                public void onRegistrationDone(String localProfileUri, long expiryTime) {
	                	Log.d(LCAT, "registrado");
	                	//Toast.makeText(getBaseContext(), "Registrado " + username, Toast.LENGTH_SHORT).show();
	                }
	
	                public void onRegistrationFailed(String localProfileUri, int errorCode,
	                        String errorMessage) {
	                	Log.e(LCAT, "Register FAILED" + errorCode + " " + errorMessage);
	                	//Toast.makeText(getBaseContext(), "No Registrado " + username, Toast.LENGTH_SHORT).show();
	                }
	            });
	            //manager.register(me, 30, null);
	            Toast.makeText(this, "Initiated... " + username, Toast.LENGTH_SHORT).show();
	    	}catch(ParseException pe){
	    		Toast.makeText(this, "ERROR 1", Toast.LENGTH_SHORT).show();
	        }catch (SipException se) {
	        	Toast.makeText(this, "ERROR 2 " + se.toString(), Toast.LENGTH_SHORT).show();
	        	//Log.e(LCAT, "Register FAILED " + se.toString());
	        }
    	//}
    }
    
	@Override
	public void onSaveClick() {
		// TODO Auto-generated method stub
		//UserSettingsFragment fragment = (UserSettingsFragment) getSupportFragmentManager().findFragmentById(R.id.user_settings_view);
		EditText aux = (EditText) findViewById(R.id.edit_username);
		setUsername(aux.getText().toString());
		aux = (EditText) findViewById(R.id.edit_password);
		setPass(aux.getText().toString());
		aux = (EditText) findViewById(R.id.edit_domain);
		setDomain(aux.getText().toString());
		aux = (EditText) findViewById(R.id.edit_server);
		setServer(aux.getText().toString());
		
		//Toast.makeText(this, "POTOI " + username, Toast.LENGTH_SHORT).show();
		
		
	}   
    
	public void updateStatus(final String status) {
        // Be a good citizen.  Make sure UI changes fire on the UI thread.
        /*this.runOnUiThread(new Runnable() {
            public void run() {
                TextView labelView = (TextView) findViewById(R.id.sipLabel);
                labelView.setText(status);
            }
        });*/
    }
	public void updateStatus(SipAudioCall call) {
        String useName = call.getPeerProfile().getDisplayName();
        if(useName == null) {
          useName = call.getPeerProfile().getUserName();
        }
        updateStatus(useName + "@" + call.getPeerProfile().getSipDomain());
    }
	
}
