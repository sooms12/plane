package PLANE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    public void connect(){
        con=DBC.DBConnect();
    }
    public void conClose(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean idCheck(Member mem) {
        boolean check=false;
        return check;

    }

    public boolean idPwCheck(Member mem) {
        boolean check=false;
        return check;
    }

    public void join(Member mem) {
    }

    public boolean adminCheck(Member mem) {
        boolean check=false;
        return check;
    }
}
