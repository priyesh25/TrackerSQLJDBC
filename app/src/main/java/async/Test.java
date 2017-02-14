package async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.lang.*;

import model.Employee;
import tracker.nus.edu.sg.track.Create2Activity;

import static android.R.id.list;

/**
 * Created by darryl on 10/12/2016.
 */

public class Test extends AsyncTask<Void, Void, String> {
    Context context;
    private String url;

    public Test(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            java.sql.Connection con = DriverManager.getConnection(url, "root", "darryl1975");
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from employee");
            //list = new ArrayList<Employee>();

            while (rs.next()) {
                int field1= rs.getInt("empid");
                String field2 = rs.getString("empname");
                Double field3 = rs.getDouble("salary");

                Employee emp = new Employee();
                emp.setId(field1);
                emp.setName(field2);
                emp.setSalary(field3);

                //Create2Activity.objList.add(emp);

                Toast.makeText(this.context, "Success", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Toast.makeText(this.context, "Failed" + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast.makeText(this.context, "Failed" + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            rs.close();
//            st.close();
//            if (con != null) {
//                con.close();
//            }
//        }
        return "Complete";
    }
}
