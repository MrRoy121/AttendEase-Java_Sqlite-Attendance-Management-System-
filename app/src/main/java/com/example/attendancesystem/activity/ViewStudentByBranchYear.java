package com.example.attendancesystem.activity;



import com.example.attendancesystem.R;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancesystem.bean.StudentBean;
import com.example.attendancesystem.context.RecyclerTouchListener;
import com.example.attendancesystem.db.DBAdapter;
import com.example.attendancesystem.models.perstudentAdapter;
import com.example.attendancesystem.models.student;

public class ViewStudentByBranchYear extends Activity {

	ArrayList<StudentBean> studentBeanList;
	private RecyclerView listView ;
	private ArrayAdapter<String> listAdapter;
	String branch;
	String year;
	List<student> mys;
	

	DBAdapter dbAdapter = new DBAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_student_list);

		listView=findViewById(R.id.listview);
		mys = new ArrayList<student>();


		 branch=getIntent().getExtras().getString("branch");
		 year =getIntent().getExtras().getString("year");

		studentBeanList=dbAdapter.getAllStudentByBranchYear(branch, year);

		for(StudentBean studentBean : studentBeanList)
		{
			mys.add(new student(String.valueOf(studentBean.getStudent_id()),String.valueOf(studentBean.getStudent_firstname()+" "+studentBean.getStudent_lastname()),String.valueOf(studentBean.getStudent_class()),studentBean.getStudent_department()));
		}


		perstudentAdapter adapter = new perstudentAdapter(mys);
		listView.setHasFixedSize(true);
		listView.setLayoutManager(new LinearLayoutManager(this));
		listView.setAdapter(adapter);
		listView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), listView, new RecyclerTouchListener.ClickListener() {
			@Override
			public void onClick(View view, int position) {
			}

			@Override
			public void onLongClick(View view, int position) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewStudentByBranchYear.this);

				alertDialogBuilder.setTitle(getTitle()+" decision");
				alertDialogBuilder.setMessage("Are you sure You want to delete the student?");

				alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						mys.clear();
						dbAdapter.deleteStudent(studentBeanList.get(position).getStudent_id());
						studentBeanList=dbAdapter.getAllStudentByBranchYear(branch, year);

						for(StudentBean studentBean : studentBeanList)
						{
							Log.e("TAG",String.valueOf(studentBean.getStudent_id()));
							mys.add(new student(String.valueOf(studentBean.getStudent_id()),String.valueOf(studentBean.getStudent_firstname()+" "+studentBean.getStudent_lastname()),String.valueOf(studentBean.getStudent_class()),studentBean.getStudent_department()));
						}
						perstudentAdapter adapter = new perstudentAdapter(mys);
						listView.setAdapter(adapter);
					}

				});

				alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// cancel the alert box and put a Toast to the user
						dialog.cancel();
						Toast.makeText(getApplicationContext(), "You choose cancel",
								Toast.LENGTH_LONG).show();
					}
				});

				AlertDialog alertDialog = alertDialogBuilder.create();
				// show alert
				alertDialog.show();



			}
		}));




	}


}
