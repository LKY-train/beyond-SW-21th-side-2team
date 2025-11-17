package com.team2.project.view;

import com.team2.project.controller.SubwayManager;
import com.team2.project.model.dto.StationDTO;
import com.team2.project.service.SubwayService;

import java.util.ArrayList;
import java.util.Scanner;

// SubwayMenu.java
public class SubwayMenu {
    private SubwayService service = new SubwayService();
    private SubwayManager sm = new SubwayManager();

    public void mainMenu() {
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
                case 1:
                    // Get all stations from database
                    sm.selectAll();
                    break;
                case 2:
                    System.out.println("출발역을 입력해주세요");
                    String start = sc.nextLine();
                    System.out.println("도착역을 입력해주세요");
                    String end = sc.nextLine();
                    String path = service.findShortestPath(start, end);
                    if (path != null) {
                        // SubwayService returns path in forward order, but printPath expects reverse
                        String reversePath = reversePath(path);
                        sp.printPath(reversePath);
                    } else {
                        System.out.println("경로를 찾을 수 없습니다. 역 이름을 확인해주세요.");
                    }
                    break;
                case 3:
                    System.out.println("출발역을 입력해주세요");
                    start = sc.nextLine();
                    System.out.println("도착역을 입력해주세요");
                    end = sc.nextLine();
                    int travelTime = service.calculateTravelTime(start, end);
                    if (travelTime >= 0) {
                        System.out.println("예상 소요시간: " + travelTime + "분");
                    } else {
                        System.out.println("경로를 찾을 수 없습니다. 역 이름을 확인해주세요.");
                    }
                    break;
                case 9:
                    on = false;
                    break;
            }
        }
    }

    /**
     * Reverses a path string (e.g., "A-B-C" becomes "C-B-A")
     */
    private String reversePath(String path) {
        String[] stations = path.split("-");
        StringBuilder reversed = new StringBuilder();
        for (int i = stations.length - 1; i >= 0; i--) {
            reversed.append(stations[i]);
            if (i > 0) {
                reversed.append("-");
            }
        }
        return reversed.toString();
    }
}

