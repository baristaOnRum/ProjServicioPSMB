import java.sql.*;

public class ConnectDB {
    public static void main(String args[]) throws SQLException{
        
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "xxx";
        
        try {
            Connection conexion = DriverManager.getConnection(url, user, pass);
        }
        catch(SQLException e) {
            return;
            }
        }
}
