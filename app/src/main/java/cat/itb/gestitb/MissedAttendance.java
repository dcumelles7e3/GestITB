package cat.itb.gestitb;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class MissedAttendance implements Parcelable {

    String name, subject;
    Date date;
    boolean justified;
    int subjNum;    //Used for the correct spinner position on the fragment

    public MissedAttendance() {
        this.name = "";
        this.subject = "";
        this.date = new Date();
        this.justified = false;
    }

    protected MissedAttendance(Parcel in) {
        name = in.readString();
        subject = in.readString();
        justified = in.readByte() != 0;
    }

    public static final Creator<MissedAttendance> CREATOR = new Creator<MissedAttendance>() {
        @Override
        public MissedAttendance createFromParcel(Parcel in) {
            return new MissedAttendance(in);
        }

        @Override
        public MissedAttendance[] newArray(int size) {
            return new MissedAttendance[size];
        }
    };

    public int getSubjNum() {
        return subjNum;
    }

    public void setSubjNum(int subjNum) {
        this.subjNum = subjNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isJustified() {
        return justified;
    }

    public void setJustified(boolean justified) {
        this.justified = justified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(subject);
        dest.writeByte((byte) (justified ? 1 : 0));
    }
}
