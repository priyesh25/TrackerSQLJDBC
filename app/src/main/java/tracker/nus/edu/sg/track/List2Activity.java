package tracker.nus.edu.sg.track;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.EmployeeListAdapter;
import adapter.ReminderListAdapter;
import fao.ManageEmployee;
import model.Employee;

public class List2Activity extends AppCompatActivity {

    ListView employeeListView;
    List<Employee> employees;

    ManageEmployee mEmployee;
    EmployeeListAdapter employeeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        findViewsById();

        mEmployee = new ManageEmployee(this);
        mEmployee.loadEmployees("employee.txt");
        employees = mEmployee.getEmployeeList();

        if (employees != null) {
            if (employees.size() != 0) {
                employeeListAdapter = new EmployeeListAdapter(getApplicationContext(), employees);
                employeeListView.setAdapter(employeeListAdapter);
            } else {
                Toast.makeText(this, "No Employee", Toast.LENGTH_LONG).show();
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ///getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        // return super.onCreateView(parent, name, context, attrs);

        View view = getLayoutInflater().inflate(R.layout.activity_list2, null, false);

        if (employees != null) {
            if (employees.size() != 0) {
                employeeListAdapter = new EmployeeListAdapter(getApplicationContext(), employees);
                employeeListView.setAdapter(employeeListAdapter);
            } else {
                Toast.makeText(this, "No Employee", Toast.LENGTH_LONG).show();
            }
        }
        return view;
    }
*/

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.activity_list2, container, false);

        if (employees != null) {
            if (employees.size() != 0) {
                employeeListAdapter = new EmployeeListAdapter(getApplicationContext(), employees);
                employeeListView.setAdapter(employeeListAdapter);
            } else {
                Toast.makeText(this, "No Employee", Toast.LENGTH_LONG).show();
            }
        }

        return view;
    }
    */

    private void findViewsById() {
        employeeListView = (ListView) findViewById(R.id.list_emp);
    }
}
