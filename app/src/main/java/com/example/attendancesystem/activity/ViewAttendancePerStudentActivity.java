package com.example.attendancesystem.activity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancesystem.R;
import com.example.attendancesystem.bean.AttendanceBean;
import com.example.attendancesystem.bean.StudentBean;
import com.example.attendancesystem.context.ApplicationContext;
import com.example.attendancesystem.db.DBAdapter;
import com.example.attendancesystem.models.perstudentAdapter;
import com.example.attendancesystem.models.student;

public class ViewAttendancePerStudentActivity extends Activity {

	ArrayList<AttendanceBean> attendanceBeanList;
	private RecyclerView listView ;
	EditText clss;
	LinearLayout lot;
	int x = 1;
	List<student>mys;
	DBAdapter dbAdapter = new DBAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_attendance_per_student);

		clss = findViewById(R.id.clss);
		lot = findViewById(R.id.lot);
		listView= findViewById(R.id.listview);
		//ArrayList<String> attendanceList = new ArrayList<String>();
		mys = new ArrayList<student>();
		lot.setVisibility(View.VISIBLE);
		TextView tt = findViewById(R.id.tt);
		tt.setText("Per Student Attendance Lists");
		TextView ttt = findViewById(R.id.ttt);
		ttt.setText("Provide the total number of classes to see the attendance percentage.");
		attendanceBeanList=((ApplicationContext)ViewAttendancePerStudentActivity.this.getApplicationContext()).getAttendanceBeanList();

		findViewById(R.id.cal).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(!(clss.getText().length() ==0)){
					x = Integer.parseInt(clss.getText().toString());
					String users = "",id = "";
					mys.clear();
					for(AttendanceBean attendanceBean : attendanceBeanList)
					{
						DBAdapter dbAdapter = new DBAdapter(ViewAttendancePerStudentActivity.this);
						StudentBean studentBean =dbAdapter.getStudentById(attendanceBean.getAttendance_student_id());
						DecimalFormat df = new DecimalFormat("##.00");
						Double d = (Double.parseDouble(String.valueOf(attendanceBean.getAttendance_session_id()))/x)*100;
						id = String.valueOf(attendanceBean.getAttendance_student_id());
						mys.add(new student(id,String.valueOf(studentBean.getStudent_firstname()+" "+studentBean.getStudent_lastname()),String.valueOf(attendanceBean.getAttendance_session_id()),String.valueOf(df.format(d))));

					}

					perstudentAdapter adapter = new perstudentAdapter(mys);
					listView.setAdapter(adapter);
				}else{
					Toast.makeText(ViewAttendancePerStudentActivity.this, "Please Provide the Total Class Number!!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		for(AttendanceBean attendanceBean : attendanceBeanList)
		{
			String id = "";
			DBAdapter dbAdapter = new DBAdapter(ViewAttendancePerStudentActivity.this);
			StudentBean studentBean =dbAdapter.getStudentById(attendanceBean.getAttendance_student_id());
			id = String.valueOf(attendanceBean.getAttendance_student_id());
			mys.add(new student(id,String.valueOf(studentBean.getStudent_firstname()+" "+studentBean.getStudent_lastname()),String.valueOf(attendanceBean.getAttendance_session_id()),""));

		}

		perstudentAdapter adapter = new perstudentAdapter(mys);
		listView.setHasFixedSize(true);
		listView.setLayoutManager(new LinearLayoutManager(this));
		listView.setAdapter(adapter);

	}
}
