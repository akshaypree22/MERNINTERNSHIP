import java.sql.Connection;
public class DBConnection{
    Connection con = null;
    String url = "";
    String userName = "akshay";
    String passwordWord = "1234";
    if(con == null){ 
        class.forName( className:"com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection(url,username,password);
    }catch(Exception e){
        System.out.println("exception occured")
    }
}
