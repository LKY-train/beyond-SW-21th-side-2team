package com.team2.project.model.dto;

/**
 * 연결 정보 DTO (역과 역 사이의 연결)
 */
public class ConnectionDTO {
    private int connectionId;
    private int fromStationId;
    private int toStationId;
    private int travelTime;
    
    // 조인 시 필요한 추가 필드
    private String fromStationName;
    private String toStationName;
    
    public ConnectionDTO() {}
    
    public ConnectionDTO(int fromStationId, int toStationId, int travelTime) {
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.travelTime = travelTime;
    }
    
    // Getters and Setters
    public int getConnectionId() {
        return connectionId;
    }
    
    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }
    
    public int getFromStationId() {
        return fromStationId;
    }
    
    public void setFromStationId(int fromStationId) {
        this.fromStationId = fromStationId;
    }
    
    public int getToStationId() {
        return toStationId;
    }
    
    public void setToStationId(int toStationId) {
        this.toStationId = toStationId;
    }
    
    public int getTravelTime() {
        return travelTime;
    }
    
    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }
    
    public String getFromStationName() {
        return fromStationName;
    }
    
    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }
    
    public String getToStationName() {
        return toStationName;
    }
    
    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }
    
    @Override
    public String toString() {
        return "ConnectionDTO{" +
                "connectionId=" + connectionId +
                ", fromStationId=" + fromStationId +
                ", toStationId=" + toStationId +
                ", travelTime=" + travelTime +
                ", fromStationName='" + fromStationName + '\'' +
                ", toStationName='" + toStationName + '\'' +
                '}';
    }
}
