package com.team2.project.run;

import com.team2.project.model.dto.StationDTO;
import com.team2.project.model.dto.TransferStationDTO;
import com.team2.project.service.SubwayService;

import java.util.List;

/**
 * MyBatis 데이터베이스 연동 예제
 * 
 * 이 파일은 데이터베이스를 사용하는 다양한 예제를 보여줍니다.
 */
public class DatabaseExampleUsage {
    
    public static void main(String[] args) {
        SubwayService service = new SubwayService();
        
        System.out.println("=== 지하철 데이터베이스 예제 ===\n");
        
        // 1. 모든 역 조회
        example1_getAllStations(service);
        
        // 2. 특정 노선의 역 조회
        example2_getStationsByLine(service);
        
        // 3. 환승역 조회
        example3_getTransferStations(service);
        
        // 4. 역 이름으로 역 정보 조회
        example4_getStationByName(service);
        
        // 5. 최단 경로 찾기
        example5_findShortestPath(service);
        
        // 6. 소요 시간 계산
        example6_calculateTravelTime(service);
        
        // 7. 환승역 정보 조회
        example7_getTransferStationInfo(service);
    }
    
    /**
     * 예제 1: 모든 역 조회
     */
    private static void example1_getAllStations(SubwayService service) {
        System.out.println("📍 예제 1: 모든 역 조회");
        System.out.println("─".repeat(50));
        
        List<StationDTO> stations = service.getAllStations();
        System.out.println("총 " + stations.size() + "개의 역이 있습니다.\n");
        
        // 처음 5개만 출력
        for (int i = 0; i < Math.min(5, stations.size()); i++) {
            StationDTO station = stations.get(i);
            System.out.printf("%d. %s (역번호: %d, 빠른출구: %s, 환승역: %s)\n",
                    i + 1,
                    station.getStationName(),
                    station.getStationNo(),
                    station.getFastExit(),
                    station.isTransfer() ? "예" : "아니오");
            
            if (station.getLineNumbers() != null && !station.getLineNumbers().isEmpty()) {
                System.out.print("   노선: ");
                for (int lineNo : station.getLineNumbers()) {
                    System.out.print(lineNo + "호선 ");
                }
                System.out.println();
            }
        }
        System.out.println("...(생략)\n\n");
    }
    
    /**
     * 예제 2: 특정 노선의 역 조회
     */
    private static void example2_getStationsByLine(SubwayService service) {
        System.out.println("🚇 예제 2: 신분당선(1호선) 역 조회");
        System.out.println("─".repeat(50));
        
        List<StationDTO> stations = service.getStationsByLine(1);
        System.out.println("신분당선에는 " + stations.size() + "개의 역이 있습니다.");
        
        for (int i = 0; i < stations.size(); i++) {
            System.out.print(stations.get(i).getStationName());
            if (i < stations.size() - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println("\n\n");
    }
    
    /**
     * 예제 3: 환승역 조회
     */
    private static void example3_getTransferStations(SubwayService service) {
        System.out.println("🔄 예제 3: 환승역 조회");
        System.out.println("─".repeat(50));
        
        List<StationDTO> transferStations = service.getTransferStations();
        System.out.println("총 " + transferStations.size() + "개의 환승역이 있습니다.\n");
        
        for (StationDTO station : transferStations) {
            System.out.printf("• %s역\n", station.getStationName());
            if (station.getLineNumbers() != null) {
                System.out.print("  환승 가능 노선: ");
                for (int lineNo : station.getLineNumbers()) {
                    System.out.print(lineNo + "호선 ");
                }
                System.out.println();
            }
        }
        System.out.println("\n");
    }
    
    /**
     * 예제 4: 역 이름으로 역 정보 조회
     */
    private static void example4_getStationByName(SubwayService service) {
        System.out.println("🔍 예제 4: 역 이름으로 검색");
        System.out.println("─".repeat(50));
        
        String searchName = "양재";
        StationDTO station = service.getStationByName(searchName);
        
        if (station != null) {
            System.out.printf("역 이름: %s\n", station.getStationName());
            System.out.printf("역 번호: %d\n", station.getStationNo());
            System.out.printf("빠른 출구: %s\n", station.getFastExit());
            System.out.printf("환승역 여부: %s\n", station.isTransfer() ? "예" : "아니오");
            
            if (station.getLineNumbers() != null) {
                System.out.print("운행 노선: ");
                for (int lineNo : station.getLineNumbers()) {
                    System.out.print(lineNo + "호선 ");
                }
                System.out.println();
            }
        } else {
            System.out.println("역을 찾을 수 없습니다.");
        }
        System.out.println("\n");
    }
    
    /**
     * 예제 5: 최단 경로 찾기
     */
    private static void example5_findShortestPath(SubwayService service) {
        System.out.println("🗺️ 예제 5: 최단 경로 찾기");
        System.out.println("─".repeat(50));
        
        String start = "광교중앙";
        String end = "강남";
        
        System.out.printf("출발역: %s\n", start);
        System.out.printf("도착역: %s\n", end);
        
        String path = service.findShortestPath(start, end);
        
        if (path != null) {
            System.out.println("\n최단 경로:");
            String[] stations = path.split("-");
            for (int i = 0; i < stations.length; i++) {
                System.out.print(stations[i]);
                if (i < stations.length - 1) {
                    System.out.print(" → ");
                }
            }
            System.out.printf("\n\n총 %d개 역을 지나갑니다.\n", stations.length);
        } else {
            System.out.println("경로를 찾을 수 없습니다.");
        }
        System.out.println("\n");
    }
    
    /**
     * 예제 6: 소요 시간 계산
     */
    private static void example6_calculateTravelTime(SubwayService service) {
        System.out.println("⏱️ 예제 6: 소요 시간 계산");
        System.out.println("─".repeat(50));
        
        String start = "미금";
        String end = "양재";
        
        System.out.printf("출발역: %s\n", start);
        System.out.printf("도착역: %s\n", end);
        
        int time = service.calculateTravelTime(start, end);
        
        if (time > 0) {
            System.out.printf("\n예상 소요 시간: %d분\n", time);
            System.out.printf("(환승 시간 제외)\n");
        } else {
            System.out.println("시간을 계산할 수 없습니다.");
        }
        System.out.println("\n");
    }
    
    /**
     * 예제 7: 환승역 상세 정보 조회
     */
    private static void example7_getTransferStationInfo(SubwayService service) {
        System.out.println("ℹ️ 예제 7: 환승역 상세 정보");
        System.out.println("─".repeat(50));
        
        String stationName = "미금";
        TransferStationDTO transferInfo = service.getTransferStationInfo(stationName);
        
        if (transferInfo != null) {
            System.out.printf("역 이름: %s\n", transferInfo.getStationName());
            System.out.printf("환승 소요 시간: %d분\n", transferInfo.getTransferTime());
            
            if (transferInfo.getLineNames() != null && !transferInfo.getLineNames().isEmpty()) {
                System.out.print("환승 가능 노선: ");
                for (String lineName : transferInfo.getLineNames()) {
                    System.out.print(lineName + " ");
                }
                System.out.println();
            }
        } else {
            System.out.printf("%s역은 환승역이 아닙니다.\n", stationName);
        }
        System.out.println("\n");
    }
}
