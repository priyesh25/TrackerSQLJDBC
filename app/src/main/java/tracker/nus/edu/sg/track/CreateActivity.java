package tracker.nus.edu.sg.track;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.text.InputType;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.Toast;

import dao.ReminderDAO;
import model.Reminder;
import tracker.nus.edu.sg.track.R;


// Reference
// http://www.journaldev.com/9708/android-asynctask-example-tutorial

public class CreateActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private Button bReset, bAdd;
    private EditText etxtTitle, etxtTime, etxtDesc;
    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;

    Reminder reminder = null;
    private ReminderDAO reminderDAO;
    private AddReminder task;

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //getActionBar().setHomeButtonEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        reminderDAO = new ReminderDAO(this);

        findViewsById();
        setListeners();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etxtWhen:
                datePickerDialog.show();
                break;
            case R.id.btnAdd:
                setReminder();

                task = new AddReminder(this);
                task.execute((Void) null);
                break;
            case R.id.btnReset:
                resetAllFields();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int actionPerformed = event.getAction();

        switch(actionPerformed) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
        }
        return false;
    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */

    private void findViewsById() {
        etxtTitle = (EditText) findViewById(R.id.etxtTitle);
        etxtTime = (EditText) findViewById(R.id.etxtWhen);
        etxtDesc = (EditText) findViewById(R.id.etxtDesc);
        etxtTime.setInputType(InputType.TYPE_NULL);

        bReset = (Button) findViewById(R.id.btnReset);
        bAdd = (Button) findViewById(R.id.btnAdd);
    }

    private void setListeners() {
        etxtTime.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateCalendar = Calendar.getInstance();
                        dateCalendar.set(year, monthOfYear, dayOfMonth);
                        etxtTime.setText(formatter.format(dateCalendar.getTime()));
                    }
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));

        bAdd.setOnClickListener(this);
        bReset.setOnClickListener(this);
    }

    private void setReminder() {
        reminder = new Reminder();
        reminder.setEventTitle(etxtTitle.getText().toString());
        reminder.setEventDesc(etxtDesc.getText().toString());

        if (dateCalendar != null)
            reminder.setEventDate(dateCalendar.getTime());
    }

    protected void resetAllFields() {
        etxtTitle.setText("");
        etxtTime.setText("");
        etxtDesc.setText("");
    }



    public class AddReminder extends AsyncTask<Void, Void, Long> {
        private final WeakReference<Activity> activityWeakRef;

        public AddReminder(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected Long doInBackground(Void... params) {
            long result = reminderDAO.save(reminder);
            return result;
        }

        @Override
        protected void onPostExecute(Long result) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                if (result != -1)
                    Toast.makeText(activityWeakRef.get(), "Reminder Saved", Toast.LENGTH_LONG).show();
            }
        }
    }

}
