import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        DbConnection dbConn = new DbConnection();
        try {
            dbConn.getConnection();
            Scanner scan = new Scanner(System.in);
            boolean loginResult = false;
            int userOpt = 0;
            System.out.println("===================================== Online Reservation System ======================================");
            System.out.println("--------------------------------------   Enter your option    ----------------------------------------");
            System.out.println("                                         1.Create account");
            System.out.println("                                         2.Login");
            System.out.println("======================================================================================================");
            userOpt = scan.nextInt();
            while (!loginResult) {

                Login login = new Login(userOpt);
                LoginDAO logindao = new LoginDAO();
                loginResult = (userOpt == 1) ? logindao.signUpCredentials(login) : logindao.loginCredentials(login);

                if (!loginResult) {
                    System.out.println("                                Invalid credentials. Please try again.");
                }
            }

            if (loginResult) {
                boolean exitProgram = false;
                BookingDAO bookingdao = new BookingDAO();
                
                while (!exitProgram) {
                    TrainDAO traindao = new TrainDAO();
                    traindao.displayTrainInfo();
                    System.out.println("-----------------------------------------  Enter your option  ----------------------------------------");
                    System.out.println("                                        1. To Book your train.");
                    System.out.println("                                        2. To Cancel booking.");
                    System.out.println("                                        3. Check your Ticket status.");
                    System.out.println("                                        4. To exit");
                    System.out.println("------------------------------------------------------------------------------------------------------");
                    userOpt = scan.nextInt();

                    switch (userOpt) {
                        case 1:
                            Booking booking = new Booking();
                            if (booking.isAvailable()) {
                                bookingdao.addBooking(booking);
                            } else {
                                System.out.println("                            Sorry, Train is Full");
                            }
                            break;
                        case 2:
                            System.out.println("------------------------------------- Ticket cancellation ------------------------------------------");
                            System.out.print("\n                              Enter your PNR number to cancel: ");
                            int bookedPnrNo = scan.nextInt();
                            bookingdao.cancelBooking(bookedPnrNo);
                            System.out.println("                              Your Ticket is cancelled successfully.");
                            System.out.println("----------------------------------------------------------------------------------------------------");
                            break;
                        case 3:
                            System.out.println("----------------------------------------- Ticket Status ---------------------------------------------");
                            System.out.print("\n                        Enter your PNR number to check your status : ");
                            bookedPnrNo = scan.nextInt();
                            boolean ticketStatus = bookingdao.checkTicketStatus(bookedPnrNo);
                            if (ticketStatus) {
                                System.out.println("                             Your ticket is confirmed.");
                            } else {
                                System.out.println("                    No booking found with the provided PNR number.");
                            }
                            break;
                        case 4:
                            System.out.println("Thank you for visiting....");
                            exitProgram = true; // Set exit flag to true to exit the while loop
                            break;
                        default:
                            System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
                            break;
                    }
                }
            } else {
                System.out.println("Please provide valid information.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
