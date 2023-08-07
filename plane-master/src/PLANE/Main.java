package PLANE;

import java.util.Scanner;

import static PLANE.util.calender;
import static PLANE.util.plane;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        memSQL sql = new memSQL();
        Member mem = new Member();
        airSQL aSql = new airSQL();
        Airline air = new Airline();
        boolean run = true;
        // DB 접속 메소드
        sql.connect();
        while (run) {
            plane();
            System.out.println("────────────────────────────────────────");
            System.out.println("1. 회원 가입 2. 로그인 3. ID/PW 찾기 4. 종료");
            System.out.println("────────────────────────────────────────");
            System.out.print("메뉴 선택 : ");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    System.out.print("아이디 입력 : ");
                    mem.setmId(sc.next());
                    // ID 확인 메소드
                    if (sql.idCheck(mem)) {
                        System.out.println("이미 존재하는 아이디입니다");
                    } else {
                        System.out.print("패스워드 입력 : ");
                        mem.setmPw(sc.next());
                        System.out.print("이름 입력 : ");
                        mem.setmName(sc.next());
                        System.out.print("전화번호 입력 : ");
                        mem.setmPhone(sc.next());
                        System.out.print("여권번호 입력 : ");
                        mem.setmPassport(sc.next());
                        System.out.print("생년월일 입력 : ");
                        mem.setmBirth(sc.next());
                        // 회원 가입 메소드
                        sql.join(mem);
                    }
                    break;
                case 2:
                    System.out.print("아이디 입력 : ");
                    mem.setmId(sc.next());
                    // ID 확인 메소드
                    if (sql.idCheck(mem)) {
                        System.out.print("패스워드 입력 : ");
                        mem.setmPw(sc.next());
                        // DB 접속 메소드, sql class를 둘로 분리해서 분리된 sql문 각각 접속 필요
                        aSql.connect();
                        // PW 확인 메소드
                        if (sql.idPwCheck(mem)) {
                            // 관리자 확인 메소드
                            if (sql.adminCheck(mem)) {
                                boolean run2 = true;
                                while (run2) {
                                    System.out.println();
                                    System.out.println("─────────────────────────────────────────────────────────");
                                    System.out.println("1. 회원 조회 2. 회원 수정 3. 예약 조회 4. 코드 등록 5. 코드 조회");
                                    System.out.println("6. 편명 등록 7. 편명 조회 8. 일정 등록 9. 일정 조회 0. 로그 아웃");
                                    System.out.println("─────────────────────────────────────────────────────────");
                                    System.out.print("메뉴 선택 : ");
                                    int menu2 = sc.nextInt();
                                    switch (menu2) {
                                        case 1:
                                            // 회원 조회 메소드
                                            sql.mSelect();
                                            break;
                                        case 2:
                                            System.out.print("아이디 입력 : ");
                                            mem.setmId(sc.next());
                                            // ID 확인 메소드
                                            if (sql.idCheck(mem)) {
                                                System.out.println("─────────────────────────────────────────────────────────────");
                                                System.out.println("1. 패스워드 2. 이름 3. 전화번호 4. 여권번호 5. 생년월일 6. 관리자권한");
                                                System.out.println("─────────────────────────────────────────────────────────────");
                                                System.out.print("메뉴 선택 : ");
                                                int umenu = sc.nextInt();
                                                switch (umenu) {
                                                    case 1:
                                                        System.out.print("패스워드 : ");
                                                        mem.setmPw(sc.next());
                                                        // 회원정보 부분수정 메소드(mPw)
                                                        sql.mUpdate(mem, "mPw", mem.getmPw());
                                                        break;
                                                    case 2:
                                                        System.out.print("이름 : ");
                                                        mem.setmName(sc.next());
                                                        // 회원정보 부분수정 메소드(mName)
                                                        sql.mUpdate(mem, "mName", mem.getmName());
                                                        break;
                                                    case 3:
                                                        System.out.print("전화번호 : ");
                                                        mem.setmPhone(sc.next());
                                                        // 회원정보 부분수정 메소드(mPhone)
                                                        sql.mUpdate(mem, "mPhone", mem.getmPhone());
                                                        break;
                                                    case 4:
                                                        System.out.print("여권번호 : ");
                                                        mem.setmPassport(sc.next());
                                                        // 회원정보 부분수정 메소드(mPassport)
                                                        sql.mUpdate(mem, "mPassport", mem.getmPassport());
                                                        break;
                                                    case 5:
                                                        System.out.print("생년월일 : ");
                                                        mem.setmBirth(sc.next());
                                                        // 회원정보 부분수정 메소드(mBirth)
                                                        sql.mUpdate(mem, "mBirth", mem.getmBirth());
                                                        break;
                                                    case 6:
                                                        System.out.println("관리자 권한 부여하시겠습니까? > 1. 예 2. 아니오");
                                                        System.out.print("선택 : ");
                                                        int admin = sc.nextInt();
                                                        if (admin == 1) {
                                                            // 회원정보 부분수정 메소드(mAdmin)
                                                            sql.mUpdate(mem, "mAdmin", 1);
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("잘못 입력했습니다");
                                                }

                                            } else {
                                                System.out.println("아이디가 존재하지 않습니다");
                                            }
                                            break;
                                        case 3:
                                            // 예약 조회 메소드
                                            // RCHECK가 1(결제대기)인 내역이 있는지 체크
                                            if (aSql.rCheck(1)) {
                                                // 입금여부 체크 후 입금 된 내역 RCHECK 2(결제완료)로 변환
                                                // 입금 내역이 없어질 때가지 반복
                                                while (aSql.checkBalance()) {
                                                    // 입금 내역의 mId를 받환 받음
                                                    air.setmId(aSql.pId());
                                                    // RCHECK 1을 2로 변경
                                                    aSql.rUpdate(air, 1);
                                                    // paccount 내역 삭제
                                                    aSql.bPDelete(air);
                                                }
                                            }
                                            // 전체 예약내역 조회(현재 장바구니 내역 포함해서 조회, 빼고 조회하는 것으로 수정해도 무방)
                                            aSql.rSelect();
                                            break;


                                        case 4:
                                            System.out.print("코드 입력 : ");
                                            air.setCode(sc.next());
                                            System.out.print("국가 입력 : ");
                                            air.setNation(sc.next());
                                            System.out.print("도시 입력 : ");
                                            air.setCity(sc.next());
                                            System.out.print("타임존 입력 : ");
                                            air.settZone(sc.nextInt());
                                            // 코드 입력 메소드
                                            aSql.cReg(air);
                                            break;
                                        case 5:
                                            // 코드 조회 메소드
                                            aSql.cSelect();
                                            break;
                                        case 6:
                                            System.out.print("항공편 입력 : ");
                                            air.setaName(sc.next());
                                            System.out.print("비행기종 입력 : ");
                                            air.setaType(sc.next());
                                            System.out.print("항공사 입력 : ");
                                            air.setaCompany(sc.next());
                                            System.out.print("비행시간 입력 : ");
                                            air.setaTime(sc.nextDouble());
                                            System.out.print("출발공항 코드 입력 : ");
                                            air.setdCode(sc.next());
                                            System.out.print("도착공항 코드 입력 : ");
                                            air.setaCode(sc.next());
                                            // 항공편 입력 메소드
                                            aSql.aReg(air);
                                            break;
                                        case 7:
                                            // 항공편 조회 메소드
                                            aSql.aSelect();
                                            break;
                                        case 8:
                                            System.out.print("일정 번호 입력 : ");
                                            air.setsNum(sc.next());
                                            System.out.print("출발시간 입력 : ");
                                            air.setsStart(sc.next());
                                            System.out.print("가격 입력 : ");
                                            air.setsPrice(sc.nextInt());
                                            System.out.print("항공편 입력 : ");
                                            air.setaName(sc.next());
                                            // 일정 입력 메소드
                                            aSql.sReg(air);
                                            break;
                                        case 9:
                                            // 일정 조회 메소드
                                            aSql.sSelect();
                                            break;
                                        case 0:
                                            run2 = false;
                                            System.out.println("로그아웃 성공!");
                                            break;
                                        default:
                                            System.out.println("잘못 입력했습니다");
                                            break;
                                    }
                                }
                            } else {
                                boolean run3 = true;
                                // 로그인시 사용한 아이디가 mem 쪽에만 있어서 아이디 확인을 위해 air에도 set
                                air.setmId(mem.getmId());
                                while (run3) {
                                    int confirm = 0;
                                    int count = 0;
                                    int back = 0;
                                    String select = null;
                                    String compare = null;
                                    System.out.println();
                                    System.out.println("────────────────────────────────────────────────────────────────");
                                    System.out.println("1. 조회하기 2. 추천일정 3. 여행정보 4. 장바구니 5. 마이페이지 6. 로그아웃");
                                    System.out.println("────────────────────────────────────────────────────────────────");
                                    System.out.print("메뉴 선택 : ");
                                    int menu3 = sc.nextInt();
                                    switch (menu3) {
                                        case 1:
                                            // 달력 출력
                                            calender();
                                            System.out.println("출발일을 입력해 주세요(**/**)");
                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                            air.setsStart(sc.next());
                                            System.out.println("여행 지역을 입력해 주세요");
                                            System.out.println("1. 유럽 2. 미주 3. 아시아");
                                            int region = sc.nextInt();
                                            // 각 케이스는 지역 선택 외에는 동일하게 진행 되는 부분이라 메소드 제작하는 방법 고민 필요
                                            switch (region) {
                                                case 1:
                                                    // 일정 테이블에서 유럽만 선택해서 중복을 제거하고 국가,도시만 출력
                                                    aSql.sSelect("EU");
                                                    System.out.println("도시명을 입력해 주세요");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                    if (aSql.sSelect(air, select)) {
                                                        System.out.println("잘못 입력하셨거나, 해당일에 일정이 없습니다");
                                                        break;
                                                    }
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                    if (aSql.sSelect(air)) {
                                                        System.out.println("잘못 입력하셨습니다");
                                                        break;
                                                    }
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {
                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.genrNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        back = sc.nextInt();
                                                        if (back == 1) {
                                                            // 달력 출력
                                                            calender();
                                                            System.out.println("복귀일을 입력해 주세요(**/**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                                            compare = sc.next();
                                                            // 복귀일이 출발일보다 늦은 날짜로 입력할 때까지 재입력
                                                            while (Integer.parseInt(compare.substring(3, 5)) <= Integer.parseInt(air.getsStart().substring(3, 5))) {
                                                                System.out.println("복귀일을 출발일 이후로 입력해 주세요");
                                                                System.out.print("복귀일을 입력해 주세요(**/**) : ");
                                                                compare = sc.next();
                                                                ;
                                                            }
                                                            air.setsStart(compare);
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                            if (aSql.sBSelect(air, select)) {
                                                                System.out.println("잘못 입력하셨거나, 해당일에 일정이 없습니다");
                                                                break;
                                                            }
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                            if (aSql.sSelect(air)) {
                                                                System.out.println("잘못 입력하셨습니다");
                                                                break;
                                                            }
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {
                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.print("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드
                                                                    air.setrNum(aSql.genrNum() + 1);
                                                                    // 아이디와 일정 번호로 예약 테이블에 입력
                                                                    // rTime은 SYSDATE 이용
                                                                    aSql.rReg(air);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 2:
                                                    // 일정 테이블에서 미주만 선택해서 중복을 제거하고 국가,도시만 출력
                                                    aSql.sSelect("AM");
                                                    System.out.println("도시명을 입력해 주세요");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                    if (aSql.sSelect(air, select)) {
                                                        System.out.println("잘못 입력하셨거나, 해당일에 일정이 없습니다");
                                                        break;
                                                    }
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                    if (aSql.sSelect(air)) {
                                                        System.out.println("잘못 입력하셨습니다");
                                                        break;
                                                    }
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {
                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.genrNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        back = sc.nextInt();
                                                        if (back == 1) {
                                                            // 달력 출력
                                                            calender();
                                                            System.out.println("복귀일을 입력해 주세요(**/**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                                            compare = sc.next();
                                                            // 복귀일이 출발일보다 늦은 날짜로 입력할 때까지 재입력
                                                            while (Integer.parseInt(compare.substring(3, 5)) <= Integer.parseInt(air.getsStart().substring(3, 5))) {
                                                                System.out.println("복귀일을 출발일 이후로 입력해 주세요");
                                                                System.out.print("복귀일을 입력해 주세요(**/**) : ");
                                                                compare = sc.next();
                                                                ;
                                                            }
                                                            air.setsStart(compare);
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                            if (aSql.sBSelect(air, select)) {
                                                                System.out.println("잘못 입력하셨거나, 해당일에 일정이 없습니다");
                                                                break;
                                                            }
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                            if (aSql.sSelect(air)) {
                                                                System.out.println("잘못 입력하셨습니다");
                                                                break;
                                                            }
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {
                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.print("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드
                                                                    air.setrNum(aSql.genrNum() + 1);
                                                                    // 아이디와 일정 번호로 예약 테이블에 입력
                                                                    // rTime은 SYSDATE 이용
                                                                    aSql.rReg(air);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 3:
                                                    // 일정 테이블에서 아시아만 선택해서 중복을 제거하고 국가,도시만 출력
                                                    aSql.sSelect("AS");
                                                    System.out.println("도시명을 입력해 주세요");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                    if (aSql.sSelect(air, select)) {
                                                        System.out.println("잘못 입력하셨거나, 해당일에 일정이 없습니다");
                                                        break;
                                                    }
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                    if (aSql.sSelect(air)) {
                                                        System.out.println("잘못 입력하셨습니다");
                                                        break;
                                                    }
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {
                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.genrNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        back = sc.nextInt();
                                                        if (back == 1) {
                                                            // 달력 출력
                                                            calender();
                                                            System.out.println("복귀일을 입력해 주세요(**/**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                                            compare = sc.next();
                                                            // 복귀일이 출발일보다 늦은 날짜로 입력할 때까지 재입력
                                                            while (Integer.parseInt(compare.substring(3, 5)) <= Integer.parseInt(air.getsStart().substring(3, 5))) {
                                                                System.out.println("복귀일을 출발일 이후로 입력해 주세요");
                                                                System.out.print("복귀일을 입력해 주세요(**/**) : ");
                                                                compare = sc.next();
                                                                ;
                                                            }
                                                            air.setsStart(compare);
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                            if (aSql.sBSelect(air, select)) {
                                                                System.out.println("잘못 입력하셨거나, 해당일에 일정이 없습니다");
                                                                break;
                                                            }
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                            if (aSql.sSelect(air)) {
                                                                System.out.println("잘못 입력하셨습니다");
                                                                break;
                                                            }
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {
                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.print("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드
                                                                    air.setrNum(aSql.genrNum() + 1);
                                                                    // 아이디와 일정 번호로 예약 테이블에 입력
                                                                    // rTime은 SYSDATE 이용
                                                                    aSql.rReg(air);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;

                                                default:
                                                    System.out.println("잘못 입력했습니다");
                                                    break;
                                            }
                                            break;
                                        case 2:
                                            System.out.println("이 달의 추천 여행지");
                                            // 전체 DB 에서 지역별로 구분해서 상위 3개의 여행지를 출력
                                            System.out.print("미주 : ");
                                            aSql.recommend("AM-D");
                                            System.out.println();
                                            System.out.print("아시아 : ");
                                            aSql.recommend("AS-D");
                                            System.out.println();
                                            System.out.print("유럽 : ");
                                            aSql.recommend("EU-D");
                                            System.out.println();
                                            System.out.println("도시명을 입력해 주세요");
                                            System.out.print("선택 : ");
                                            select = sc.next();
                                            // 달력 출력
                                            calender();
                                            System.out.println("출발일을 입력해 주세요(**/**)");
                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                            air.setsStart(sc.next());
                                            // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                            if (aSql.sSelect(air, select)) {
                                                System.out.println("잘못 입력하셨거나, 해당일에 일정이 없습니다");
                                                break;
                                            }
                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                            air.setsNum(sc.next());
                                            // 일정번호로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                            if (aSql.sSelect(air)) {
                                                System.out.println("잘못 입력하셨습니다");
                                                break;
                                            }
                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                            System.out.print("선택 : ");
                                            confirm = sc.nextInt();
                                            if (confirm == 1) {
                                                System.out.println("인원 수를 입력해 주세요");
                                                System.out.print("선택 : ");
                                                count = sc.nextInt();
                                                for (int i = 0; i < count; i++) {
                                                    // rNum 생성메소드
                                                    air.setrNum(aSql.genrNum() + 1);
                                                    // 아이디와 일정 번호로 예약 테이블에 입력
                                                    // rTime은 SYSDATE 이용
                                                    aSql.rReg(air);
                                                }
                                                System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                System.out.print("선택 : ");
                                                back = sc.nextInt();
                                                if (back == 1) {
                                                    // 달력 출력
                                                    calender();
                                                    System.out.println("복귀일을 입력해 주세요(**/**)");
                                                    System.out.print("현재 5월만 입력 가능합니다 >> ");
                                                    compare = sc.next();
                                                    // 복귀일이 출발일보다 늦은 날짜로 입력할 때까지 재입력
                                                    while (Integer.parseInt(compare.substring(3, 5)) <= Integer.parseInt(air.getsStart().substring(3, 5))) {
                                                        System.out.println("복귀일을 출발일 이후로 입력해 주세요");
                                                        System.out.print("복귀일을 입력해 주세요(**/**) : ");
                                                        compare = sc.next();
                                                        ;
                                                    }
                                                    air.setsStart(compare);
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                    if (aSql.sBSelect(air, select)) {
                                                        System.out.println("잘못 입력하셨거나, 해당일에 일정이 없습니다");
                                                        break;
                                                    }
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력, boolean 타입으로 참일때 에러 메시지, 거짓일 때 일정 출력
                                                    if (aSql.sSelect(air)) {
                                                        System.out.println("잘못 입력하셨습니다");
                                                        break;
                                                    }
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {
                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.genrNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                        case 3:
                                            System.out.println();
                                            System.out.println("───────────────────────────");
                                            System.out.println("1. 여행지 2. 항공사 3. 돌아가기");
                                            System.out.println("───────────────────────────");
                                            System.out.print("메뉴 선택 : ");
                                            int menu6 = sc.nextInt();
                                            switch (menu6) {
                                                case 1:
                                                System.out.println("여행 지역을 입력해 주세요");
                                                System.out.println("1. 유럽 2. 미주 3. 아시아");
                                                int region2 = sc.nextInt();
                                                // 각 케이스는 지역 선택 외에는 동일하게 진행 되는 부분이라 메소드 제작 고민 필요
                                                switch (region2) {
                                                    case 1:
                                                        // 일정 테이블에서 유럽만 선택해서 중복을 제거하고 국가,도시만 출력
                                                        aSql.sSelect("EU");
                                                        System.out.println("국가명을 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        air.setNation(sc.next());
                                                        // 국가 정보 출력
                                                        aSql.nInfo(air);
                                                        break;
                                                    case 2:
                                                        // 일정 테이블에서 미주만 선택해서 중복을 제거하고 국가,도시만 출력
                                                        aSql.sSelect("AM");
                                                        System.out.println("국가명을 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        air.setNation(sc.next());
                                                        // 국가 정보 출력
                                                        aSql.nInfo(air);
                                                        break;
                                                    case 3:
                                                        // 일정 테이블에서 아시아만 선택해서 중복을 제거하고 국가,도시만 출력
                                                        aSql.sSelect("AS");
                                                        System.out.println("국가명을 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        air.setNation(sc.next());
                                                        // 국가 정보 출력
                                                        aSql.nInfo(air);
                                                        break;
                                                }
                                                break;
                                                case 2:
                                                    System.out.println("항공사를 입력해 주세요");
                                                    System.out.println("1. 대한항공 2. 아시아나항공 3. 진에어");
                                                    int aCompany=sc.nextInt();
                                                    // 선택한 번호에 맞는 항공사 정보 출력
                                                    if(aCompany==1) {
                                                        aSql.cInfo("대한항공");
                                                    } else if(aCompany==2){
                                                        aSql.cInfo("아시아나항공");
                                                    } else if(aCompany==3){
                                                        aSql.cInfo("진에어");
                                                    }
                                                    break;
                                                case 3:
                                                    break;
                                            }
                                            break;
                                        case 4:
                                            System.out.println("1. 장바구니 2. 결제대기");
                                            System.out.print("선택 : ");
                                            int pay = sc.nextInt();
                                            if (pay == 1) {
                                                System.out.println(" [ 장바구니 ]");
                                                // 아이디에 해당하는 예약정보중 RCHECK가 0인 내역(장바구니)만 출력
                                                if (aSql.rCheck(air, 0)) {
                                                    aSql.rSelect(air, 0);
                                                    System.out.println("1. 계좌발급 2. 삭제");
                                                    System.out.print("선택 : ");
                                                    int payAccount = sc.nextInt();
                                                    if (payAccount == 1) {
                                                        // RCHECK=1인 내역이 있으면 계좌 발급 불가 메세지 출력
                                                        if (aSql.rCheck(air, 1)) {
                                                            System.out.println("결제 대기 중인 내역이 있습니다.");
                                                        } else {
                                                            // 계좌 번호 생성 메소드
                                                            air.settAccount(aSql.bPAccount());
                                                            // 계좌 생성 메소드 : 계좌 생성
                                                            aSql.bPCreate(air);
                                                            // 입금 금액 생성 : RCHECK가 0인 내역의 합계
                                                            air.setaTotal(aSql.rTotal(air));
                                                            // RCHECK가 0인 내역의 리스트 합계
                                                            air.setaCount(aSql.rCount(air));
                                                            // PACCOUNT 테이블에 데이터 추가
                                                            aSql.pCreate(air);
                                                            // 계좌 출력 메소드
                                                            aSql.pSelect(air);
                                                            // RCHECK 0을 1로 변경
                                                            aSql.rUpdate(air, 0);
                                                        }
                                                    } else if (payAccount == 2) {
                                                        System.out.print("예약번호 선택 : ");
                                                        air.setrNum(sc.nextInt());
                                                        // 예약번호 받아서 삭제
                                                        aSql.rDelete(air);

                                                    }
                                                } else {
                                                    System.out.println("예약 내역이 없습니다");
                                                }
                                            } else if (pay == 2) {
                                                System.out.println(" [ 결제대기 ]");
                                                // 입금 확인 메소드
                                                // RCHECK가 1인 내역이 있는지 체크
                                                if (aSql.rCheck(air, 1)) {
                                                    // 입금이 됐는지 확인
                                                    if (aSql.checkBalance(air)) {
                                                        // RCHECK 1을 2로 변경
                                                        aSql.rUpdate(air, 1);
                                                        // 결제대기 내역 삭제
                                                        aSql.bPDelete(air);
                                                    } else {
                                                        // 멤버 아이디에 해당하는 예약정보중 RCHECK가 1인 내역(결제대기)만 출력
                                                        aSql.rSelect(air, 1);
                                                        // 결제 계좌 출력
                                                        aSql.pSelect(air);
                                                    }
                                                }

                                            }
                                            break;

                                        case 5:
                                            boolean run4 = true;
                                            while (run4) {
                                                System.out.println();
                                                System.out.println("────────────────────────────────────────────");
                                                System.out.println("1. 정보 조회 2. 정보 수정 3. 예약 내역 4. 돌아가기");
                                                System.out.println("────────────────────────────────────────────");
                                                System.out.print("메뉴 선택 : ");
                                                int menu4 = sc.nextInt();
                                                switch (menu4) {
                                                    case 1:
                                                        // 정보 조회
                                                        sql.mSelect(mem);
                                                        break;
                                                    case 2:
                                                        System.out.println("─────────────────────────────────────────────────");
                                                        System.out.println("1. 패스워드 2. 이름 3. 전화번호 4. 여권번호 5. 생년월일");
                                                        System.out.println("─────────────────────────────────────────────────");
                                                        System.out.print("메뉴 선택 : ");
                                                        int menu5 = sc.nextInt();
                                                        switch (menu5) {
                                                            case 1:
                                                                System.out.print("패스워드 : ");
                                                                mem.setmPw(sc.next());
                                                                // 회원정보 부분수정 메소드(mPw)
                                                                sql.mUpdate(mem, "mPw", mem.getmPw());
                                                                break;
                                                            case 2:
                                                                System.out.print("이름 : ");
                                                                mem.setmName(sc.next());
                                                                // 회원정보 부분수정 메소드(mName)
                                                                sql.mUpdate(mem, "mName", mem.getmName());
                                                                break;
                                                            case 3:
                                                                System.out.print("전화번호 : ");
                                                                mem.setmPhone(sc.next());
                                                                // 회원정보 부분수정 메소드(mPhone)
                                                                sql.mUpdate(mem, "mPhone", mem.getmPhone());
                                                                break;
                                                            case 4:
                                                                System.out.print("여권번호 : ");
                                                                mem.setmPassport(sc.next());
                                                                // 회원정보 부분수정 메소드(mPassport)
                                                                sql.mUpdate(mem, "mPassport", mem.getmPassport());
                                                                break;
                                                            case 5:
                                                                System.out.print("생년월일 : ");
                                                                mem.setmBirth(sc.next());
                                                                // 회원정보 부분수정 메소드(mBirth)
                                                                sql.mUpdate(mem, "mBirth", mem.getmBirth());
                                                                break;
                                                            default:
                                                                System.out.println("잘못 입력했습니다");
                                                        }
                                                        break;
                                                    case 3:
                                                        // RCHECK가 1인 내역이 있는지 체크
                                                        if (aSql.rCheck(air, 1)) {
                                                            // 입금이 됐는지 확인
                                                            if (aSql.checkBalance(air)) {
                                                                //RCHECK 1을 2로 변경
                                                                aSql.rUpdate(air, 1);
                                                                // 결제대기 내역 삭제
                                                                aSql.bPDelete(air);
                                                            } else {
                                                                System.out.println("입금 대기 내역이 있습니다 장바구니에서 확인하세요");
                                                            }
                                                        }
                                                        // 멤버 아이디에 해당하는 예약정보중 RCHECK가 2인 내역(결제완료)만 출력
                                                        if (aSql.rSelect(air, 2)) {
                                                            System.out.print("예약번호 입력 : ");
                                                            air.setrNum(sc.nextInt());
                                                            // 선택한 예약번호만 따로 출력
                                                            aSql.rSelect(air);
                                                        }
                                                        break;
                                                    case 4:
                                                        run4 = false;
                                                        break;
                                                    default:
                                                        System.out.println("잘못 입력했습니다");
                                                        break;

                                                }
                                            }
                                            break;
                                        case 6:
                                            run3 = false;
                                            System.out.println("로그아웃 성공");
                                            break;
                                        default:
                                            System.out.println("잘못 입력했습니다");
                                            break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("패스워드를 잘못 입력했습니다");
                        }
                    } else {
                        System.out.println("아이디가 존재하지 않습니다");
                    }
                    break;
                case 3:
                    System.out.println("1. 아이디 찾기 2. 비밀번호 찾기");
                    System.out.print("메뉴 선택 : ");
                    int find = sc.nextInt();
                    if (find == 1) {
                        System.out.print("이름 입력 : ");
                        mem.setmName(sc.next());
                        System.out.println("연락처 입력 : ");
                        mem.setmPhone(sc.next());
                        // 입력한 정보가 맞으면 아이디 출력
                        sql.findId(mem);
                    } else if (find == 2) {
                        System.out.print("아이디 입력 : ");
                        mem.setmId(sc.next());
                        // 아이디 확인
                        if (sql.idCheck(mem)) {
                            System.out.print("이름 입력 : ");
                            mem.setmName(sc.next());
                            System.out.println("연락처 입력 : ");
                            mem.setmPhone(sc.next());
                            System.out.println("여권번호 입력 : ");
                            mem.setmPassport(sc.next());
                            // 입력한 정보가 맞으면 비밀번호 출력
                            sql.findPw(mem);
                        } else {
                            System.out.println("아이디가 존재하지 않습니다");
                        }

                    }
                    break;
                case 4:
                    System.out.println("이용해 주셔서 감사합니다");
                    run = false;
                    sql.conClose();
                    break;
                default:
                    System.out.println("잘못 입력했습니다");
                    break;
            }
        }
    }

}
