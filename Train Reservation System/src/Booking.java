import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Booking {

    String passengerName;
    int trainNo;
    Date date;

    Booking(){
        Scanner scan = new Scanner(System.in);
        System.out.print("                      Enter name of Passenger : ");
        passengerName = scan.next();
        System.out.print("                      Enter Train No :");
        trainNo = scan.nextInt();
        System.out.print("                      Enter date dd-mm-yyyy :");
        String journeyDate = scan.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            date = dateFormat.parse(journeyDate);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public boolean isAvailable() throws SQLException{

        TrainDAO traindao = new TrainDAO();
        BookingDAO bookingdao =new BookingDAO();
        int capacity = traindao.getCapacity(trainNo);
        int booked = bookingdao.getBookedCount(trainNo, date);
        int availableSeat = capacity - booked ;
        System.out.println("                      Available Seats :" + availableSeat);
        return capacity > booked;
    } 
    public int getPnrNo() throws SQLException{

        return trainNo;
        
    }

}
