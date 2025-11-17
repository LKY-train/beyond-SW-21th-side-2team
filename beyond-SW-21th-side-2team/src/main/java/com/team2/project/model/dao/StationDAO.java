package com.team2.project.model.dao;

import com.team2.project.model.dto.StationDTO;
import java.util.List;

/**
 * Station 테이블 데이터 접근 인터페이스
 */
public interface StationDAO {
    
    /**
     * 모든 역 조회
     */
    List<StationDTO> selectAllStations();
    
    /**
     * 역 ID로 역 조회
     */
    StationDTO selectStationById(int stationId);
    
    /**
     * 역 이름으로 역 조회
     */
    StationDTO selectStationByName(String stationName);
    
    /**
     * 역 번호로 역 조회
     */
    StationDTO selectStationByNo(int stationNo);
    
    /**
     * 특정 노선의 모든 역 조회
     */
    List<StationDTO> selectStationsByLineNo(int lineNo);
    
    /**
     * 환승역만 조회
     */
    List<StationDTO> selectTransferStations();
    
    /**
     * 역 추가
     */
    int insertStation(StationDTO station);
    
    /**
     * 역 정보 수정
     */
    int updateStation(StationDTO station);
    
    /**
     * 역 삭제
     */
    int deleteStation(int stationId);
    
    /**
     * 역 이름으로 역 번호 찾기
     */
    Integer findStationNoByName(String stationName);
}
