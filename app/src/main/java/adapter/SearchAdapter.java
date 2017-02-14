package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.Employee;
import tracker.nus.edu.sg.track.R;

// Reference
// http://abhiandroid.com/ui/searchview

public class SearchAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;

    private List<Employee> employeeNamesList = null;
    private ArrayList<Employee> arraylist;

    public SearchAdapter(Context context, List<Employee> empNamesList) {
        mContext = context;
        this.employeeNamesList = empNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Employee>();
        this.arraylist.addAll(empNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return employeeNamesList.size();
    }

    @Override
    public Employee getItem(int position) {
        return employeeNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row_item, null);

            // Locate the TextViews in row_item.xml
            holder.name = (TextView) view.findViewById(R.id.stringName);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Set the results into TextViews
        holder.name.setText(employeeNamesList.get(position).getName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        employeeNamesList.clear();

        if (charText.length() == 0) {
            employeeNamesList.addAll(arraylist);
        } else {
            for (Employee wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    employeeNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
