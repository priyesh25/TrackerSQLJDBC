package tracker.nus.edu.sg.track;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SearchView;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.SearchAdapter;
import model.Employee;

public class SearchViewActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    SearchAdapter adapter;
    SearchView editsearch;
    String[] employeeNameList;
    ArrayList<Employee> arraylist = new ArrayList<Employee>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Generate sample data
        employeeNameList = new String[]{"Darryl", "Eddie", "Flora",
                "Gloria", "Herman", "Ignatius", "Justin", "Kenny",
                "Lackern","Morris","Nancy"};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.lvSearch);

        for (int i = 0; i < employeeNameList.length; i++) {
            Employee empName = new Employee();
            empName.setName(employeeNameList[i]);
            // Binds all strings into an array
            arraylist.add(empName);
        }

        // Pass results to SearchAdapter Class
        adapter = new SearchAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate SearchView
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
