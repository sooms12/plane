package PLANE;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class airSQL {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public void connect() {
        con = DBC.DBConnect();
    }

    public void aReg(Airline air) {
        String sql = "INSERT INTO AIRLINE VALUES (?,?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getaName());
            pstmt.setString(2, air.getaType());
            pstmt.setString(3, air.getaCompany());
            pstmt.setDouble(4, air.getaTime());
            pstmt.setString(5, air.getdCode());
            pstmt.setString(6, air.getaCode());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("입력 성공!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void aSelect() {
        String sql = "SELECT * FROM AIRLINE ORDER BY ANAME";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("항공편 : " + rs.getString(1) + " | "
                        + "비행기종 : " + rs.getString(2) + " | "
                        + "항공사 : " + rs.getString(3) + " | "
                        + "출발공항 코드 : " + rs.getString(5) + " | "
                        + "도착공항 코드 : " + rs.getString(6)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cReg(Airline air) {
        String sql = "INSERT INTO CODE VALUES (?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getCode());
            pstmt.setString(2, air.getNation());
            pstmt.setString(3, air.getCity());
            pstmt.setInt(4, air.gettZone());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("입력 성공!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cSelect() {
        String sql = "SELECT * FROM CODE ORDER BY CODE";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("공항 코드 : " + rs.getString(1) + " | "
                        + "국가 : " + rs.getString(2) + " | "
                        + "도시 : " + rs.getString(3) + " | "
                        + "타임존 : " + rs.getInt(4)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int genrNum() {
        int rNum = 0;
        String sql = "SELECT MAX(RNUM) FROM ARESERVATION";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                rNum = rs.getInt(1);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rNum;
    }

    public void sReg(Airline air) {
        String sql = "INSERT INTO ASCHEDULE VALUES (?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getsNum());
            pstmt.setString(2, air.getsStart());
            pstmt.setInt(3, air.getsPrice());
            pstmt.setString(4, air.getaName());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("입력 성공!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sSelect() {
        String sql = "SELECT b.SNUM,a.ANAME,a.ACOMPANY,a.DCODE,a.ACODE,b.SSTART,a.ATIME,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b WHERE a.ANAME=b.ANAME ORDER BY b.SNUM";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("일정번호 : " + rs.getString(1) + " | "
                        + "항공편 : " + rs.getString(2) + " | "
                        + "항공사 : " + rs.getString(3) + " | "
                        + "출발지 : " + rs.getString(4) + " | "
                        + "도착지 : " + rs.getString(5) + " | "
                        + "출발시간 : " + rs.getString(6).substring(0, 5) + " " + rs.getString(6).substring(6, 11).replace('/', ':') + " | "
                        + "가격 : " + rs.getInt(8)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sSelect(Airline air) {
        boolean check = false;
        String sql = "SELECT b.SNUM,a.ANAME,a.ACOMPANY,a.DCODE,a.ACODE,b.SSTART,a.ATIME,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b, CODE c " +
                "WHERE a.ANAME=b.ANAME AND c.CODE=a.ACODE AND b.SNUM=? ORDER BY b.SNUM";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getsNum());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    System.out.println("일정번호 : " + rs.getString(1) + " | "
                            + "항공편 : " + rs.getString(2) + " | "
                            + "항공사 : " + rs.getString(3) + " | "
                            + "출발지 : " + rs.getString(4) + " | "
                            + "도착지 : " + rs.getString(5) + " | "
                            + "출발시간 : " + rs.getString(6).substring(0, 5) + " " + rs.getString(6).substring(6, 11).replace('/', ':') + " | "
                            + "가격 : " + rs.getInt(8)
                    );
                }
            } else {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void sSelect(String region) {
        String sql = "SELECT DISTINCT c.NATION, c.CITY FROM AIRLINE a, ASCHEDULE b, CODE c "
                + "WHERE a.ANAME=b.ANAME AND c.CODE=a.ACODE AND SUBSTR(b.SNUM,6,2)=? AND a.DCODE='ICN'";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("국가 : " + rs.getString(1) + " | "
                        + "도시 : " + rs.getString(2)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sSelect(Airline air, String region) {
        boolean check = false;
        String sql = "SELECT b.SNUM,a.ANAME,a.ACOMPANY,a.DCODE,a.ACODE,b.SSTART,a.ATIME,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b, CODE c " +
                "WHERE a.ANAME=b.ANAME AND c.CODE=a.ACODE AND c.CITY=? AND SUBSTR(b.SSTART,1,5)=? ORDER BY b.SNUM";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            pstmt.setString(2, air.getsStart());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    System.out.println("일정번호 : " + rs.getString(1) + " | "
                            + "항공편 : " + rs.getString(2) + " | "
                            + "출발지 : " + rs.getString(4) + " | "
                            + "도착지 : " + rs.getString(5) + " | "
                            + "출발시간 : " + rs.getString(6).substring(0, 5) + " " + rs.getString(6).substring(6, 11).replace('/', ':') + " | "
                            + "가격 : " + rs.getInt(8) + " | "
                            + "항공사 : " + rs.getString(3)
                    );
                }
            } else {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public boolean sBSelect(Airline air, String region) {
        boolean check = false;
        String sql = "SELECT b.SNUM,a.ANAME,a.ACOMPANY,a.DCODE,a.ACODE,b.SSTART,a.ATIME,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b, CODE c " +
                "WHERE a.ANAME=b.ANAME AND c.CODE=a.DCODE AND c.CITY=? AND SUBSTR(b.SSTART,1,5)=? ORDER BY b.SNUM";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            pstmt.setString(2, air.getsStart());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    System.out.println("일정번호 : " + rs.getString(1) + " | "
                            + "항공편 : " + rs.getString(2) + " | "
                            + "출발지 : " + rs.getString(4) + " | "
                            + "도착지 : " + rs.getString(5) + " | "
                            + "출발시간 : " + rs.getString(6).substring(0, 5) + " " + rs.getString(6).substring(6, 11).replace('/', ':') + " | "
                            + "가격 : " + rs.getInt(8) + " | "
                            + "항공사 : " + rs.getString(3)
                    );
                }
            } else {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void rReg(Airline air) {
        String sql = "INSERT INTO ARESERVATION VALUES (?,SYSDATE,?,?,0)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getmId());
            pstmt.setString(2, air.getsNum());
            pstmt.setInt(3, air.getrNum());
            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rSelect() {
        String sql = "SELECT c.MID,c.RTIME,b.ANAME,b.SSTART,a.DCODE,a.ACODE,b.SPRICE,d.PNAME" +
                " FROM AIRLINE a, ASCHEDULE b, ARESERVATION c, PAY d" +
                " WHERE a.ANAME=b.ANAME AND B.SNUM=C.SNUM AND c.RCHECK=d.RCHECK ORDER BY c.RTIME";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("아이디 : " + rs.getString(1) + " | "
                        + "예약시간 : " + rs.getString(2).substring(5, 16) + " | "
                        + "항공편 : " + rs.getString(3) + " | "
                        + "출발시간 : " + rs.getString(4).substring(0, 5) + " " + rs.getString(4).substring(6, 11).replace('/', ':') + " | "
                        + "출발지 : " + rs.getString(5) + " | "
                        + "목적지 : " + rs.getString(6) + " | "
                        + "가격 : " + rs.getInt(7) + " | "
                        + "결제여부 : " + rs.getString(8)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean rSelect(Airline air, int pay) {
        boolean check = false;
        String sql = "SELECT c.RNUM, c.RTIME,b.ANAME,b.SSTART,a.DCODE,a.ACODE,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b, ARESERVATION c " +
                "WHERE a.ANAME=b.ANAME AND B.SNUM=C.SNUM AND c.RCHECK=? AND c.MID=? ORDER BY c.RNUM";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pay);
            pstmt.setString(2, air.getmId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("예약번호 : " + rs.getString(1) + " | "
                        + "예약시간 : " + rs.getString(2).substring(5, 16) + " | "
                        + "항공편 : " + rs.getString(3) + " | "
                        + "출발시간 : " + rs.getString(4).substring(0, 5) + " " + rs.getString(4).substring(6, 11).replace('/', ':') + " | "
                        + "출발지 : " + rs.getString(5) + " | "
                        + "목적지 : " + rs.getString(6) + " | "
                        + "가격 : " + rs.getInt(7)
                );
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void rSelect(Airline air) {
        String sql = "SELECT a.ACOMPANY, d.MNAME, c.SNUM,b.ANAME,b.SSTART,a.DCODE,a.ACODE" +
                " FROM AIRLINE a, ASCHEDULE b, ARESERVATION c, AMEMBER d " +
                "WHERE a.ANAME=b.ANAME AND b.SNUM=c.SNUM AND c.MID=d.MID AND c.RNUM=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, air.getrNum());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println();
                System.out.println("  " + rs.getString(1) + "     ECONOMY      탑승권      BOARDING PASS");
                System.out.println("──────────────────────────────────────────────────────");
                System.out.println("   이   름    :  " + rs.getString(2));
                System.out.println("   항 공 편    :  " + rs.getString(4) + "               도착지 : " + rs.getString(7));
                System.out.println("   출발시간    :  " + rs.getString(5).substring(0, 5) + " " + rs.getString(5).substring(6, 11).replace('/', ':') + "         출발지 : " + rs.getString(6));
                System.out.println("   탑 승 구    :  --                                    ");
                System.out.println("                                                       ");
                System.out.println("   " + rs.getString(3));
                System.out.print("───────────────────────────────────────────────────────");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rDelete(Airline air) {
        String sql = "DELETE FROM ARESERVATION WHERE RNUM=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, air.getrNum());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int rTotal(Airline air) {
        String sql = "SELECT SUM(SPRICE) FROM ASCHEDULE a, ARESERVATION b" +
                " WHERE a.SNUM=b.SNUM AND b.RCHECK=0 AND b.MID=?";
        int total = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getmId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }

    public int rCount(Airline air) {
        String sql = "SELECT COUNT(*),SUM(SPRICE) FROM ASCHEDULE a, ARESERVATION b" +
                " WHERE a.SNUM=b.SNUM AND b.RCHECK=0 AND b.MID=?";
        int count = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getmId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public void rUpdate(Airline air, int i) {
        String sql = "UPDATE ARESERVATION SET RCHECK=? WHERE MID=? AND RCHECK=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, i + 1);
            pstmt.setString(2, air.getmId());
            pstmt.setInt(3, i);
            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean rCheck(Airline air, int i) {
        boolean check = false;
        String sql = "SELECT * FROM ARESERVATION WHERE RCHECK=? AND MID=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, i);
            pstmt.setString(2, air.getmId());
            rs = pstmt.executeQuery();
            check = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public boolean rCheck(int i) {
        boolean check = false;
        String sql = "SELECT * FROM ARESERVATION WHERE RCHECK=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, i);
            rs = pstmt.executeQuery();
            check = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void pSelect(Airline air) {
        String sql = "SELECT a.TNAME,a.TACCOUNT,b.ACOUNT,b.atotal FROM BANKTEAM a, PACCOUNT b WHERE a.TACCOUNT=b.TACCOUNT AND a.TNAME=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getmId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print("통장명 : " + rs.getString(1) + ", 계좌번호 : "
                        + rs.getString(2) + ", 예약내역 " + rs.getInt(3) + "건에 대한 "
                        + rs.getInt(4) + "원을 입금해 주십시오"
                );

            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkBalance(Airline air) {
        boolean check = false;
        String sql = "SELECT * FROM PACCOUNT a, BANKTEAM b WHERE a.ATOTAL=b.TBALANCE AND a.TACCOUNT=b.TACCOUNT AND b.TNAME=?";
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, air.getmId());
            rs = pstmt.executeQuery();
            check = rs.next();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public boolean checkBalance() {
        boolean check = false;
        String sql = "SELECT * FROM PACCOUNT a, BANKTEAM b WHERE a.ATOTAL=b.TBALANCE AND a.TACCOUNT=b.TACCOUNT";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            check = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public String pId() {
        String id = null;
        String sql = "SELECT b.TNAME FROM PACCOUNT a, BANKTEAM b WHERE a.ATOTAL=b.TBALANCE AND a.TACCOUNT=b.TACCOUNT";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getString(1);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void pCreate(Airline air) {
        String sql = "INSERT INTO PACCOUNT VALUES (?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.gettAccount());
            pstmt.setInt(2, air.getaTotal());
            pstmt.setInt(3, air.getaCount());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void bPCreate(Airline air) {
        String sql = "INSERT INTO BANKTEAM VALUES ('admin',?,0,1,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.gettAccount());
            pstmt.setString(2, air.getmId());
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
        // 계좌번호 333-xxx-xxxxxx
        cAccount = "333-";
        for (int i = 0; i < 3; i++) {
            cAccount += (int) (Math.random() * 9);
        }
        cAccount += "-";
        for (int i = 0; i < 6; i++) {
            cAccount += (int) (Math.random() * 9);
        }
        return cAccount;
    }

    public void bPDelete(Airline air) {
        String sql = "DELETE (SELECT * FROM BANKTEAM a, PACCOUNT b WHERE a.TACCOUNT=b.TACCOUNT AND TNAME=?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getmId());
            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void recommend(String value) {
        String sql = "SELECT c.CITY, COUNT(c.CITY)  FROM ASCHEDULE a, AIRLINE b, CODE c " +
                "WHERE substr(a.snum,6,4) LIKE ? AND a.ANAME=b.ANAME AND b.ACODE=c.CODE " +
                "GROUP BY c.CITY ORDER BY COUNT(c.CITY) desc";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, value);
            rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                if (count < 2) {
                    System.out.print(rs.getString(1) + ", ");
                    count++;
                } else if (count == 2) {
                    System.out.print(rs.getString(1));
                    count++;
                }
            }
            pstmt.close();
            rs.close();
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void nInfo(Airline air) {
        String sql = "SELECT * FROM NATIONINFO WHERE NATION=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getNation());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("국 가 명 : " + rs.getString(1));
                System.out.println("주요도시 : " + rs.getString(2));
                System.out.println("화   폐 : " + rs.getString(3));
                System.out.println("기   후 : " + rs.getString(4));
                System.out.println("언   어 : " + rs.getString(5));
                System.out.println("GDP($) : " + rs.getString(6));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cInfo(String value) {
        String sql = "SELECT * FROM COMPANYINFO WHERE ACOMPANY=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, value);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("항공사명 : " + rs.getString(1));
                System.out.println("항공동맹 : " + rs.getString(2));
                System.out.println("마일리지 : " + rs.getString(3));
                System.out.println("운항노선 : " + rs.getString(4));
                System.out.println("보유기종 : " + rs.getString(5));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
