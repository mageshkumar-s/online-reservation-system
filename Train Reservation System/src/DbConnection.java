import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String Url = "jdbc:postgresql://localhost:5432/app";
    private static final String userName = "postgres";
    private static final String passWord = "magesh";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection( Url, userName, passWord);
    }
}
