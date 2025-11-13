package com.team2.project.view;

// StationByLinePrinter.java
import com.team2.project.controller.SubwayManager;
import com.team2.project.model.dto.StationDTO;

import java.util.ArrayList;

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

    public void printAllStation( ArrayList<StationDTO> stations) {
    }
}
