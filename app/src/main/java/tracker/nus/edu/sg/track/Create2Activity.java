package tracker.nus.edu.sg.track;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import async.Test;
import fao.ManageEmployee;
import model.Employee;
import util.FileOperation;

// References
// http://alvinalexander.com/java/jwarehouse/android/core/tests/coretests/src/android/database/sqlite/SQLiteJDBCDriverTest.java.shtml


public class Create2Activity extends AppCompatActivity implements View.OnClickListener {

    /*
    static final String url = "jdbc:mysql://localhost:3306/test";
    static final String user = "root";
    static final String pass = "darryl1975";
    public static List<Employee> objList;
    */

    private EditText etName, etSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create2);

        etName = (EditText) findViewById(R.id.etName);
        etSalary = (EditText) findViewById(R.id.etSalary);
        Button bAdd = (Button) findViewById(R.id.btnAddEmployee);
        bAdd.setOnClickListener(this);

        //new Test(Create2Activity.this, url).execute(); //async task for getting data from db
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddEmployee) {
            Employee employee = new Employee();
            employee.setName(etName.getText().toString());
            employee.setSalary(Double.parseDouble(etSalary.getText().toString()));

            ManageEmployee mEmployee = new ManageEmployee(this);
            mEmployee.saveEmployee(employee, "employee.txt");
            finish();
        }
    }
}
