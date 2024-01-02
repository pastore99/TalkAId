package model.entity;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;

public class License {
    private String sequence;
    private int idUser;
    private Date expirationDate;
    private boolean active;

    public License() {}
    //Constructor for creating a pin or a license based on n
    public License(int n){
        this.sequence = generatePin(n);
        if (n == 4){
            LocalDate d = LocalDate.now();
            d = d.plusDays(7);
            this.expirationDate = java.sql.Date.valueOf(d);
        }
    }
    // Getter and Setter methods

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private String generatePin(int n){
        SecureRandom random = new SecureRandom();

        String digits;

        if(n==4){
            digits = "0123456789";
        }else{
            digits = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        }

        StringBuilder pin = new StringBuilder();
        for(int i = 0; i < n; i++) {
            pin.append(digits.charAt(random.nextInt(digits.length())));
        }

        return pin.toString();
    }
}
