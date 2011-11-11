// bcao 11/10/11
package com.paypal.uitest.test;
// Test initial string in EditText for login email id.

import android.test.ActivityInstrumentationTestCase2;
import com.paypal.uitest.PPUITestActivity;

import android.widget.EditText;
//import android.widget.TextView;

public class PPUITextEditTest extends ActivityInstrumentationTestCase2<PPUITestActivity> {
	 private PPUITestActivity mActivity;
	 private EditText mView;
	  private String resourceString;
	
	  public PPUITextEditTest() {
	      super("com.paypal.uitest", PPUITestActivity.class);
	    }
	  
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        mActivity = this.getActivity();
	        mView = (EditText) mActivity.findViewById(com.paypal.uitest.R.id.editText4Email);
	        resourceString = mActivity.getString(com.paypal.uitest.R.string.myemail);
	    }

	    public void testPreconditions() {
	        assertNotNull(mView);
	    }
	    
	    public void testText() {
	    	//mView.setText("liacao@paypal.com");
	    	sendKeys("L I A C A O @ P A Y P A L . C O M");
	        assertEquals(resourceString,(String)mView.getText().toString());
	     }
}

