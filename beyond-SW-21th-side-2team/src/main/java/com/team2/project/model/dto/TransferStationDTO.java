package com.team2.project.model.dto;

import java.util.List;

/**
 * 환승역 정보 DTO
 */
public class TransferStationDTO {
    private int transferId;
    private int stationId;
    private int transferTime;
    
    // 조인 시 필요한 추가 필드
    private String stationName;
    private List<Integer> lineNumbers; // 환승 가능한 노선 번호들
    private List<String> lineNames;    // 환승 가능한 노선 이름들
    
    public TransferStationDTO() {}
    
    public TransferStationDTO(int stationId, int transferTime) {
        this.stationId = stationId;
        this.transferTime = transferTime;
    }
    
    // Getters and Setters
    public int getTransferId() {
        return transferId;
    }
    
    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }
    
    public int getStationId() {
        return stationId;
    }
    
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
    
    public int getTransferTime() {
        return transferTime;
    }
    
    public void setTransferTime(int transferTime) {
        this.transferTime = transferTime;
    }
    
    public String getStationName() {
        return stationName;
    }
    
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    
    public List<Integer> getLineNumbers() {
        return lineNumbers;
    }
    
    public void setLineNumbers(List<Integer> lineNumbers) {
        this.lineNumbers = lineNumbers;
    }
    
    public List<String> getLineNames() {
        return lineNames;
    }
    
    public void setLineNames(List<String> lineNames) {
        this.lineNames = lineNames;
    }
    
    @Override
    public String toString() {
        return "TransferStationDTO{" +
                "transferId=" + transferId +
                ", stationId=" + stationId +
                ", transferTime=" + transferTime +
                ", stationName='" + stationName + '\'' +
                ", lineNumbers=" + lineNumbers +
                ", lineNames=" + lineNames +
                '}';
    }
}
