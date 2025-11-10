package com.team2.project.model.dto;

// StationDTO.java
public class StationDTO {
    private String stationName;
    private int stationNo;
    private int lineNo;

    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }

    public int getStationNo() { return stationNo; }
    public void setStationNo(int stationNo) { this.stationNo = stationNo; }

    public int getLineNo() { return lineNo; }
    public void setLineNo(int lineNo) { this.lineNo = lineNo; }

    @Override
    public String toString() {
        return "";
    }
}

