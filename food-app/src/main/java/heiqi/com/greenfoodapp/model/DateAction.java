package heiqi.com.greenfoodapp.model;

import java.util.Date;

public class DateAction {
    private String id;
    private String theme;
    private String location;
    private String phone;
    private String time;
    private String consumption;
    private String introduction;
    private Date crateTime;
    private Date updateTime;
    private Boolean isDelete;

    public void setId(String id) {
        this.id = id;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getId() {

        return id;
    }

    public String getTheme() {
        return theme;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getTime() {
        return time;
    }

    public String getConsumption() {
        return consumption;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Boolean getDelete() {
        return isDelete;
    }
}
