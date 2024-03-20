import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainDAO {

    public boolean isValidTrain(int trainNo) throws SQLException {
        String query = "SELECT COUNT(*) FROM train_schedule WHERE train_no = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, trainNo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; 
                }
            }
        }
        return false; 
    }

    public void displayTrainInfo() throws SQLException{
        String query = "Select * from train_schedule";
        Connection conn = DbConnection.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        System.out.println("------------------------------------- Available Train List -------------------------------------------");
        int i =1;
        while(rs.next()){
            System.out.println(i +". Train No is "+rs.getString(2) + ". Starting from "+ rs.getString(4)+" to "+rs.getString(5)+". Date of journey "+rs.getString(6)+"\n");
            i++;
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }

    public  int getCapacity(int trainNo) throws SQLException{
        String query = "select capacity from train_schedule where train_no = "+trainNo;
        Connection conn = DbConnection.getConnection();
        Statement statement =  conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }
}
