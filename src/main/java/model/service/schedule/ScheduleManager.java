package model.service.schedule;

import model.DAO.DAOSchedule;
import model.entity.Schedule;

import java.sql.Date;
import java.util.List;

/**
 * Questa classe provvede alle funzionalità per la gestione dell'agenda e prenotazioni
 */
public class ScheduleManager implements ScheduleManagerInterface {
    DAOSchedule db;

    public ScheduleManager() {
        this.db = new DAOSchedule();}


    private Date convStoD(String Date){
        return java.sql.Date.valueOf(Date);
    }

    public void createNewSchedule(int idTherapist, String date, String timeSlot){
        db.createNewSchedule(idTherapist, convStoD(date), timeSlot);
    }

    public void modifySchedule(int idTherapist, String date, String timeSlot, String ndate,String ntimeSlot, int reserved){
        db.modifySchedule(idTherapist, convStoD(date), timeSlot, reserved, convStoD(ndate), ntimeSlot);
    }

    public void deleteSchedule(int idTherapist, String date, String timeSlot){
        db.deleteSchedule(idTherapist, convStoD(date), timeSlot);
    }
    
    public List<Schedule> retrieveAllPatientSchedules(int reserved){
        return db.retrieveAllPatientSchedules(reserved);
    }

    public List<Schedule> retrieveAllTherapistSchedules(int idTherapist){
        return db.retrieveAllTherapistSchedules(idTherapist);
    }

    public List<Schedule> retrieveAllPrenotedSchedules(int idTherapist){
        return db.retrieveAllPrenotedSchedules(idTherapist);
    }

    public List<Schedule> retrieveAllNotPrenotedSchedules(int idTherapist){
        return db.retrieveAllNotPrenotedSchedules(idTherapist);
    }

    public int retrieveAllPrenotedSchedulesCount(int idTherapist){
        return db.retrieveAllPrenotedSchedulesCount(idTherapist);
    }

    public boolean checkData(int idTherapist, String date, String timeSlot) {
        return db.checkData(idTherapist, convStoD(date), timeSlot);
    }
}
