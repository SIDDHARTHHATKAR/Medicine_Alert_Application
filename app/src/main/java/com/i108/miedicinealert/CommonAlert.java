package com.i108.miedicinealert;

import com.i108.miedicinealert.constants.MessageTypes;
import com.i108.miedicinealert.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class CommonAlert {

	private AlertDialog.Builder builder;

	CommonAlert(Context context) {
		builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.alert_title);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
	}

	public void showMessage(int type) {
		switch(type) {
		case MessageTypes.EMPTY_NAME:
			builder.setMessage(R.string.empty_name_info);
			break;
		}
		builder.create().show();
	}
}
