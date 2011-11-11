package com.paypal.uitest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ShowMyDialog extends Activity {
	
/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

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
            builder.setMessage("This will end the activity");
            builder.setCancelable(true);
            builder.setPositiveButton("I agree", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ShowMyDialog.this.finish();
                }
            });
            builder.setNegativeButton("No, no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"Activity will continue",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onCreateDialog(id);
    }

}
	
