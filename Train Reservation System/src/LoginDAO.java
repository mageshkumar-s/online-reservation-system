import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    
    public boolean signUpCredentials(Login login) {
        String query = "INSERT INTO auth_details (username, password, mobile_no) VALUES (?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, login.userName);
            preparedStatement.setString(2, login.passWord);
            preparedStatement.setString(3, login.mobileNo);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was affected
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurs
        }
    }

    public boolean loginCredentials(Login login) {
    String query = "SELECT COUNT(*) FROM auth_details WHERE username = ? AND password = ?";
    try (Connection conn = DbConnection.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
        preparedStatement.setString(1, login.userName);
        preparedStatement.setString(2, login.passWord);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0; // Return true if there is at least one matching username and password
        }
        return false; // Return false if no matching username and password found
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false if an exception occurs
    }
}

    
}
