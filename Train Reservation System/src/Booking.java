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
        System.out.println("Enter name of Passenger : ");
        passengerName = scan.next();
        System.out.println("Enter Train No :");
        trainNo = scan.nextInt();
        System.out.println("Enter date dd-mm-yyyy ");
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
        //System.out.println("capacity is :" + capacity);
        int booked = bookingdao.getBookedCount(trainNo, date);
        System.out.println("capacity is :" + capacity + " booked seat count :" + booked);
        return capacity > booked;
    } 

}