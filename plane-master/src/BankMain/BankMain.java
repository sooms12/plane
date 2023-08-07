package BankMain;

import BankDAO.BankSQL;
import BankDTO.Client;

import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Client client = new Client();
        Client client2 = new Client();
        BankSQL sql = new BankSQL();
        boolean run = true;
        sql.connect();
        while (run) {
            System.out.println("==========================");
            System.out.println("1. 회원가입 2. 로그인 3. 종료");
            System.out.println("==========================");
            System.out.print("메뉴 선택 : ");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    System.out.print("아이디 : ");
                    client.setmId(sc.next());
                    if (sql.bIdCheck(client)) {
                        System.out.println("이미 있는 아이디입니다.");
                    } else {
                        System.out.print("비밀번호 : ");
                        client.setmPassword(sc.next());
                        System.out.print("이름 : ");
                        client.setmName(sc.next());
                        System.out.print("연락처 : ");
                        client.setmPhone(sc.next());
                        sql.bJoin(client);
                    }
                    break;
                case 2:
                    System.out.print("아이디 : ");
                    client.setmId(sc.next());
                    if (sql.bIdCheck(client)) {
                        System.out.print("비밀번호 : ");
                        client.setmPassword(sc.next());
                        if (sql.bPwCheck(client)) {
                            boolean run2 = true;
                            while (run2) {
                                System.out.println("=====================================================");
                                System.out.println("1. 생성 2. 입금 3. 출금 4. 조회 5. 송금 6. 기록 7. 로그아웃");
                                System.out.println("=====================================================");
                                System.out.print("메뉴 선택 : ");
                                int menu2 = sc.nextInt();
                                switch (menu2) {
                                    case 1:
                                        if (sql.bCheck(client)) {
                                            System.out.println("1.일반 2.적금 3. 청약 4.주식 ");
                                            System.out.print("계좌 종류 선택 :");
                                            client.setcCode(sc.nextInt());
                                            client.settAccount(sql.btAccount());
                                            System.out.print("초기 입금액 입력 : ");
                                            client.settBalance(sc.nextInt());
                                            sql.bCreate(client);
                                            client.setrNum(sql.rNum()+1);
                                            sql.bRecord(client, "계좌 생성");
                                            System.out.println(client.gettAccount());
                                        } else {
                                            System.out.println("계좌가 이미 3개가 만들어져서 더 이상 생성이 불가능합니다");
                                        }
                                        break;
                                    case 2:
                                        System.out.print("계좌 정보 입력 : ");
                                        client.settAccount(sc.next());
                                        if (sql.bAccountCheck(client)) {
                                            System.out.print("입금 금액 입력 : ");
                                            client.settBalance(sc.nextInt());
                                            sql.bDeposit(client);
                                            client.setrNum(sql.rNum()+1);
                                            sql.bRecord(client, "입금");
                                        } else {
                                            System.out.println("계좌번호가 존재하지 않습니다");
                                        }
                                        break;
                                    case 3:
                                        System.out.print("계좌 정보 입력 : ");
                                        client.settAccount(sc.next());
                                        if (sql.bwAccountCheck(client)) {
                                            System.out.print("출금 금액 입력 : ");
                                            client.settBalance(sc.nextInt());
                                            if (sql.balance(client) >= client.gettBalance()) {
                                                sql.bWithdrawal(client);
                                                client.setrNum(sql.rNum()+1);
                                                sql.bRecord(client, "출금");
                                                System.out.println("출금 성공! 잔액은 " + sql.balance(client) + "원입니다");
                                            } else {
                                                System.out.println("잔액이 부족합니다");
                                            }
                                        } else {
                                            System.out.println("해당 계정에 일치하는 계좌번호가 존재하지 않습니다");
                                        }
                                        break;
                                    case 4:
                                        sql.select(client);
                                        break;
                                    case 5:
                                        System.out.print("출금 계좌 정보 입력 : ");
                                        client.settAccount(sc.next());
                                        if (sql.bwAccountCheck(client)) {
                                            System.out.print("출금 금액 입력 : ");
                                            int withdrawal = sc.nextInt();
                                            if (withdrawal <= sql.balance(client)) {
                                                System.out.print("입금 계좌 정보 입력 : ");
                                                client2.settAccount(sc.next());
                                                if (sql.bAccountCheck(client2)) {
                                                    client.settBalance(withdrawal);
                                                    sql.bWithdrawal(client);
                                                    client.setrNum(sql.rNum()+1);
                                                    sql.bRecord(client, "출금");
                                                    client2.settBalance(withdrawal);
                                                    sql.bDeposit(client2);
                                                    client2.setrNum(sql.rNum()+1);
                                                    sql.bRecord(client2, "입금");
                                                    System.out.println("송금 성공! 잔액은 " + sql.balance(client) + "원입니다");
                                                } else {
                                                    System.out.println("계좌번호가 존재하지 않습니다");
                                                }
                                            } else {
                                                System.out.println("잔액이 부족합니다");
                                            }
                                        } else {
                                            System.out.println("해당 계정에 일치하는 계좌번호가 존재하지 않습니다");
                                        }
                                        break;
                                    case 6:
                                        System.out.print("계좌 정보 입력 : ");
                                        client.settAccount(sc.next());
                                        if (sql.bwAccountCheck(client)) {
                                            sql.bRecordCheck(client);
                                            System.out.println("잔액은 " + sql.balance(client) + "원입니다");
                                        }
                                        else{
                                            System.out.println("해당 계정에 일치하는 계좌번호가 존재하지 않습니다");
                                        }
                                        break;
                                    case 7:
                                        System.out.println("로그아웃합니다");
                                        run2 = false;
                                        break;
                                    default:
                                        System.out.println("잘못 입력했습니다");
                                        break;
                                }

                            }
                        } else {
                            System.out.println("비밀번호가 일치하지 않습니다");
                        }
                    } else {
                        System.out.println("아이디가 존재하지 않습니다");
                    }
                    break;
                case 3:
                    System.out.println("종료합니다");
                    sql.conClose();
                    run = false;
                    break;
                default:
                    System.out.println("잘못 입력했습니다");
                    break;
            }
        }


    }
}
