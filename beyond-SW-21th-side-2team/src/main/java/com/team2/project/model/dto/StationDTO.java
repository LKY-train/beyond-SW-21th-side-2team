package com.team2.project.model.dto;

import java.util.Arrays;
import java.util.List;

/**
 * 역 정보 DTO (데이터베이스 버전)
 */
public class StationDTO {
    // 데이터베이스 필드
    private int stationId;      // Primary Key
    private int stationNo;      // 역 고유 번호
    private String stationName; // 역 이름
    private String fastExit;    // 빠른 환승 출구
    
    // 조인 시 필요한 추가 필드
    private List<Integer> lineNumbers; // 노선 번호들
    private List<String> lineNames;    // 노선 이름들
    private boolean isTransfer;        // 환승역 여부
    
    // 기존 코드와의 호환성을 위한 필드
    private int[] lineNo;
    
    public StationDTO() {}
    
    public StationDTO(String stationName, int stationNo, String fastExit) {
        this.stationName = stationName;
        this.stationNo = stationNo;
        this.fastExit = fastExit;
    }
    
    // Getters and Setters
    public int getStationId() {
        return stationId;
    }
    
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
    
    public String getStationName() {
        return stationName;
    }
    
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    
    public int getStationNo() {
        return stationNo;
    }
    
    public void setStationNo(int stationNo) {
        this.stationNo = stationNo;
    }
    
    public int[] getLineNo() {
        return lineNo;
    }
    
    public void setLineNo(int[] lineNo) {
        this.lineNo = lineNo;
    }
    
    public String getFastExit() {
        return fastExit;
    }
    
    public void setFastExit(String fastExit) {
        this.fastExit = fastExit;
    }
    
    public boolean isTransfer() {
        return isTransfer;
    }
    
    public void setTransfer(boolean transfer) {
        isTransfer = transfer;
    }
    
    public List<Integer> getLineNumbers() {
        return lineNumbers;
    }
    
    public void setLineNumbers(List<Integer> lineNumbers) {
        this.lineNumbers = lineNumbers;
        // 기존 코드와의 호환성을 위해 int[] 배열도 설정
        if (lineNumbers != null) {
            this.lineNo = lineNumbers.stream().mapToInt(Integer::intValue).toArray();
        }
    }
    
    public List<String> getLineNames() {
        return lineNames;
    }
    
    public void setLineNames(List<String> lineNames) {
        this.lineNames = lineNames;
    }
    
    @Override
    public String toString() {
        return "StationDTO{" +
                "stationId=" + stationId +
                ", stationNo=" + stationNo +
                ", stationName='" + stationName + '\'' +
                ", fastExit='" + fastExit + '\'' +
                ", lineNumbers=" + lineNumbers +
                ", lineNames=" + lineNames +
                ", isTransfer=" + isTransfer +
                ", lineNo=" + Arrays.toString(lineNo) +
                '}';
    }
}
