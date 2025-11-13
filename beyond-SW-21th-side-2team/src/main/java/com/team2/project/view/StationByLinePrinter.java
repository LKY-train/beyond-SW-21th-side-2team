package com.team2.project.view;

// StationByLinePrinter.java
import com.team2.project.controller.SubwayManager;
import com.team2.project.model.dto.StationDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StationByLinePrinter {

    public void printPath(String reversePath) {
        StringBuilder sb = new StringBuilder("최단경로 : ");
        String[] rPaths = reversePath.split("-");
        int pathNums = rPaths.length;
        for (int i = 0; i < pathNums; i++) {
            sb.append(rPaths[pathNums - i - 1]).append("-");
        }
        sb.setLength(sb.length() - 1);
        String info = "총 " + pathNums + "개의 역을 지나야 합니다.";
        sb.append("\n").append(info);
        System.out.println(sb.toString());
    }

    public void printAllStation(ArrayList<StationDTO> stations) {

        Scanner sc = new Scanner(System.in);
        int line = 0;

        do {
            System.out.println("""
                    1. 신분당선
                    2. 수인분당선
                    3. 3호선
                    4. 나가기 """);
            System.out.print("검색하고 싶은 라인의 번호를 입력하세요 : ");
            line = sc.nextInt();

            for(int i = 0; i < stations.size(); i++) {
                switch (line) {
                    case 1:
                        System.out.println(stations.get(i).getStationName() +" ");
                        break;
                    case 2:
                        System.out.println(stations.get(i).getStationName() +" ");
                        break;
                    case 3:
                        System.out.println(stations.get(i).getStationName() +" ");
                        break;
                    default:
                        System.out.println("번호를 잘못 입력하셨습니다. : ");
                        break;
                }
            }
        } while (line != 4);

        System.out.println("hello world");

    }
}
