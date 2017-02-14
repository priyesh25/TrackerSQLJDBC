package adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Reminder;
import tracker.nus.edu.sg.track.R;

/**
 * Created by Darryl on 6/1/2017.
 */

public class EmployeeListAdapter extends ArrayAdapter<Employee> {
    private Context context;
    List<Employee> employees;

    public EmployeeListAdapter(Context context, List<Employee> employees) {
        super(context, R.layout.list_item2, employees);
        this.context = context;
        this.employees = employees;
    }

    private class ViewHolder {
        TextView empName;
        TextView empSalary;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Nullable
    @Override
    public Employee getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return super.getItemId(position);
        return 0;
    }

    @Override
    public void add(Employee object) {
        employees.add(object);
        notifyDataSetChanged();
        super.add(object);
    }

    @Override
    public void remove(Employee object) {
        employees.remove(object);
        notifyDataSetChanged();
        super.remove(object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmployeeListAdapter.ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item2, null);
            holder = new EmployeeListAdapter.ViewHolder();

            holder.empName = (TextView) convertView.findViewById(R.id.tvEmpName);
            holder.empSalary = (TextView) convertView.findViewById(R.id.tvEmpSal);

            convertView.setTag(holder);
        } else {
            holder = (EmployeeListAdapter.ViewHolder) convertView.getTag();
        }
        Employee employee = (Employee) getItem(position);
        holder.empName.setText(employee.getName() + "");
        holder.empSalary.setText(String.valueOf(employee.getSalary()));

        //return super.getView(position, convertView, parent);
        return convertView;
    }


}
