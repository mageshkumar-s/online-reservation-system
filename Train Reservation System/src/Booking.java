import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Booking {

    String passengerName;
    int trainNo;
    Date date;

    Booking(){
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("                      Enter name of Passenger : ");
            String input = scan.nextLine().trim();
            if (input.matches("^[a-zA-Z ]+$")) {
                passengerName = input;
                break;
            } else {
                System.out.println("            Invalid input! Please enter alphabetic characters only.");
            }
        }

        TrainDAO trainDao = new TrainDAO();

        while (true) {
            System.out.print("                      Enter Train No: ");
            int inputTrainNo = scan.nextInt();
            try {
                if (trainDao.isValidTrain(inputTrainNo)) {
                    trainNo = inputTrainNo;
                    break;
                } else {
                    System.out.println("            Invalid Train No! Please enter a valid train number.");
                }
            } catch (SQLException e) {
                System.out.println("            Error occurred while checking train number validity: " + e.getMessage());
            }
        }
        
        while (true) {
            System.out.print("                      Enter date [dd-mm-yyyy] : ");
            String journeyDate = scan.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            try {
                LocalDate parsedDate = LocalDate.parse(journeyDate, dateFormatter);
                LocalDate currentDate = LocalDate.now();
                if (parsedDate.isEqual(currentDate) || parsedDate.isAfter(currentDate)) {
                    date = java.sql.Date.valueOf(parsedDate);
                    break;
                } else {
                    System.out.println("            Invalid date! Please enter today's date or a future date.");
                }
            } catch (Exception e) {
                System.out.println("            Invalid date format! Please enter date in dd-mm-yyyy format.");
            }
        }
    }

    public boolean isAvailable() throws SQLException{

        TrainDAO traindao = new TrainDAO();
        BookingDAO bookingdao =new BookingDAO();
        int capacity = traindao.getCapacity(trainNo);
        int booked = bookingdao.getBookedCount(trainNo, date);
        int availableSeat = capacity - booked ;
        System.out.println("                      Available Seats           :" + availableSeat);
        return capacity > booked;
    } 
    

}
