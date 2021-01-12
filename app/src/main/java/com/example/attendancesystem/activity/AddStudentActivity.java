package com.example.attendancesystem.activity;



import com.example.attendancesystem.R;
import com.example.attendancesystem.bean.StudentBean;
import com.example.attendancesystem.db.DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudentActivity extends Activity {

	Button registerButton;
	EditText textFirstName;
	EditText textLastName;

	EditText textcontact;
	EditText textaddress;
	Spinner spinnerbranch,spinneryear,spinnersem;
	String userrole,branch,year,sem;
	private String[] branchString = new String[] {"CSE","IT","ECE","CIVIL","MECH","EEE"};
	private String[] yearString = new String[] {"I","II","III","IV"};
	private String[] semString = new String[] {"I","II"};
	private String[] y1 = new String[] {"I","II"};
	private String[] y2 = new String[] {"III","IV"};
	private String[] y3 = new String[] {"V","VI"};
	private String[] y4 = new String[] {"VII","VIII"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_student);

		spinnerbranch=(Spinner)findViewById(R.id.spinnerdept);
		spinneryear=(Spinner)findViewById(R.id.spinneryear);
		spinnersem=(Spinner)findViewById(R.id.spinnersem);
		textFirstName=(EditText)findViewById(R.id.editTextFirstName);
		textLastName=(EditText)findViewById(R.id.editTextLastName);
		textcontact=(EditText)findViewById(R.id.editTextPhone);
		textaddress=(EditText)findViewById(R.id.editTextaddr);
		registerButton=(Button)findViewById(R.id.RegisterButton);

		spinnerbranch.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				branch =(String) spinnerbranch.getSelectedItem();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, branchString);
		adapter_branch
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerbranch.setAdapter(adapter_branch);

		spinneryear.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				year =(String) spinneryear.getSelectedItem();
				if(year.equals("I")){
					semString=y1;
				}else if(year.equals("II")){
					semString=y2;
				}else if(year.equals("III")){
					semString=y3;
				}else if(year.equals("IV")){
					semString=y4;
				}

				spinnersem.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View view,
											   int arg2, long arg3) {
						// TODO Auto-generated method stub
						((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
						sem =(String) spinnersem.getSelectedItem();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

				ArrayAdapter<String> adapter_sem = new ArrayAdapter<String>(AddStudentActivity.this,
						android.R.layout.simple_spinner_item, semString);
				adapter_sem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnersem.setAdapter(adapter_sem);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, yearString);
		adapter_year
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinneryear.setAdapter(adapter_year);



		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//......................................validation
				String first_name = textFirstName.getText().toString();
				String last_name = textLastName.getText().toString();
				String phone_no = textcontact.getText().toString();
				String address = textaddress.getText().toString();

				if (TextUtils.isEmpty(first_name)) {
					textFirstName.setError("please enter firstname");
				}

				else if (TextUtils.isEmpty(last_name)) {
					textLastName.setError("please enter lastname");
				}
				else if (TextUtils.isEmpty(phone_no)) {
					textcontact.setError("please enter phoneno");
				}

				else if (TextUtils.isEmpty(address)) {
					textaddress.setError("enter address");
				}				
				else { 
					
					StudentBean studentBean = new StudentBean();
					
					studentBean.setStudent_firstname(first_name);
					studentBean.setStudent_lastname(last_name);
					studentBean.setStudent_mobilenumber(phone_no);
					studentBean.setStudent_address(address);
					studentBean.setStudent_department(branch);
					studentBean.setStudent_class(year);
					
					DBAdapter dbAdapter= new DBAdapter(AddStudentActivity.this);
					dbAdapter.addStudent(studentBean);
					finish();
					Toast.makeText(getApplicationContext(), "student added successfully", Toast.LENGTH_SHORT).show();

				}
			}
		});
	}

}
