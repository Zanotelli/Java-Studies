package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public long duration(){
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String updateDates(Date checkIn, Date checkOut){
        Date now = new Date();
        if(checkIn.before(now) || checkOut.before(now)){
            return "A data de check-in ou check-out não podem ser anteriores a data de agora!";
        } if(checkIn.after(checkOut)){
            return "A data de check-out não pode ser anterior a data de check-in!";
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        return null;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString(){
        return "Quarto " + roomNumber
                + ", Check-in: " + sdf.format(checkIn)
                + ", Check-out: " + sdf.format(checkOut)
                + ", Duração: " + duration() + " noites.";
    }

}
