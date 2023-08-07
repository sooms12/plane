package BankDAO;

import BankDTO.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankSQL {
    Connection con;

    PreparedStatement pstmt;

    ResultSet rs;

    public void connect() {
        con = DBC.DBConnect();
    }

    public void conClose() {
        try {
            con.close();
            System.out.println("DB 접속 종료!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void bJoin(BankDTO.Client client) {
        String sql = "INSERT INTO BANKMEMBER VALUES (?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.getmId());
            pstmt.setString(2, client.getmPassword());
            pstmt.setString(3, client.getmName());
            pstmt.setString(4, client.getmPhone());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("입력 성공!");
            } else {
                System.out.println("입력 실패!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean bIdCheck(Client client) {
        String sql = "SELECT * FROM BANKMEMBER WHERE MID=?";
        boolean check = false;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.getmId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;
    }

    public boolean bPwCheck(Client client) {
        String sql = "SELECT * FROM BANKMEMBER WHERE MID=? AND MPW=?";
        boolean check = false;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.getmId());
            pstmt.setString(2, client.getmPassword());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;
    }

    public String btAccount() {
        String cAccount = null;
        // 계좌번호 110-xxx-xxxxxx
        cAccount = "110-";
        for (int i = 0; i < 3; i++) {
            cAccount += (int) (Math.random() * 9);
        }
        cAccount += "-";
        for (int i = 0; i < 6; i++) {
            cAccount += (int) (Math.random() * 9);
        }

        return cAccount;
    }

    public void bCreate(Client client) {
        String sql = "INSERT INTO BANKTEAM VALUES (?,?,?,?,null)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.getmId());
            pstmt.setString(2, client.gettAccount());
            pstmt.setInt(3, client.gettBalance());
            pstmt.setInt(4, client.getcCode());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("계좌가 생성되었습니다!");
            } else {
                System.out.println("생성 실패");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void bPCreate(Client client) {
        String sql = "INSERT INTO BANKTEAM VALUES ('admin',?,0,1,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.gettAccount());
            pstmt.setString(2, client.gettName());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("계좌가 생성되었습니다!");
            } else {
                System.out.println("생성 실패");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String bPAccount() {
        String cAccount = null;
        // 계좌번호 222-xxx-xxxxxx
        cAccount = "222-";
        for (int i = 0; i < 3; i++) {
            cAccount += (int) (Math.random() * 9);
        }
        cAccount += "-";
        for (int i = 0; i < 6; i++) {
            cAccount += (int) (Math.random() * 9);
        }

        return cAccount;
    }

    public void select(Client client) {
        String sql = "SELECT b.CNAME, a.MID, a.TACCOUNT, a.TBALANCE " +
                "FROM BANKTEAM a, BANKCODE b,BANKMEMBER c " +
                "WHERE a.MID=? AND b.ccode=a.ccode and c.mid=a.mid";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.getmId());
//            pstmt.executeUpdate();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print("[" + rs.getString(1) + "통장] ");
                System.out.print(rs.getString(2) + " : ");
                System.out.print(rs.getString(3) + "  잔액 : ");
                System.out.println(rs.getInt(4) + "원");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pSelect(Client client) {
        String sql = "SELECT a.TNAME,a.TACCOUNT,b.acount,b.atotal FROM BANKTEAM a, PACCOUNT b WHERE a.TACCOUNT=b.TACCOUNT AND TNAME=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.gettName());
//            pstmt.executeUpdate();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print("통장명 : "+ rs.getString(1) + ", 계좌번호 : "
                        +rs.getString(2)+", 예약내역"+rs.getInt(3)+"건에 대한 "
                        +rs.getInt(4)+"원을 입금해 주십시오"
                );

            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean bAccountCheck(Client client) {
        String sql = "SELECT * FROM BANKTEAM WHERE tAccount=?";
        boolean check = false;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.gettAccount());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public boolean bwAccountCheck(Client client) {
        String sql = "SELECT * FROM BANKTEAM WHERE tAccount=? AND mId=?";
        boolean check = false;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.gettAccount());
            pstmt.setString(2, client.getmId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void bDeposit(Client client) {
        String sql = "UPDATE BANKTEAM SET tbalance=tbalance+? WHERE tAccount=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(2, client.gettAccount());
            pstmt.setInt(1, client.gettBalance());
            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void bWithdrawal(Client client) {
        String sql = "UPDATE BANKTEAM SET tbalance=tbalance-? WHERE tAccount=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(2, client.gettAccount());
            pstmt.setInt(1, client.gettBalance());
            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int balance(Client client) {
        String sql = "SELECT tbalance FROM BANKTEAM WHERE tAccount=?";
        int bal = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.gettAccount());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bal = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bal;
    }

    public boolean bCheck(Client client) {
        boolean check = false;
        String sql = "SELECT * FROM BANKTEAM WHERE MID=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.getmId());
            int result = pstmt.executeUpdate();
            if (result < 3) {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void bRecord(Client client, String io) {
        String sql = "INSERT INTO BANKRECORD VALUES (?,SYSDATE,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, client.getrNum());
            pstmt.setString(2, client.gettAccount());
            pstmt.setInt(3, client.gettBalance());
            pstmt.setString(4, io);
            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void bRecordCheck(Client client) {
        String sql = "SELECT * FROM BANKRECORD WHERE tAccount=? ORDER BY rNum";
        try {
            int count = 0;
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.gettAccount());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getString(2) + " >> ");
                System.out.print(rs.getInt(4) + " 원 ");
                System.out.println(rs.getString(5) + "하였습니다");
                count++;
            }
            System.out.print(count + "번의 거래 내역이 있습니다 ");
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int rNum() {
        int rNum = 0;
        String sql = "SELECT Max(rNum) FROM BANKRECORD";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rNum = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rNum;
    }
}
