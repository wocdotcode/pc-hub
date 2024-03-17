
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDao {
    
    
    protected synchronized Connection getConnection() throws SQLException{
        String url ="jdbc:mysql://localhost:3306/dbsystem";
        String user = "root";
        String password = "123";
        
        return DriverManager.getConnection(url, user,password);
    }
}
