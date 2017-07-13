import java.sql.ResultSet;

public class Book extends Conexion{
	protected int id;
	protected String name;
	protected String date;
	protected ResultSet resultSet;
	
	public Book(){
		/*this.name = name;
		this.date = date;*/
	}
	
	public void setBook(String name, String date){
		String sql = "INSERT INTO books(name, date)"
				+ "VALUES('" + name + "', '" + date + "')";
		try{
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(e);
			System.exit(0);
		}finally{
			System.out.println("ok");
		}
		
	}
	
	public void getAllBooks(){
        try{
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM books");
            if(resultSet.next() == true){
            	System.out.println(resultSet.getString("name"));
            }
         }catch(Exception e){
              e.printStackTrace();
         }
	}
	public void getIdBook(int id){
        try{
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM books WHERE id="+id);
            if(resultSet.next() == true){

            }
         }catch(Exception e){
              e.printStackTrace();
         }
	}
}
