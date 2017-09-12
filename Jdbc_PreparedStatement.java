import java.sql.*;
class Jdbc_PreparedStatement
{
    public static void main(String args[])
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","amit","amit");
            // Statement st=con.createStatement();
            PreparedStatement ps1=con.prepareStatement("INSERT INTO user_info VALUES(?,?)");
            PreparedStatement ps2=con.prepareStatement("SELECT * FROM user_info WHERE userid=?");
            ps1.setString(2,"Ramu");
            ps1.setInt(1,5);
            ps1.executeUpdate();
            ps1.close();
            ps2.setInt(1,343435);
            ResultSet rs=ps2.executeQuery();
            while(rs.next())
            {
                String s=rs.getString(2);
                int n=rs.getInt(1);
                System.out.println("Name"+s+"\n"+"salary : "+n);
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}