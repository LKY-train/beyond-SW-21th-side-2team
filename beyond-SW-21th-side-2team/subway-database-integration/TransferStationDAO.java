package com.team2.project.model.dao;

import com.team2.project.model.dto.TransferStationDTO;
import java.util.List;

/**
 * TransferStation 테이블 데이터 접근 인터페이스
 */
public interface TransferStationDAO {
    
    /**
     * 모든 환승역 조회
     */
    List<TransferStationDTO> selectAllTransferStations();
    
    /**
     * 환승역 ID로 환승역 조회
     */
    TransferStationDTO selectTransferStationById(int transferId);
    
    /**
     * 역 ID로 환승 정보 조회
     */
    TransferStationDTO selectTransferStationByStationId(int stationId);
    
    /**
     * 역이 환승역인지 확인
     */
    boolean isTransferStation(int stationId);
    
    /**
     * 환승역 추가
     */
    int insertTransferStation(TransferStationDTO transferStation);
    
    /**
     * 환승역 정보 수정
     */
    int updateTransferStation(TransferStationDTO transferStation);
    
    /**
     * 환승역 삭제
     */
    int deleteTransferStation(int transferId);
}
