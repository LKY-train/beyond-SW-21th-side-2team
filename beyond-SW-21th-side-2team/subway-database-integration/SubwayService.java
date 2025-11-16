package com.team2.project.service;

import com.team2.project.config.MyBatisConfig;
import com.team2.project.model.dao.ConnectionDAO;
import com.team2.project.model.dao.StationDAO;
import com.team2.project.model.dao.TransferStationDAO;
import com.team2.project.model.dto.ConnectionDTO;
import com.team2.project.model.dto.StationDTO;
import com.team2.project.model.dto.TransferStationDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.*;

/**
 * 지하철 비즈니스 로직을 처리하는 서비스 클래스
 */
public class SubwayService {

    /**
     * 모든 역 조회
     */
    public List<StationDTO> getAllStations() {
        SqlSession session = MyBatisConfig.getSqlSession();
        try {
            StationDAO stationDAO = session.getMapper(StationDAO.class);
            return stationDAO.selectAllStations();
        } finally {
            session.close();
        }
    }

    /**
     * 특정 노선의 모든 역 조회
     */
    public List<StationDTO> getStationsByLine(int lineNo) {
        SqlSession session = MyBatisConfig.getSqlSession();
        try {
            StationDAO stationDAO = session.getMapper(StationDAO.class);
            return stationDAO.selectStationsByLineNo(lineNo);
        } finally {
            session.close();
        }
    }

    /**
     * 환승역 목록 조회
     */
    public List<StationDTO> getTransferStations() {
        SqlSession session = MyBatisConfig.getSqlSession();
        try {
            StationDAO stationDAO = session.getMapper(StationDAO.class);
            return stationDAO.selectTransferStations();
        } finally {
            session.close();
        }
    }

    /**
     * 역 이름으로 역 정보 조회
     */
    public StationDTO getStationByName(String stationName) {
        SqlSession session = MyBatisConfig.getSqlSession();
        try {
            StationDAO stationDAO = session.getMapper(StationDAO.class);
            return stationDAO.selectStationByName(stationName);
        } finally {
            session.close();
        }
    }

    /**
     * 역 이름으로 역 번호 찾기
     */
    public Integer findStationNo(String stationName) {
        SqlSession session = MyBatisConfig.getSqlSession();
        try {
            StationDAO stationDAO = session.getMapper(StationDAO.class);
            return stationDAO.findStationNoByName(stationName);
        } finally {
            session.close();
        }
    }

    /**
     * BFS 알고리즘으로 최단 경로 찾기 (데이터베이스 버전)
     */
    public String findShortestPath(String startName, String endName) {
        SqlSession session = MyBatisConfig.getSqlSession();
        try {
            StationDAO stationDAO = session.getMapper(StationDAO.class);
            ConnectionDAO connectionDAO = session.getMapper(ConnectionDAO.class);

            // 역 이름으로 역 정보 조회
            StationDTO startStation = stationDAO.selectStationByName(startName);
            StationDTO endStation = stationDAO.selectStationByName(endName);

            if (startStation == null || endStation == null) {
                return null;
            }

            return bfs(startStation.getStationId(), endStation.getStationId(), 
                      connectionDAO, stationDAO);
        } finally {
            session.close();
        }
    }

    /**
     * BFS 알고리즘 구현 (데이터베이스에서 연결 정보를 가져옴)
     */
    private String bfs(int startId, int endId, ConnectionDAO connectionDAO, StationDAO stationDAO) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        queue.offer(startId);
        visited.add(startId);
        parent.put(startId, startId);

        while (!queue.isEmpty()) {
            int currentId = queue.poll();

            if (currentId == endId) {
                break;
            }

            // 데이터베이스에서 연결된 역들을 조회
            List<Integer> connectedStations = connectionDAO.selectConnectedStationIds(currentId);

            for (int nextId : connectedStations) {
                if (!visited.contains(nextId)) {
                    visited.add(nextId);
                    parent.put(nextId, currentId);
                    queue.offer(nextId);
                }
            }
        }

        // 경로 역추적
        StringBuilder path = new StringBuilder();
        int currentId = endId;
        List<String> stationNames = new ArrayList<>();

        while (currentId != startId) {
            StationDTO station = stationDAO.selectStationById(currentId);
            stationNames.add(station.getStationName());
            currentId = parent.get(currentId);
        }

        StationDTO startStation = stationDAO.selectStationById(startId);
        stationNames.add(startStation.getStationName());

        // 역순으로 경로 구성
        for (int i = stationNames.size() - 1; i >= 0; i--) {
            path.append(stationNames.get(i));
            if (i > 0) {
                path.append("-");
            }
        }

        return path.toString();
    }

    /**
     * 소요 시간 계산
     */
    public int calculateTravelTime(String startName, String endName) {
        String path = findShortestPath(startName, endName);
        if (path == null) {
            return -1;
        }

        String[] stations = path.split("-");
        // 각 역 사이는 5분으로 계산
        return (stations.length - 1) * 5;
    }

    /**
     * 역 추가 (트랜잭션 예제)
     */
    public boolean addStation(StationDTO station) {
        SqlSession session = MyBatisConfig.getSqlSession(false); // 수동 커밋
        try {
            StationDAO stationDAO = session.getMapper(StationDAO.class);
            int result = stationDAO.insertStation(station);
            
            if (result > 0) {
                session.commit();
                return true;
            } else {
                session.rollback();
                return false;
            }
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * 연결 추가 (양방향)
     */
    public boolean addBidirectionalConnection(int fromStationId, int toStationId, int travelTime) {
        SqlSession session = MyBatisConfig.getSqlSession(false);
        try {
            ConnectionDAO connectionDAO = session.getMapper(ConnectionDAO.class);
            int result = connectionDAO.insertBidirectionalConnection(fromStationId, toStationId, travelTime);
            
            if (result > 0) {
                session.commit();
                return true;
            } else {
                session.rollback();
                return false;
            }
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * 환승역 정보 조회
     */
    public TransferStationDTO getTransferStationInfo(String stationName) {
        SqlSession session = MyBatisConfig.getSqlSession();
        try {
            StationDAO stationDAO = session.getMapper(StationDAO.class);
            TransferStationDAO transferDAO = session.getMapper(TransferStationDAO.class);

            StationDTO station = stationDAO.selectStationByName(stationName);
            if (station == null) {
                return null;
            }

            return transferDAO.selectTransferStationByStationId(station.getStationId());
        } finally {
            session.close();
        }
    }
}
