import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

public class BookingDAO {

    public int getBookedCount(int trainNo,Date date ) throws SQLException{
        String query = "select count(pnr_no) from booked_details where train_no = ? and date = ?";
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
        int pnr_no = random.nextInt(9000)+1000;

        String query = "insert into booked_details (pnr_no, passenger_name, date, train_no) values (?, ?, ?, ?)";
        Connection conn = DbConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        java.sql.Date sqlDate = new java.sql.Date(booking.date.getTime());
        preparedStatement.setInt(1, pnr_no);
        preparedStatement.setString(2, booking.passengerName);
        preparedStatement.setDate(3, sqlDate);
        preparedStatement.setInt(4, booking.trainNo);
        preparedStatement.executeUpdate();
    }

    public void cancelBooking(int pnr_no) throws SQLException{
        String query = "delete from booked_details where pnr_no = " + pnr_no;
        Connection conn = DbConnection.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }
}
