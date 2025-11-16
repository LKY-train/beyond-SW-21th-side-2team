package com.team2.project.model.dao;

import com.team2.project.model.dto.LineDTO;
import java.util.List;

/**
 * Line 테이블 데이터 접근 인터페이스
 */
public interface LineDAO {
    
    /**
     * 모든 노선 조회
     */
    List<LineDTO> selectAllLines();
    
    /**
     * 노선 ID로 노선 조회
     */
    LineDTO selectLineById(int lineId);
    
    /**
     * 노선 번호로 노선 조회
     */
    LineDTO selectLineByNo(int lineNo);
    
    /**
     * 노선 추가
     */
    int insertLine(LineDTO line);
    
    /**
     * 노선 정보 수정
     */
    int updateLine(LineDTO line);
    
    /**
     * 노선 삭제
     */
    int deleteLine(int lineId);
}
