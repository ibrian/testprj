 package com.paypal.uitest.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.widget.EditText;
import com.paypal.uitest.PPUITestActivity;
import android.widget.Button;;

public class PPLoginUIActivityTest extends
		ActivityInstrumentationTestCase2<PPUITestActivity> {

	  private PPUITestActivity mLoginActivity;
	  private EditText mEmailEditText;
	  private EditText mPwdEditText;
	  private Button mLoginButton;
	  private String  resourceString;
	  
	
	  public PPLoginUIActivityTest() {
		    super("com.paypal.uitest", PPUITestActivity.class);
	  } // end of  constructor definition
 
	@Override
	  protected void setUp() throws Exception {
	    super.setUp();

	    setActivityInitialTouchMode(false);
	    mLoginActivity = getActivity();
	    mEmailEditText =
	      (EditText) mLoginActivity.findViewById(
	        com.paypal.uitest.R.id.editText4Email
	      );
	    
	    mPwdEditText =
	  	      (EditText) mLoginActivity.findViewById(
	  	        com.paypal.uitest.R.id.editText4PassWord
	  	      );
	    mLoginButton =
	  	      (Button) mLoginActivity.findViewById(
	  	        com.paypal.uitest.R.id.loginButton
	  	      );
	    resourceString = mLoginActivity.getString(com.paypal.uitest.R.string.myemail);
	  } // end of setUp() method definition */
	
	 public void testPreConditions() {
		    assertTrue(mEmailEditText != null);
		    assertTrue(mPwdEditText != null);
		    assertTrue(mLoginButton != null);
		 } // end of testPreConditions() method definition
	 
	 
	 
	 public void testCheckLoginButtonAtStart() 
	 {
		 Assert.assertFalse(mLoginButton.isEnabled());//assertFalse test
	 }
	 
	 public void testEmailEditTextEntry() {
		 mLoginActivity.runOnUiThread(
			      new Runnable() {
			        public void run() {
			          //mEmailEditText.requestFocus(); not needed due to default 			          
			          mEmailEditText.setText("liacao@paypal.com");
			          mPwdEditText.requestFocus();
			          //mSpinner.setSelection(INITIAL_POSITION);
			        } // end of run() method definition
			      } // end of anonymous Runnable object instantiation
			    ); // end of invocation of runOnUiThread
		    //sendKeys("DPAD_LEFT l i c a o @ p a y p a l . c o m DPAD_CENTER"); //symbols not working
		 	this.getInstrumentation().waitForIdleSync();
	        assertEquals(resourceString,(String)mEmailEditText.getText().toString());
	 }
	    
	 public void testUIEmailEditTextAndLoginButton() 
	 {
		    mLoginActivity.runOnUiThread(
		      new Runnable() {
		        public void run() {
		          //mEmailEditText.requestFocus();
		          //sendKeys("L I A C A O @ P A Y P A L . c o m"); //symbols not working
		          mEmailEditText.setText("liacao@paypal.com");
		          mPwdEditText.requestFocus();
		          //mSpinner.setSelection(INITIAL_POSITION);
		        } // end of run() method definition
		      } // end of anonymous Runnable object instantiation
		    ); // end of invocation of runOnUiThread
		   
	        //sendKeys(k. A B C DPAD_CENTER");
		    this.getInstrumentation().waitForIdleSync();
		    String eMailStr = mEmailEditText.getText().toString();
		    //System.out.println(" *******  EmailEditText = " + eMailStr );
		    //Log.i("My Tag", "test tag info");
		   //if (checkEmailCorrect(eMailStr))
	    	{    		
	    		Assert.assertTrue(mLoginButton.isEnabled());
	    	}
	    	/*else {
	    		//assertEquals(false,  mLoginButton.isEnabled());
	    		Assert.assertFalse(mLoginButton.isEnabled());
	    	}*/
	 } // end of testSpinnerUI() method definition
	 
	 
	  boolean checkEmailCorrect(String Email) {
	        if(mEmailEditText.length() == 0) {
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
	  
	  public void testStateDestroy() {
		  //shutdown and restart
		    //mActivity.setSpinnerPosition(TEST_STATE_DESTROY_POSITION);
		    mLoginActivity.runOnUiThread(
				      new Runnable() {
				        public void run() {
				        	// changed to email instead of setting button
				        	 mLoginButton.setEnabled(true); //(TEST_STATE_DESTROY_SELECTION);
				          //mSpinner.setSelection(INITIAL_POSITION);
				        } // end of run() method definition
				      } // end of anonymous Runnable object instantiation
				    ); // end of invocation of runOnUiThread
		   
		    mLoginActivity.finish();
		    mLoginActivity = this.getActivity();
		    this.getInstrumentation().waitForIdleSync();
		    Assert.assertTrue(mLoginButton.isEnabled());
	  }

	  @UiThreadTest
	    public void testStatePause() {
		  Instrumentation mInstr = this.getInstrumentation();
		  mLoginActivity.runOnUiThread(
			      new Runnable() {
			        public void run() {
			        	 mLoginButton.setEnabled(true); //(TEST_STATE_DESTROY_SELECTION);
			          //mSpinner.setSelection(INITIAL_POSITION);
			        } // end of run() method definition
			      } // end of anonymous Runnable object instantiation
			    ); // end of invocation of runOnUiThread
	      // this.getInstrumentation().waitForIdleSync();
		    mInstr.callActivityOnPause(mLoginActivity);
		   // mActivity.setSpinnerPosition(0);
		    //mActivity.setSpinnerSelection("");
		    mInstr.callActivityOnResume(mLoginActivity);
		    Assert.assertTrue(mLoginButton.isEnabled());
	  } // end of testStatePause() method definition */
}
