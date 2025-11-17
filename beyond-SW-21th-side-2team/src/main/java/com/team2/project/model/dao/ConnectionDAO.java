package com.team2.project.model.dao;

import com.team2.project.model.dto.ConnectionDTO;
import java.util.List;

/**
 * Connection 테이블 데이터 접근 인터페이스
 */
public interface ConnectionDAO {
    
    /**
     * 모든 연결 조회
     */
    List<ConnectionDTO> selectAllConnections();
    
    /**
     * 특정 역에서 출발하는 모든 연결 조회
     */
    List<ConnectionDTO> selectConnectionsByFromStation(int fromStationId);
    
    /**
     * 두 역 사이의 연결 조회
     */
    ConnectionDTO selectConnectionBetweenStations(int fromStationId, int toStationId);
    
    /**
     * 역 번호로 연결된 역들 조회 (BFS 알고리즘용)
     */
    List<Integer> selectConnectedStationIds(int stationId);
    
    /**
     * 연결 추가
     */
    int insertConnection(ConnectionDTO connection);
    
    /**
     * 연결 정보 수정
     */
    int updateConnection(ConnectionDTO connection);
    
    /**
     * 연결 삭제
     */
    int deleteConnection(int connectionId);
    
    /**
     * 양방향 연결 추가 (from->to, to->from 모두 추가)
     */
    int insertBidirectionalConnection(int fromStationId, int toStationId, int travelTime);
}
