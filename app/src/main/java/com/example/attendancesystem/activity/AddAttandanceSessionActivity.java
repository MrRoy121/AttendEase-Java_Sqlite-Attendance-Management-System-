package com.example.attendancesystem.activity;


import com.example.attendancesystem.R;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.attendancesystem.bean.AttendanceBean;
import com.example.attendancesystem.bean.AttendanceSessionBean;
import com.example.attendancesystem.bean.FacultyBean;
import com.example.attendancesystem.bean.StudentBean;
import com.example.attendancesystem.context.ApplicationContext;
import com.example.attendancesystem.db.DBAdapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddAttandanceSessionActivity<AddAttandanceActivity> extends Activity {

	private ImageButton date;
	private Calendar cal;
	private int day;
	private int month;
	private int dyear;
	private EditText dateEditText;
	Button submit;
	Button viewAttendance;
	Button viewTotalAttendance;
	Spinner spinnerbranch,spinneryear,spinnerSubject,spinnersem;
	String branch = "cse";
	String year = "SE", sem;
	String subject = "SC";
	Button addStudent;
	Button viewStudent;
	Button attendancePerStudent;

	Button logout;

	private String[] branchString = new String[] { "CSE","IT","ECE","CIVIL","MECH","EEE"};
	private String[] yearString = new String[] {"I","II","III","IV"};

	private String[] subjectFinal = new String[] {"M3","DS","M4","CN","M5","NS"};
	private String[] it3year3sem5 = new String[] {"DAA","ML","DM","DS","NLP","ES","ES LAB","DAA LAB","MIN PROJ LAB"};

	private String[] semString = new String[] {"I","II"};
	private String[] y1 = new String[] {"I","II"};
	private String[] y2 = new String[] {"III","IV"};
	private String[] y3 = new String[] {"V","VI"};
	private String[] y4 = new String[] {"VII","VIII"};


	AttendanceSessionBean attendanceSessionBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_attendance);

		//Assume subject will be SE
		//subjectFinal = subjectSEString;

		spinnerbranch=(Spinner)findViewById(R.id.spinner1);
		spinneryear=(Spinner)findViewById(R.id.spinneryear);
		spinnerSubject=(Spinner)findViewById(R.id.spinnerSE);
		spinnersem=(Spinner)findViewById(R.id.spinnersem);
		addStudent =(Button)findViewById(R.id.buttonaddstudent);
		viewStudent =(Button)findViewById(R.id.buttonViewstudent);
		logout =(Button)findViewById(R.id.buttonlogout);



		addStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent =new Intent(AddAttandanceSessionActivity.this,AddStudentActivity.class);
				startActivity(intent);
			}
		});
		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent =new Intent(AddAttandanceSessionActivity.this,MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		viewStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent =new Intent(AddAttandanceSessionActivity.this,ViewStudentActivity.class);
				startActivity(intent);
			}
		});
		attendancePerStudent=(Button)findViewById(R.id.attendancePerStudentButton);
		attendancePerStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
				ArrayList<AttendanceBean> attendanceBeanList=dbAdapter.getAllAttendanceByStudent();
				((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

				Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendancePerStudentActivity.class);
				startActivity(intent);

			}
		});
		ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, branchString);
		adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerbranch.setAdapter(adapter_branch);
		spinnerbranch.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				branch =(String) spinnerbranch.getSelectedItem();


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
								if(branch.equals("IT")&&sem.equals("V")){
									subjectFinal = new String[] {};
									subjectFinal=it3year3sem5;
								}else{
									subjectFinal = new String[] {"M3","DS","M4","CN","M5","NS"};
								}

								ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(AddAttandanceSessionActivity.this, android.R.layout.simple_spinner_item, subjectFinal);
								adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
								spinnerSubject.setAdapter(adapter_subject);
								spinnerSubject.setOnItemSelectedListener(new OnItemSelectedListener() {
									@Override
									public void onItemSelected(AdapterView<?> arg0, View view,
															   int arg2, long arg3) {
										((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
										subject =(String) spinnerSubject.getSelectedItem();

									}

									@Override
									public void onNothingSelected(AdapterView<?> arg0) {
										// TODO Auto-generated method stub
									}
								});

							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub
							}
						});

						ArrayAdapter<String> adapter_sem = new ArrayAdapter<String>(AddAttandanceSessionActivity.this,
								android.R.layout.simple_spinner_item, semString);
						adapter_sem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						spinnersem.setAdapter(adapter_sem);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, yearString);
		adapter_year
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinneryear.setAdapter(adapter_year);



		date = (ImageButton) findViewById(R.id.DateImageButton);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		dyear = cal.get(Calendar.YEAR);
		dateEditText = (EditText) findViewById(R.id.DateEditText);
		date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog(0);

			}
		});

		submit=(Button)findViewById(R.id.buttonsubmit);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
				FacultyBean bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

				attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
				attendanceSessionBean.setAttendance_session_department(branch);
				attendanceSessionBean.setAttendance_session_class(year);
				attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
				attendanceSessionBean.setAttendance_session_subject(subject);

				DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
				int sessionId=	dbAdapter.addAttendanceSession(attendanceSessionBean);

				ArrayList<StudentBean> studentBeanList=dbAdapter.getAllStudentByBranchYear(branch, year);
				((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setStudentBeanList(studentBeanList);


				Intent intent = new Intent(AddAttandanceSessionActivity.this,AddAttendanceActivity.class);
				intent.putExtra("sessionId", sessionId);
				startActivity(intent);
			}
		});
		
		viewAttendance=(Button)findViewById(R.id.viewAttendancebutton);
		viewAttendance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
				FacultyBean bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

				attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
				attendanceSessionBean.setAttendance_session_department(branch);
				attendanceSessionBean.setAttendance_session_class(year);
				attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
				attendanceSessionBean.setAttendance_session_subject(subject);

				DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
				
				ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getAttendanceBySessionID(attendanceSessionBean);
				((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
				
				Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendanceByFacultyActivity.class);
				startActivity(intent);
				
			}
		});
		
		viewTotalAttendance=(Button)findViewById(R.id.viewTotalAttendanceButton);
		viewTotalAttendance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
				FacultyBean bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

				attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
				attendanceSessionBean.setAttendance_session_department(branch);
				attendanceSessionBean.setAttendance_session_class(year);
				attendanceSessionBean.setAttendance_session_subject(subject);

				DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
				
				ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getTotalAttendanceBySessionID(attendanceSessionBean);
				((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
				
				Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendanceByFacultyActivity.class);
				startActivity(intent);
				
			}
		});
	}
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, dyear, month, day);
	}
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
					+ selectedYear);
		}
	};

}
