package com.yan.phoneintercept.bean;

/**
 * Created by Administrator on 2016/5/11.
 */
public class RecoredListItemData {

    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private boolean ischecked;

    public RecoredListItemData(String number, String name, boolean ischecked) {
        this.ischecked = ischecked;
        this.number = number;
        this.name = name;
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
