
import java.sql.*;

public class Conexion {
	Connection con;
	Statement stmt;
	
	public Conexion(){
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:db/data.db");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
