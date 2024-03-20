import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

public class BookingDAO {

    public int getBookedCount(int trainNo, Date date) throws SQLException {
        String query = "SELECT COUNT(pnr_no) FROM booked_details WHERE train_no = ? AND date = ?";
        Connection conn = DbConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        preparedStatement.setInt(1, trainNo);
        preparedStatement.setDate(2, sqlDate);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        return rs.getInt(1);
    
    }
    

    public void addBooking(Booking booking) throws SQLException{

        Random random = new Random();
        int pnr_no = random.nextInt(90000)+1000;

        String query = "insert into booked_details (pnr_no, passenger_name, date, train_no) values (?, ?, ?, ?)";
        Connection conn = DbConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        java.sql.Date sqlDate = new java.sql.Date(booking.date.getTime());
        preparedStatement.setInt(1, pnr_no);
        preparedStatement.setString(2, booking.passengerName);
        preparedStatement.setDate(3, sqlDate);
        preparedStatement.setInt(4, booking.trainNo);
        preparedStatement.executeUpdate();
        printTicket(pnr_no);
    }

    public void printTicket(int pnrNo) {
        try {
            String query = "SELECT bd.pnr_no, bd.passenger_name, bd.date, bd.train_no, ts.train_name, ts.departure_station, ts.destination_station " +
                           "FROM booked_details bd " +
                           "JOIN train_schedule ts ON bd.train_no = ts.train_no " +
                           "WHERE bd.pnr_no = ?";
            Connection conn = DbConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, pnrNo);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                // Retrieve ticket details from the result set
                int pnr_no = resultSet.getInt("pnr_no");
                String passengerName = resultSet.getString("passenger_name");
                Date date = resultSet.getDate("date");
                int trainNo = resultSet.getInt("train_no");
                String trainName = resultSet.getString("train_name");
                String departureStation = resultSet.getString("departure_station");
                String destinationStation = resultSet.getString("destination_station");
    
                // Print ticket details
                System.out.println("                   Your Tickets are confirmed successfully!");
                System.out.println("------------------------:   Ticket Details  :-------------------------------");
                System.out.println("                        PNR Number           : " + pnr_no);
                System.out.println("                        Passenger Name       : " + passengerName);
                System.out.println("                        Date of journey      : " + date);
                System.out.println("                        Train Number         : " + trainNo);
                System.out.println("                        Train Name           : " + trainName);
                System.out.println("                        Departure Station    : " + departureStation);
                System.out.println("                        Destination Station  : " + destinationStation);
                System.out.println("--------------------------------------------------------------------------");
                // Add other ticket details as needed
            } else {
                System.out.println("Ticket details not found for PNR number: " + pnrNo);
            }
    
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkTicketStatus(int pnrNo) {
        String query = "SELECT EXISTS (SELECT 1 FROM booked_details WHERE pnr_no = ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, pnrNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(1); 
            }
            return false; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    

    public void cancelBooking(int pnr_no) throws SQLException{
        String query = "delete from booked_details where pnr_no = " + pnr_no;
        Connection conn = DbConnection.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }
}
