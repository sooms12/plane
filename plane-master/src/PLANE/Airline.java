package PLANE;

import java.sql.SQLException;

public class Airline extends Member{

    // AIRLINE 테이블 변수
    String aName, aType, aCompany;
    double aTime;
    String dCode, aCode;

    // CODE 테이블 변수
    String code, nation, city;
    int tZone;

    public String getsNum() {
        return sNum;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
    }

    public String getsStart() {
        return sStart;
    }

    public void setsStart(String sStart) {
        this.sStart = sStart;
    }

    public int getsPrice() {
        return sPrice;
    }

    public void setsPrice(int sPrice) {
        this.sPrice = sPrice;
    }

    // ASCHEDULE 테이블 변수
    String  sNum;
    String sStart;
    int sPrice;

    // ARESERVATION 테이블 변수
    String rTime;
    int rNum;
    int rCheck;
    String tAccount;
    int aTotal;
    int aCount;

    public int getaTotal() {
        return aTotal;
    }

    public void setaTotal(int aTotal) {
        this.aTotal = aTotal;
    }

    public int getaCount() {
        return aCount;
    }

    public void setaCount(int aCount) {
        this.aCount = aCount;
    }

    public String gettAccount() {
        return tAccount;
    }

    public void settAccount(String tAccount) {
        this.tAccount = tAccount;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public int getrNum() {
        return rNum;
    }

    public void setrNum(int rNum) {
        this.rNum = rNum;
    }

    public int getrCheck() {
        return rCheck;
    }

    public void setrCheck(int rCheck) {
        this.rCheck = rCheck;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int gettZone() {
        return tZone;
    }

    public void settZone(int tZone) {
        this.tZone = tZone;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getaCompany() {
        return aCompany;
    }

    public void setaCompany(String aCompany) {
        this.aCompany = aCompany;
    }

    public double getaTime() {
        return aTime;
    }

    public void setaTime(double aTime) {
        this.aTime = aTime;
    }

    public String getdCode() {
        return dCode;
    }

    public void setdCode(String dCode) {
        this.dCode = dCode;
    }

    public String getaCode() {
        return aCode;
    }

    public void setaCode(String aCode) {
        this.aCode = aCode;
    }


}
