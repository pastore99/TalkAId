package model.service.schedule;

import model.entity.Schedule;

import java.util.List;

/**
 * Interfaccia per l'agenda e prenotazioni
 */
public interface ScheduleManagerInterface {

    /**
     * Creation of a new schedule slot from the therapist.
     *
     * @param idTherapist   the ID of the therapist;
     * @param date          the date for the schedule;
     * @param timeSlot      the timeslot for the schedule.
     */
    void createNewSchedule(int idTherapist, String date, String timeSlot);

    /**
     * Modification of a existing schedule slot from the therapist.
     *
     * @param idTherapist   the ID of the therapist;
     * @param date          the date for the schedule;
     * @param timeSlot      the timeslot for the schedule;
     * @param ndate          the new date for the schedule;
     * @param ntimeSlot      the new timeslot for the schedule;
     * @param reserved      the ID of the patient, if 0 the prenotation is not reserved.
     */
    void modifySchedule(int idTherapist, String date, String timeSlot, String ndate,String ntimeSlot, int reserved);


    /**
     * Elimination of a existing schedule slot from the therapist.
     *
     * @param idTherapist   the ID of the therapist;
     * @param date          the date for the schedule;
     * @param timeSlot      the timeslot for the schedule.
     */
    void deleteSchedule(int idTherapist, String date, String timeSlot);
    
    /**
     * Retrieves all the schedules of a patient.
     * @param reserved      the ID of the patient, if 0 the prenotation is not reserved;
     * @return a list of schedule that the patient has signed on.
     */
    List<Schedule> retrieveAllPatientSchedules(int reserved);

    /**
     * Retrieves all the schedules from a therapist.
     * @param idTherapist   the ID of the therapist;
     * @return a list of schedule that the therapist has created.
     */
    List<Schedule> retrieveAllTherapistSchedules(int idTherapist);

    /**
     * Retrieves all the schedules from a therapist that are prenoted.
     * @param idTherapist   the ID of the therapist;
     * @return a list of schedule that the therapist has created.
     */
    List<Schedule> retrieveAllPrenotedSchedules(int idTherapist);

    /**
     * Retrieves all the schedules from a therapist that are not prenoted.
     * @param idTherapist   the ID of the therapist;
     * @return a list of schedule that the therapist has created.
     */
    List<Schedule> retrieveAllNotPrenotedSchedules(int idTherapist);

    /**
     * Retrieves the number of schedules from a therapist that are prenoted.
     * @param idTherapist   the ID of the therapist;
     * @return the number of schedule that the therapist has created.
     */
    int retrieveAllPrenotedSchedulesCount(int idTherapist);

    /**
     * checks if the data exists in the database.
     */
    boolean checkData(int idTherapist, String date, String timeSlot);
}
