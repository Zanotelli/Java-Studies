package application;

import entities.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    /**
     * VARY BAD solution for handling Exceptions
     */
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Número do quarto: ");
        int roomNumber = sc.nextInt();
        System.out.print("Data de check-in (dd/MM/yyyy): ");
        Date checkIn = sdf.parse(sc.next());
        System.out.print("Data de check-out (dd/MM/yyyy): ");
        Date checkOut = sdf.parse(sc.next());

        if(checkIn.after(checkOut)){
            System.out.println("\nERROR: A data de check-out não pode ser anterior a data de check-in!");
        } else {
            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println("\nReserva: " + reservation);


            System.out.print("Atualize seu check-in (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());
            System.out.print("Atualize seu check-out (dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());

            Date now = new Date();
            if(checkIn.before(now) || checkOut.before(now)){
                System.out.println("\nERROR: A data de check-in ou check-out não podem ser anteriores a data de agora!");
            } else if(checkIn.after(checkOut)){
                System.out.println("\nERROR: A data de check-out não pode ser anterior a data de check-in!");
            } else {
                reservation.updateDates(checkIn, checkOut);     //don't handle exceptions
                System.out.println("\nReserva: " + reservation);
            }

        }

        sc.close();
    }
}