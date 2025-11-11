package com.team2.project.model.dto;

import java.util.Arrays;

// StationDTO.java
public class StationDTO {
    private String stationName;
    private int stationNo;
    private int[] lineNo;
    private String fastExit;
    private boolean isTransfer = false;

    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }

    public int getStationNo() { return stationNo; }
    public void setStationNo(int stationNo) { this.stationNo = stationNo; }

    public int[] getLineNo() {return lineNo;}
    public void setLineNo(int[] lineNo) {this.lineNo = lineNo;}

    public String getFastExit() {return fastExit;}
    public void setFastExit(String fastExit) {this.fastExit = fastExit;}

    public boolean isTransfer() {return isTransfer;}
    public void setTransfer(boolean transfer) {isTransfer = transfer;}

    @Override
    public String toString() {
        return "StationDTO{" +
                "stationName='" + stationName + '\'' +
                ", stationNo=" + stationNo +
                ", lineNo=" + Arrays.toString(lineNo) +
                ", fastExit='" + fastExit + '\'' +
                ", isTransfer=" + isTransfer +
                '}';
    }
}

