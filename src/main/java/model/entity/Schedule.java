package model.entity;

import java.sql.Date;

public class Schedule {
    private int idTherapist;
    private Date date;
    private String timeSlot;
    private int reserved;

    // Getter and Setter methods

    public int getIdTherapist() {
        return idTherapist;
    }

    public void setIdTherapist(int idTherapist) {
        this.idTherapist = idTherapist;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
}
