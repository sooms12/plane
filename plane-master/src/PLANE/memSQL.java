package PLANE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class memSQL {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public void connect() {
        con = DBC.DBConnect();
    }

    public void conClose() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean idCheck(Member mem) {
        boolean check = false;
        String sql = "SELECT * FROM AMEMBER WHERE mId=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmId());
            rs = pstmt.executeQuery();
            check = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public boolean idPwCheck(Member mem) {
        boolean check = false;
        String sql = "SELECT * FROM AMEMBER WHERE mId=? AND mPw=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmId());
            pstmt.setString(2, mem.getmPw());
            rs = pstmt.executeQuery();
            check = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void join(Member mem) {
        String sql = "INSERT INTO AMEMBER VALUES (?,?,?,?,?,?,0)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmId());
            pstmt.setString(2, mem.getmPw());
            pstmt.setString(3, mem.getmName());
            pstmt.setString(4, mem.getmPhone());
            pstmt.setString(5, mem.getmPassport());
            pstmt.setString(6, mem.getmBirth());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("가입 성공!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean adminCheck(Member mem) {
        boolean check = false;
        String sql = "SELECT * FROM AMEMBER WHERE mId=? AND mAdmin=1";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmId());
            rs = pstmt.executeQuery();
            check = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void mSelect() {
        String sql = "SELECT * FROM AMEMBER";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("아이디 : " + rs.getString(1) + " | "
                        + "비밀번호 : " + rs.getString(2) + " | "
                        + "이름 : " + rs.getString(3) + " | "
                        + "연락처 : " + rs.getString(4) + " | "
                        + "여권번호 : " + rs.getString(5) + " | "
                        + "생년월일 : " + rs.getString(6).substring(0, 10) + " | "
                        + "관리자 : " + rs.getInt(7));

            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void mSelect(Member mem) {
        String sql = "SELECT * FROM AMEMBER WHERE mId=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,mem.getmId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("아이디 : " + rs.getString(1) + " | "
                        + "비밀번호 : " + rs.getString(2) + " | "
                        + "이름 : " + rs.getString(3) + " | "
                        + "연락처 : " + rs.getString(4) + " | "
                        + "여권번호 : " + rs.getString(5) + " | "
                        + "생년월일 : " + rs.getString(6).substring(0, 10));

            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mUpdate(Member mem, String type, String value) {
        String sql = "UPDATE AMEMBER SET " + type + "=? WHERE mId=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, value);
            pstmt.setString(2, mem.getmId());
            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mUpdate(Member mem, String type, int value) {
        String sql = "UPDATE AMEMBER SET " + type + "=? WHERE mId=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, value);
            pstmt.setString(2, mem.getmId());
            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void findId(Member mem) {
        String sql = "SELECT mId FROM AMEMBER WHERE mName=? AND mPhone=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmName());
            pstmt.setString(2, mem.getmPhone());
            rs = pstmt.executeQuery();
            if(rs.next()){
                System.out.println("찾으시는 아이디는 "+rs.getString(1)+"입니다");
            }else {
                System.out.println("일치하는 정보가 존재하지 않습니다");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void findPw(Member mem) {
        String sql = "SELECT mId FROM AMEMBER WHERE mName=? AND mPhone=? AND mPassport=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmName());
            pstmt.setString(2, mem.getmPhone());
            pstmt.setString(3, mem.getmPassport());
            rs = pstmt.executeQuery();
            if(rs.next()){
                System.out.println("찾으시는 패스워드는 "+rs.getString(1)+"입니다");
            }else {
                System.out.println("일치하는 정보가 존재하지 않습니다");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
