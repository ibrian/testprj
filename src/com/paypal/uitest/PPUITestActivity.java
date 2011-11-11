package com.paypal.uitest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
//import android.widget.Button;
import android.view.View;
import android.widget.*;
import android.text.TextWatcher;
import android.text.Editable;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;


public class PPUITestActivity extends Activity implements TextWatcher{
	private Button loginBtn;
	private EditText emailInput;
	final String userName = "bcao";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loginBtn = (Button)findViewById(R.id.loginButton);
        loginBtn.setEnabled(false);
        emailInput = (EditText) findViewById(R.id.editText4Email);
        //  emailInput.addTextChangedListener(this);
        //emailInput.setText("liacao@paypal.com");
        emailInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {          
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                	if (checkEmailCorrect(emailInput.getText().toString()))
                	{
                		emailInput.setBackgroundColor(0xFFFFFFFF);
                		loginBtn.setEnabled(true);
                	}
                	else {// invalid email format
                		emailInput.setBackgroundColor(0xfff00000);
                   		loginBtn.setEnabled(false);
                	}
            }
        });
    }
    
	public void openMyDialog(View view) {
		showDialog(10);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
        switch (id) {
        case 10:
            // Create out AlterDialog
            Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Login failure. Do you like to exit?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	PPUITestActivity.this.finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"Activity will continue",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onCreateDialog(id);
    }

    // no longer used
    public void afterTextChanged(Editable s)
    {
    	if (checkEmailCorrect(s.toString()))
    	{
    		emailInput.setBackgroundColor(0xFFFFFFFF);
    		loginBtn.setEnabled(true);
    	}
    	else {
    		emailInput.setBackgroundColor(0xfff00000);
    		loginBtn.setEnabled(false);
    	}
    		
    }
    
    // We don't use these methods yet
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    public void onTextChanged(CharSequence s, int start, int before, int count) { }

    boolean checkEmailCorrect(String Email) {
        if(emailInput.length() == 0) {
            return false;
        }

        String pttn = "^\\D.+@.+\\.[a-z]+";
        Pattern p = Pattern.compile(pttn);
        Matcher m = p.matcher(Email);

        if(m.matches()) {
            return true;
        }
        return false;
    }
}