package model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

/**
 * Created by darryl on 7/12/2016.
 */

public class Reminder implements Parcelable {

    private int id;
    private String eventTitle;
    private Date eventDate;
    private String eventDesc;

    public Reminder() {
        super();
    }

    private Reminder(Parcel in) {
        super();
        this.id = in.readInt();
        this.eventTitle = in.readString();
        this.eventDate = new Date(in.readLong());
        this.eventDesc = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String title) {
        this.eventTitle = title;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date evtDate) {
        this.eventDate = evtDate;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String evtDesc) {
        this.eventDesc = evtDesc;
    }

    @Override
    public String toString() {
        return "Reminder [id=" + id + ", title=" + eventTitle + ", date=" + eventDate + ", desc=" + eventDesc + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getEventTitle());
        dest.writeLong(getEventDate().getDate());
        dest.writeString(getEventDesc());
    }

    public static final Parcelable.Creator<Reminder> CREATOR = new Parcelable.Creator<Reminder>() {
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

}
