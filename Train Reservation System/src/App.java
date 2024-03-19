import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        DbConnection dbConn = new DbConnection();
        try {
            dbConn.getConnection();
            Login login = new Login();
            LoginDAO logindao = new LoginDAO();
            
            boolean signUpResult = logindao.signUpCredentials(login);
            boolean loginResult = logindao.loginCredentials(login);
            if(signUpResult || loginResult){
                if(signUpResult && loginResult == false){
                    System.out.println("Account created successfully!");
                }else if(loginResult && signUpResult == false) {
                    System.out.println("Login successful! Welcome, " + login.userName + "!");
                }
                // Display train information after successful account creation
                TrainDAO traindao = new TrainDAO();
                traindao.displayTrainInfo();

                Scanner scan = new Scanner(System.in);
                System.out.println("---------Enter your option------------");
                System.out.println("1. To Book your train.");
                System.out.println("2. To Cancel booking.");
                System.out.println("3. To exit");
                System.out.println("--------------------------------------");
                int userOpt = scan.nextInt();

                switch (userOpt) {
                    case 1:
                        Booking booking = new Booking();
                        if (booking.isAvailable()) {
                            BookingDAO bookingdao = new BookingDAO();
                            bookingdao.addBooking(booking);
                            System.out.println("Your Tickets are confirmed successfully");
                        } else {
                            System.out.println("Sorry, Train is Full");
                        }
                        break;
                    case 2:
                        System.out.print("Enter your PNR number to cancel: ");
                        int bookedPnrNo = scan.nextInt();
                        BookingDAO bookingdao = new BookingDAO();
                        bookingdao.cancelBooking(bookedPnrNo);
                        System.out.println("Your Ticket is cancelled successfully");
                        break;
                    case 3:
                        System.out.println("Thank you for visiting....");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please enter 1, 2, or 3.");
                }
                
            }else{
                System.out.println("Failed to create or login account. Please provide valid information");
            }
                    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
