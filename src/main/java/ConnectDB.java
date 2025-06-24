import java.sql.*;

public class ConnectDB {
    public static void main(String args[]) throws SQLException{

        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
        }
        catch(SQLException e) {
            return;
            }
        }
}
