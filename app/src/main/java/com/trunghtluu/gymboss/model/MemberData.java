package com.trunghtluu.gymboss.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MemberData implements Parcelable {
    private String id;
    private String name;
    private String plan;

    public MemberData(String id, String name, String plan)
    {
        this.name = name;
        this.id = id;
        this.plan = plan;
    }

    protected MemberData(Parcel in) {
        id = in.readString();
        name = in.readString();
        plan = in.readString();
    }

    public static final Creator<MemberData> CREATOR = new Creator<MemberData>() {
        @Override
        public MemberData createFromParcel(Parcel in) {
            return new MemberData(in);
        }

        @Override
        public MemberData[] newArray(int size) {
            return new MemberData[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlan() {
        return plan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(plan);
    }
}
