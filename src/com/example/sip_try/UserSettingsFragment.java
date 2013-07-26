package com.example.sip_try;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserSettingsFragment extends Fragment implements OnClickListener{
	public int id_frag = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.user_settings_view);
	}

	UserSettingsInterface fragmentCallback;
	
	public interface UserSettingsInterface {
		public void onSaveClick();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.user_settings_view, container, false);
		Button b = (Button) v.findViewById(R.id.register_2);
		b.setOnClickListener((OnClickListener) this);
		return v;
	}
	
	@Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.register_2:
        	onButtonClick();
            break;
        }
    }
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
        try {
        	fragmentCallback = (HomeActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement the fragment interface method");
        }
		super.onAttach(activity);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
        // During startup, check if there are arguments passed to the fragment.
        /*Bundle args = getArguments();
        if (args != null) {            
        }*/
		super.onStart();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	public void onButtonClick(){
		//Context app;  
		//Toast.makeText(app.getApplicationContext(), "POTOI", Toast.LENGTH_SHORT).show();
		fragmentCallback.onSaveClick();
		/*EditText aux = (EditText) getActivity().findViewById(R.id.edit_username);
		String hola = "";  
		hola.concat("HOLA " + this.getId());
		aux.setHint(hola);*/
	}
}
