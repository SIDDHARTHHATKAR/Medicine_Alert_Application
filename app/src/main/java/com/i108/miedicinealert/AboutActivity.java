package com.i108.miedicinealert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.i108.miedicinealert.register_login.DBHelper;
import com.i108.miedicinealert.register_login.login_page;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity {

	Logger LOG = LoggerFactory.getLogger(AboutActivity.class);
	Button logo;
	DBHelper dbHelper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LOG.trace("[Create] AboutActivity");
		setContentView(R.layout.activity_about);

		dbHelper = new DBHelper(this);
		logo = (Button) findViewById(R.id.logout);
		TextView tv_version = (TextView) findViewById(R.id.tv_version);
	
		TextView tv_see_apps = (TextView) findViewById(R.id.tv_see_apps);
		

		try {
			PackageInfo pInfo = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			tv_version.setText(getResources().getString(R.string.version) + " "
					+ pInfo.versionName);
		} catch (NameNotFoundException e) {
			LOG.warn("NameNotFoundException: get package name", e);
		}

		logo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				final AlertDialog.Builder builder = new AlertDialog.Builder(AboutActivity.this);
				builder.setTitle("Info");
				builder.setMessage("Are you sure you want to logout ??");
				builder.setPositiveButton("Yes I'm sure", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {

						Intent intent = new Intent(AboutActivity.this, login_page.class);
						startActivity(intent);
						finish();
					}
				});

				builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						dialogInterface.dismiss();
					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
		
		tv_see_apps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	

	}

	@Override
	protected void onStart() {
		LOG.trace("[Start] AboutActivity");
		GoogleAnalytics.getInstance(getBaseContext()).reportActivityStart(this);
		super.onStart();
	}

	@Override
	protected void onStop() {
		LOG.trace("[Stop] AboutActivity");
		GoogleAnalytics.getInstance(getBaseContext()).reportActivityStop(this);
		super.onStop();
	}

}
