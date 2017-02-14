package fao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import model.Employee;
import util.FileOperation;

/**
 * Created by Darryl on 6/1/2017.
 */

public class ManageEmployee {

    private Context context;
    private FileOperation fo;

    public Employee employee;
    public List<Employee> lstEmployees;

    public ManageEmployee(Context con) {
        this.context = con;
        this.fo = new FileOperation(this.context);
    }

    public void saveEmployee(Employee emp, String fileName) {
        StringBuilder str = new StringBuilder();
        str.append(emp.getName())
                .append(",")
                .append(emp.getSalary())
                .append("\n");

        fo.writeFile(fileName, str.toString());
    }

    public void loadEmployees(String fileName) {

        lstEmployees = new ArrayList<Employee>();

        List<String> strEmpList = fo.readFile(fileName);
        for (String str : strEmpList) {
            String[] strList = str.split(",");
            if (strList.length > 1) {
                Employee empObj = new Employee();
                empObj.setName(strList[0]);
                empObj.setSalary(Double.parseDouble(strList[1]));

                lstEmployees.add(empObj);
            }
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<Employee> getEmployeeList() {
        return lstEmployees;
    }
}
