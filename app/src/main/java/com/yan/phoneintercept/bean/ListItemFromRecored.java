package com.yan.phoneintercept.bean;

/**
 * Created by Administrator on 2016/5/11.
<<<<<<< HEAD
 */
public class ListItemFromRecored {


    private String  number;
=======
 * sdlkflhsdkf
 */
public class ListItemFromRecored {

    private String number;
>>>>>>> 5972359c7bd2ef5a59ec393bac778771e73e3267

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private long time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private boolean ischecked;

    public ListItemFromRecored(String number, String name, long time, boolean ischecked) {
        this.ischecked = ischecked;
        this.number = number;
        this.name = name;
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
}
