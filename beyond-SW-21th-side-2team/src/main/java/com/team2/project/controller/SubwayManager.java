package com.team2.project.controller;

// SubwayManager.java
import com.team2.project.model.dto.StationDTO;
import com.team2.project.view.StationByLinePrinter;

import java.util.*;

public class SubwayManager {
    private ArrayList<ArrayList<Integer>> stationConnection = new ArrayList<>();
    private ArrayList<StationDTO> stations;
    private StationByLinePrinter sp;

    public void setUp() {
        /*
        신분당선 No = 1
        수인분당선 No = 2
        3호선 No = 3
         */
        String sinbundang = "광교중앙-상현-성복-수지구청-동천-미금-정자-판교-청계산입구-양재시민의숲-양재-강남-신논현-논현-신사";
        String suinbundang = "기흥-신갈-구성-보정-죽전-오리-미금-정자-수내-서현-이매-야탑-모란-태평-가천대-복정-수서-대모산입구-개포동-구룡-도곡";
        String line3 = "수서-일원-대청-학여울-대치-도곡-매봉-양재-남부터미널-교대-고속터미널-잠원-신사";

        /*
        ArrayList<StationDTO> 초기화
         */
        // 신분당선
        stations = new ArrayList<>();
        int stationNo = 1;
        String tmpExit = "2-1";
        String[] sinSplit = sinbundang.split("-");
        for (int i = 0; i < sinSplit.length; i++) {
            StationDTO station = new StationDTO();
            station.setStationName(sinSplit[i]);
            String name = station.getStationName();
            station.setStationNo(stationNo);
            stationNo++;
            station.setFastExit(tmpExit);
            if (name.equals("미금") || name.equals("정자")) {
                int[] lineNo = {1, 2};
                station.setLineNo(lineNo);
                station.setTransfer(true);
            } else if (name.equals("양재") || name.equals("신사")) {
                int[] lineNo = {1, 3};
                station.setLineNo(lineNo);
                station.setTransfer(true);
            } else {
                int[] lineNo = {1};
                station.setLineNo(lineNo);
            }

            stations.add(station);
        }

        // 수인분당선
        tmpExit = "4-1";
        String[] suinSplit = suinbundang.split("-");
        for (int i = 0; i < suinSplit.length; i++) {
            StationDTO station = new StationDTO();
            station.setStationName(suinSplit[i]);
            String name = station.getStationName();

            if (name.equals("미금") || name.equals("정자")) {
                continue;
            } else if (name.equals("도곡") || name.equals("수서")) {
                int[] lineNo = {2, 3};
                station.setLineNo(lineNo);
                station.setTransfer(true);
            } else {
                int[] lineNo = {2};
                station.setLineNo(lineNo);
            }
            station.setStationNo(stationNo);
            stationNo++;
            station.setFastExit(tmpExit);

            stations.add(station);
        }

        // 3호선
        tmpExit = "6-1";
        String[] line3Split = line3.split("-");
        for (int i = 0; i < line3Split.length; i++) {
            StationDTO station = new StationDTO();
            station.setStationName(line3Split[i]);
            String name = station.getStationName();

            if (name.equals("도곡") || name.equals("수서")) {
                continue;
            } else if (name.equals("양재") || name.equals("신사")) {
                continue;
            } else {
                int[] lineNo = {3};
                station.setLineNo(lineNo);
            }
            station.setStationNo(stationNo);
            stationNo++;
            station.setFastExit(tmpExit);

            stations.add(station);
        }

        /*
        ArrayList<ArrayList<Integer>> 인접리스트 기록
         */
        for (int i = 0; i < stations.size()+1; i++) {
            stationConnection.add(i, new ArrayList<Integer>());
        }

        for (int i = 1; i <= stations.size(); i++) {
            switch (i) {
                case 1: // 기흥
                    stationConnection.get(i).add(i+1);
                    break;
                case 6: // 미금
                    stationConnection.get(i).add(i-1);
                    stationConnection.get(i).add(i+1);
                    stationConnection.get(i).add(21);
                    break;
                case 7: // 정자
                    stationConnection.get(i).add(i-1);
                    stationConnection.get(i).add(i+1);
                    stationConnection.get(i).add(22);
                    break;
                case 11:// 양재
                    stationConnection.get(i).add(i-1);
                    stationConnection.get(i).add(i+1);
                    stationConnection.get(i).add(39);
                    stationConnection.get(i).add(40);
                    break;
                case 15:// 신사
                    stationConnection.get(i).add(i-1);
                    stationConnection.get(i).add(43);
                    break;
                case 16:// 기흥
                    stationConnection.get(i).add(i+1);
                    break;
                case 21:// 오리
                    stationConnection.get(i).add(i-1);
                    stationConnection.get(i).add(6);
                    break;
                case 22:// 수내
                    stationConnection.get(i).add(i+1);
                    stationConnection.get(i).add(7);
                    break;
                case 30:// 수서
                    stationConnection.get(i).add(i-1);
                    stationConnection.get(i).add(i+1);
                    stationConnection.get(i).add(35);
                    break;
                case 34:// 도곡
                    stationConnection.get(i).add(i-1);
                    stationConnection.get(i).add(38);
                    stationConnection.get(i).add(39);
                    break;
                case 35:// 일원
                    stationConnection.get(i).add(i+1);
                    stationConnection.get(i).add(30);
                    break;
                case 39:// 매봉
                    stationConnection.get(i).add(34);
                    stationConnection.get(i).add(11);
                    break;
                case 40:// 남부터미널
                    stationConnection.get(i).add(i+1);
                    stationConnection.get(i).add(11);
                    break;
                case 43:// 잠원
                    stationConnection.get(i).add(i-1);
                    stationConnection.get(i).add(15);
                    break;
                default:// 그외 모든 역
                    stationConnection.get(i).add(i-1);
                    stationConnection.get(i).add(i+1);
                    break;
            }
        }
    }

    /* @Param:
     * int start: 출발역의 고유번호
     * int end: 도착역의 고유번호
     * @Return: findStationNo() 메소드를 통해 역의 고유 번호를 받고
     * 출발역에서 종점역까지의 거리 (역의 갯수)를 계산해서 시간을 반환한다*/
    public int timeCalc(int start, int end){

        int stations = 0;
        String lines = bfs(start, end);
        for(String line : lines.split("-")){
            stations++;
        }

        return stations*5;

    }


    public String bfs(int start, int end) {

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[stations.size() + 1]; //방문 기록
        int [] parent = new int[stations.size() + 1]; //최소 노선 기록

        parent[start] = start;
        queue.offer(start);
        visit[start] = true;

        a:
        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int station : stationConnection.get(current)) {
                if(!visit[station]) {
                    visit[station] = true; //방문하지 않았다면 방문 기록을 true로 함
                    parent[station] = current; // 최근 방문했던 역을 기록
                    if(station == end) { // 종점역 확인
                        break a; //브릴리언트 윤혜님의 혜안
                    }
                    queue.offer(station);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = end;
        while(i != start){
            i = parent[i];
            sb.append(stations.get(i).getStationName()).append("-");
        }
        sb.append(stations.get(start-1).getStationName());
        return sb.toString();
    }

    public int findStationNo(String name){
        // 사용자에게 입력받은 각각의 역 이름에 맞는 번호를 뽑아내는 메서드

        StationDTO station = new StationDTO();

        int stationNo = 0;

        for(int i = 1; i<=stations.size();i++){

            if(stations.get(i).getStationName().equals(name)){
                stationNo = stations.get(i).getStationNo();
            }
        }
        return stationNo;
    }

    public void selectAll(){
        sp.printAllStation(stations);

    }

}

