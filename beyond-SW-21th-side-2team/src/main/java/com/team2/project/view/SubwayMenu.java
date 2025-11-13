package com.team2.project.view;

import com.team2.project.controller.SubwayManager;

import java.util.Scanner;

// SubwayMenu.java
public class SubwayMenu {
    private SubwayManager sm = new SubwayManager();

    public void mainMenu() {
        sm.setUp();
        StationByLinePrinter sp = new StationByLinePrinter();
        Scanner sc = new Scanner(System.in);
        boolean on = true;
        while(on){
            System.out.println("""
                    환영합니다
                    1. 노선 정보 확인
                    2. 최단 거리 노선 계산
                    3. 시간 계산 
                    9. 종료
                    """);
            int input = sc.nextInt();
            sc.nextLine();
            switch (input){
                case 1: sm.selectAll();
                    break;
                case 2:
                    System.out.println("출발역을 입력해주세요");
                    String start = sc.nextLine();
                    System.out.println("도착역을 입력해주세요");
                    String end = sc.nextLine();
                    sm.bfs(sm.findStationNo(start),sm.findStationNo(end));
                    break;
                case 3:
                    System.out.println("출발역을 입력해주세요");
                    start = sc.nextLine();
                    System.out.println("도착역을 입력해주세요");
                    end = sc.nextLine();
                    System.out.println("예상 소요시간: " + sm.timeCalc(sm.findStationNo(start),sm.findStationNo(end)) + "분");
                    break;
                    case 9: on = false;
                    break;
            }
        }
    }
}

