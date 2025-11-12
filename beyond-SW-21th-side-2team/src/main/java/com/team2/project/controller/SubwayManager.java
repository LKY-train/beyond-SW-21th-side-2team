package com.team2.project.controller;

// SubwayManager.java
import com.team2.project.model.dto.StationDTO;
import com.team2.project.view.StationByLinePrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
        System.out.println("connection list " + stationConnection.size());

        System.out.println("station: " + stations.size());
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

//        // 신분당선
//        stationConnection.get(1).add(2);    //
//        stationConnection.get(2).add(1);
//        stationConnection.get(2).add(3);
//        stationConnection.get(3).add(2);
//        stationConnection.get(3).add(4);
//        stationConnection.get(4).add(3);
//        stationConnection.get(4).add(5);
//        stationConnection.get(5).add(4);
//        stationConnection.get(5).add(6);
//        stationConnection.get(6).add(5);
//        stationConnection.get(6).add(7);
//        stationConnection.get(6).add(21);   //
//        stationConnection.get(7).add(6);
//        stationConnection.get(7).add(8);
//        stationConnection.get(8).add(7);
//        stationConnection.get(8).add(9);
//        stationConnection.get(9).add(8);
//        stationConnection.get(9).add(10);
//        stationConnection.get(10).add(9);
//        stationConnection.get(10).add(11);
//        stationConnection.get(11).add(10);
//        stationConnection.get(11).add(12);
//        stationConnection.get(11).add(39);  //
//        stationConnection.get(11).add(40);  //
//        stationConnection.get(12).add(11);
//        stationConnection.get(12).add(13);
//        stationConnection.get(13).add(12);
//        stationConnection.get(13).add(14);
//        stationConnection.get(14).add(13);
//        stationConnection.get(14).add(15);
//        stationConnection.get(15).add(14);
//        stationConnection.get(15).add(43);  //
//
//
//        // 수인분당선
//        stationConnection.get(16).add(17);  //
//        stationConnection.get(17).add(16);
//        stationConnection.get(17).add(18);
//        stationConnection.get(18).add(17);
//        stationConnection.get(18).add(19);
//        stationConnection.get(19).add(18);
//        stationConnection.get(19).add(20);
//        stationConnection.get(20).add(19);
//        stationConnection.get(20).add(21);
//        stationConnection.get(21).add(20);
//        stationConnection.get(21).add(6);   //
//        stationConnection.get(22).add(21);
//        stationConnection.get(22).add(23);
//        stationConnection.get(23).add(22);
//        stationConnection.get(23).add(24);
//        stationConnection.get(24).add(23);
//        stationConnection.get(24).add(25);
//        stationConnection.get(25).add(24);
//        stationConnection.get(25).add(26);
//        stationConnection.get(26).add(25);
//        stationConnection.get(26).add(27);
//        stationConnection.get(27).add(26);
//        stationConnection.get(27).add(28);
//        stationConnection.get(28).add(27);
//        stationConnection.get(28).add(29);
//        stationConnection.get(29).add(28);
//        stationConnection.get(29).add(30);
//        stationConnection.get(30).add(29);
//        stationConnection.get(30).add(31);
//        stationConnection.get(30).add(35);   //
//        stationConnection.get(31).add(30);
//        stationConnection.get(31).add(32);
//        stationConnection.get(32).add(31);
//        stationConnection.get(32).add(33);
//        stationConnection.get(33).add(32);
//        stationConnection.get(33).add(34);
//        stationConnection.get(34).add(33);
//        stationConnection.get(34).add(38);  //
//        stationConnection.get(34).add(39);  //
//
//
//        // 3호선
//        stationConnection.get(35).add(30);  //
//        stationConnection.get(35).add(36);  //
//        stationConnection.get(36).add(35);
//        stationConnection.get(36).add(37);
//        stationConnection.get(37).add(36);
//        stationConnection.get(37).add(38);
//        stationConnection.get(38).add(37);
//        stationConnection.get(38).add(39);
//        stationConnection.get(39).add(38);
//        stationConnection.get(39).add(40);
//        stationConnection.get(40).add(39);
//        stationConnection.get(40).add(41);
//        stationConnection.get(41).add(40);
//        stationConnection.get(41).add(42);
//        stationConnection.get(42).add(41);
//        stationConnection.get(42).add(43);
//        stationConnection.get(43).add(42);
//        stationConnection.get(43).add(15);  //


        for (int i = 0; i < stationConnection.size(); i++) {
            System.out.println(i + " : " + stationConnection.get(i));
        }


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

        for(int i = 1; i<=stations.size();i++){

            if(stations.get(i).getStationName().equals(name)){
                stationNo = stations.get(i).getStationNo();
            }

        }

        return stationNo;

    }
}

