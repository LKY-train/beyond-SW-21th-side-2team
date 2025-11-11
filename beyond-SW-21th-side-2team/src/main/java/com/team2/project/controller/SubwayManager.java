package com.team2.project.controller;

// SubwayManager.java
import com.team2.project.model.dto.StationDTO;
import com.team2.project.view.StationByLinePrinter;

import java.util.ArrayList;
import java.util.Scanner;

public class SubwayManager {
    private ArrayList<ArrayList<Integer>> stationConnection;
    private ArrayList<StationDTO> stations;
    private StationByLinePrinter sp;

    public void setUp() {
        String sinbundang = "광교중앙-상현-성복-수지구청-동천-미금-정자-판교-청계산입구-양재시민의숲-양재-강남-신논현-논현-신사";
        String suinbundang = "기흥-구성-보정-죽전-오리-미금-정자-수내-서현-이매-야탑-모란-태평-가천대-복정-수서-대모산입구-개포동-구룡-도곡";
        String line3 = "수서-일원-대청-학여울-대치-도곡-매봉-양재-남부터미널-교대-고속터미널-잠원-신사";
    }

    /* @Param:
     * int start: 출발역의 고유번호
     * int end: 도착역의 고유번호
     * @Return: 현재는 랜덤으로 구현
     * 이후에는 아직 미구현인 convert() 메소드를 통해 역의 고유 번호를 받고
     * 출발역에서 종점역까지의 거리 (역의 갯수)를 계산해서 시간을 반환한다*/
    public int timeCalc(int start, int end){

        return (int) (Math.random()*60 )+ 1;

    }

    public ArrayList<StationDTO> bfs(int start) {
        return new ArrayList<>();
    }


    public int findStationNo(String name){
        // 사용자에게 입력받은 각각의 역 이름에 맞는 번호를 뽑아내는 메서드
        
        StationDTO station = new StationDTO();

        int stationNo = 0;

        for(int i = 0; i<stations.size();i++){

            if(stations.get(i).getStationName().equals(name)){
                stationNo = stations.get(i).getStationNo();
            }

        }

        return stationNo;

    }
}

