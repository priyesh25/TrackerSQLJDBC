package adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.List;

import model.Reminder;
import tracker.nus.edu.sg.track.R;

/**
 * Created by darryl on 7/12/2016.
 */
// http://androidopentutorials.com/android-sqlite-example/

public class ReminderListAdapter extends ArrayAdapter<Reminder> {
    private Context context;
    List<Reminder> reminders;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public ReminderListAdapter(Context context, List<Reminder> reminders) {
        super(context, R.layout.list_item, reminders);
        this.context = context;
        this.reminders = reminders;
    }

    private class ViewHolder {
        TextView evtIdTxt;
        TextView evtTitleTxt;
        TextView evtDateTxt;
        TextView evtDescTxt;
    }

    @Override
    public int getCount() {
        return reminders.size();
    }

    @Nullable
    @Override
    public Reminder getItem(int position) {
        return reminders.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return super.getItemId(position);
        return 0;
    }

    @Override
    public void add(Reminder object) {
        reminders.add(object);
        notifyDataSetChanged();
        super.add(object);
    }

    @Override
    public void remove(Reminder object) {
        reminders.remove(object);
        notifyDataSetChanged();
        super.remove(object);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.evtIdTxt = (TextView) convertView.findViewById(R.id.tvEmpName);
            holder.evtTitleTxt = (TextView) convertView.findViewById(R.id.tvEmpSal);
            holder.evtDateTxt = (TextView) convertView.findViewById(R.id.tvEvtDate);
            holder.evtDescTxt = (TextView) convertView.findViewById(R.id.tvEvtDesc);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Reminder reminder = (Reminder) getItem(position);
        holder.evtIdTxt.setText(reminder.getId() + "");
        holder.evtTitleTxt.setText(reminder.getEventTitle());
        holder.evtDateTxt.setText(formatter.format(reminder.getEventDate()));
        holder.evtDescTxt.setText(reminder.getEventDesc() + "");

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
