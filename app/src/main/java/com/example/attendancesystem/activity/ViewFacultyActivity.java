package com.example.attendancesystem.activity;



import com.example.attendancesystem.R;

import java.util.ArrayList;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancesystem.bean.FacultyBean;
import com.example.attendancesystem.db.DBAdapter;

public class ViewFacultyActivity extends Activity {

	ArrayList<FacultyBean> facultyBeanList;
	private ListView listView ;  
	private ArrayAdapter<String> listAdapter;

	DBAdapter dbAdapter = new DBAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_listview);

		listView=(ListView)findViewById(R.id.listview);
		final ArrayList<String> facultyList = new ArrayList<String>();

		TextView txt = findViewById(R.id.txt);
		txt.setText("Faculty List");


		facultyBeanList=dbAdapter.getAllFaculty();

		for(FacultyBean facultyBean : facultyBeanList)
		{
			String users = "Faculty ID: " + facultyBean.getFaculty_id()+"\nName: " + facultyBean.getFaculty_firstname()+" "+facultyBean.getFaculty_lastname()+"\nPhone: "+facultyBean.getFaculty_mobilenumber()+"\nAddress: "+facultyBean.getFaculty_address();
				
			facultyList.add(users);

		}

		listAdapter = new ArrayAdapter<String>(this, R.layout.view_faculty_list, R.id.labelF, facultyList);
		listView.setAdapter( listAdapter ); 

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {



				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewFacultyActivity.this);

				alertDialogBuilder.setTitle(getTitle()+" decision");
				alertDialogBuilder.setMessage("Are you sure You want to delete the faculty?");

				alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						facultyList.remove(position);
						listAdapter.notifyDataSetChanged();
						listAdapter.notifyDataSetInvalidated();   

						dbAdapter.deleteFaculty(facultyBeanList.get(position).getFaculty_id());
						facultyBeanList=dbAdapter.getAllFaculty();

						for(FacultyBean facultyBean : facultyBeanList)
						{
							String users = "Faculty ID: " + facultyBean.getFaculty_id()+"\nName: " + facultyBean.getFaculty_firstname()+" "+facultyBean.getFaculty_lastname()+"\nPhone: "+facultyBean.getFaculty_mobilenumber()+"\nAddress: "+facultyBean.getFaculty_address();
							facultyList.add(users);
						}
						
					}
					
				});
				alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// cancel the alert box and put a Toast to the user
						dialog.cancel();
						Toast.makeText(getApplicationContext(), "You choose cancel", Toast.LENGTH_LONG).show();
					}
				});

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();





				return false;
			}
		});




	}

}
