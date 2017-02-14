package tracker.nus.edu.sg.track;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import adapter.ReminderListAdapter;
import dao.ReminderDAO;
import model.Reminder;
import tracker.nus.edu.sg.track.R;

public class ListActivity extends AppCompatActivity {

    public static final String ARG_ITEM_ID = "reminder_list";

    Activity activity;
    ListView reminderListView;
    ArrayList<Reminder> reminders;

    ReminderListAdapter reminderListAdapter;
    ReminderDAO reminderDAO;

    private GetRemTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        reminderDAO = new ReminderDAO(this);

        findViewsById();

        task = new GetRemTask(this);
        task.execute((Void) null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                Snackbar.make(view, "www.journaldev.com", Snackbar.LENGTH_LONG).show();            }
        });
    }


/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.activity_list, container, false);


        //reminderListView.setOnItemClickListener(this);
        //reminderListView.setOnItemLongClickListener(this);
        // Reminder e = reminderDAO.getReminder(1);
        // Log.d("reminder e", e.toString());
        return view;
    }*/

    private void findViewsById() {
        reminderListView = (ListView) findViewById(R.id.list_rem);
    }


    public class GetRemTask extends AsyncTask<Void, Void, ArrayList<Reminder>> {
        private final WeakReference<Activity> activityWeakRef;

        public GetRemTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<Reminder> doInBackground(Void... arg0) {
            ArrayList<Reminder> reminderList = reminderDAO.getReminders();
            return reminderList;
        }

        @Override
        protected void onPostExecute(ArrayList<Reminder> remList) {
            if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()) {
                Log.d("reminders", remList.toString());
                reminders = remList;
                if (remList != null) {
                    if (remList.size() != 0) {
                        reminderListAdapter = new ReminderListAdapter(getApplicationContext(), remList);
                        reminderListView.setAdapter(reminderListAdapter);
                    } else {
                        Toast.makeText(activity, "No Reminders", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
