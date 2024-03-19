import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        
        DbConnection dbConn = new DbConnection();
        try {
            dbConn.getConnection();
            TrainDAO traindao = new TrainDAO();
            traindao.displayTrainInfo();
            Scanner scan = new Scanner(System.in);
            System.out.println("---------Enter 1 to Book your train and 2 to exit-------");
            int userOpt = scan.nextInt();

            if(userOpt == 1){
                Booking booking = new Booking();
                if(booking.isAvailable()){
                    BookingDAO bookingdao = new BookingDAO();
                    bookingdao.addBooking(booking);
                }else{
                    System.out.println("Sorry, Train is Full");
                }
            }else{
                System.out.println("Thank you for visiting....");
            }
            scan.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        
    }
}
