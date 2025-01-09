import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/bank";
    private static final String username = "root";
    private static final String passwd = "%$#@!";

    public Connection getConnection(){
        if(con == null){
            try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, passwd);
            System.out.println("Connection successful");
            }catch(Exception e){
                System.out.println("Exception occured");
            }
        }
        return con;
    }   
    public static void main(String[] args){
        DBTest db = new DBTest();
        Connection con = db.getConnection();
    } 
}