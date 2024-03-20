import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        DbConnection dbConn = new DbConnection();
        try {
            dbConn.getConnection();
            Scanner scan = new Scanner(System.in);
            System.out.println("----------Enter your option.----------\n 1. Create account\n 2. Login\n");
            int userOpt = scan.nextInt();

            Login login = new Login(userOpt);
            LoginDAO logindao = new LoginDAO();
            
            boolean loginResult;
            if (userOpt == 1) {
                loginResult = logindao.signUpCredentials(login);
                if (loginResult) {
                    System.out.println("Account created successfully! \n"+login.userName+"! ");
                } else {
                    System.out.println("Failed to create account.");
                }
            } else if (userOpt == 2) {
                loginResult = logindao.loginCredentials(login);
                if (loginResult) {
                    System.out.println("Login successful! Welcome, " + login.userName + "!");
                } else {
                    System.out.println("Failed to login.");
                }
            } else {
                System.out.println("Invalid option. Please enter 1 or 2.");
                return;
            }
            
            
            if(loginResult){
                
                TrainDAO traindao = new TrainDAO();
                traindao.displayTrainInfo();
                System.out.println("---------Enter your option------------");
                System.out.println("1. To Book your train.");
                System.out.println("2. To Cancel booking.");
                System.out.println("3. To exit");
                System.out.println("--------------------------------------");
                userOpt = scan.nextInt();

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
                System.out.println("Please provide valid information");
            }
                    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
