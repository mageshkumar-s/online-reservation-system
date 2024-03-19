import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainDAO {
    public void displayTrainInfo() throws SQLException{
        String query = "Select * from train_details";
        Connection conn = DbConnection.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while(rs.next()){
            System.out.println("train name is "+rs.getString(2) + " Starting from "+ rs.getString(3)+" Date of journey "+rs.getString(4)+"\n");
        }
    }

    public  int getCapacity(int s_no) throws SQLException{
        String query = "select capacity from train_details where s_no = "+s_no;
        Connection conn =DbConnection.getConnection();
        Statement statement =  conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }
}
