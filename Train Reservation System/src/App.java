import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        DbConnection dbConn = new DbConnection();
        try {
            dbConn.getConnection();
            Scanner scan = new Scanner(System.in);
            System.out.println("===================================== Online Reservation System ======================================");
            System.out.println("--------------------------------------   Enter your option    ----------------------------------------");
            System.out.println("                                         1.Create account");
            System.out.println("                                         2.Login");
            System.out.println("======================================================================================================");
            int userOpt = scan.nextInt();

            Login login = new Login(userOpt);
            LoginDAO logindao = new LoginDAO();
            BookingDAO bookingdao = new BookingDAO();

            boolean loginResult;
            if (userOpt == 1) {
                loginResult = logindao.signUpCredentials(login);
                if (loginResult) {
                    System.out.println("                            Account created successfully! Welcome, " + login.userName + "! ");
                    System.out.println("------------------------------------------------------------------------------------------------------");
                } else {
                    System.out.println("                                    Failed to create account.");
                }
            } else if (userOpt == 2) {
                loginResult = logindao.loginCredentials(login);
                if (loginResult) {
                    System.out.println("                       Login successful! Welcome, " + login.userName + "!");
                    System.out.println("--------------------------------------------------------------------------------------------");
                } else {
                    System.out.println("                                              Failed to login.");
                }
            } else {
                System.out.println("                            Invalid option. Please enter 1 or 2.");
                return;
            }


            if (loginResult) {
                boolean exitProgram = false;
                while (!exitProgram) {
                    TrainDAO traindao = new TrainDAO();
                    traindao.displayTrainInfo();
                    System.out.println("------------------------------------  Enter your option  --------------------------------------");
                    System.out.println("                                    1. To Book your train.");
                    System.out.println("                                    2. To Cancel booking.");
                    System.out.println("                                    3. Check your Ticket status.");
                    System.out.println("                                    4. To exit");
                    System.out.println("-----------------------------------------------------------------------------------------------");
                    userOpt = scan.nextInt();

                    switch (userOpt) {
                        case 1:
                            Booking booking = new Booking();
                            if (booking.isAvailable()) {
                                //BookingDAO bookingdao = new BookingDAO();
                                bookingdao.addBooking(booking);

                            } else {
                                System.out.println("                Sorry, Train is Full");
                            }
                            break;
                        case 2:
                            System.out.println("------------------------------------- Ticket cancellation ------------------------------------------");
                            System.out.print("\n                              Enter your PNR number to cancel: ");
                            int bookedPnrNo = scan.nextInt();
                            //BookingDAO bookingdao = new BookingDAO();
                            bookingdao.cancelBooking(bookedPnrNo);
                            System.out.println("                              Your Ticket is cancelled successfully.");
                            System.out.println("----------------------------------------------------------------------------------------------------");
                            break;
                        case 3:
                            System.out.println("------------------------------------- Ticket Status ------------------------------------------");
                            System.out.print("\n                    Enter your PNR number to check your status : ");
                            bookedPnrNo = scan.nextInt();
                            //BookingDAO bookingdao = new BookingDAO();
                            boolean ticketStatus = bookingdao.checkTicketStatus(bookedPnrNo);
                            if (ticketStatus) {
                                System.out.println("                            Your ticket is confirmed.");
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
                System.out.println("                                    Please provide valid information.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
