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
        while (line != 4){
            System.out.println("""
                    1. 신분당선
                    2. 수인분당선
                    3. 3호선
                    4. 나가기 """);
            System.out.print("검색하고 싶은 라인의 번호를 입력하세요 : ");
            line = sc.nextInt();
            String sinbundang = "광교중앙-상현-성복-수지구청-동천-미금-정자-판교-청계산입구-양재시민의숲-양재-강남-신논현-논현-신사";
            String suinbundang = "기흥-신갈-구성-보정-죽전-오리-미금-정자-수내-서현-이매-야탑-모란-태평-가천대-복정-수서-대모산입구-개포동-구룡-도곡";
            String line3 = "수서-일원-대청-학여울-대치-도곡-매봉-양재-남부터미널-교대-고속터미널-잠원-신사";

            switch (line) {
                case 1:
                    System.out.println(sinbundang);
                    break;
                case 2:
                    System.out.println(suinbundang);
                    break;
                case 3:
                    System.out.println(line3);
                    break;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다. : ");
                    break;
            }
        }
    }
}
