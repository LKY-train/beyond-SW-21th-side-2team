-- ====================================
-- 지하철 데이터베이스 초기화 스크립트
-- ====================================

-- 데이터베이스 생성
DROP DATABASE IF EXISTS subway_db;
CREATE DATABASE subway_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE subway_db;

-- ====================================
-- 테이블 생성
-- ====================================

-- 1. 노선 테이블
CREATE TABLE lines (
    line_id INT PRIMARY KEY AUTO_INCREMENT,
    line_no INT NOT NULL UNIQUE COMMENT '노선 번호',
    line_name VARCHAR(50) NOT NULL COMMENT '노선 이름',
    line_color VARCHAR(20) COMMENT '노선 색상'
) COMMENT='지하철 노선 정보';

-- 2. 역 테이블
CREATE TABLE stations (
    station_id INT PRIMARY KEY AUTO_INCREMENT,
    station_no INT NOT NULL UNIQUE COMMENT '역 고유 번호',
    station_name VARCHAR(50) NOT NULL COMMENT '역 이름',
    fast_exit VARCHAR(10) COMMENT '빠른 환승 출구'
) COMMENT='지하철 역 정보';

-- 3. 역-노선 관계 테이블 (다대다 관계)
CREATE TABLE station_lines (
    station_line_id INT PRIMARY KEY AUTO_INCREMENT,
    station_id INT NOT NULL,
    line_id INT NOT NULL,
    station_order INT NOT NULL COMMENT '해당 노선에서의 순서',
    FOREIGN KEY (station_id) REFERENCES stations(station_id) ON DELETE CASCADE,
    FOREIGN KEY (line_id) REFERENCES lines(line_id) ON DELETE CASCADE,
    UNIQUE KEY unique_station_line (station_id, line_id)
) COMMENT='역과 노선의 관계';

-- 4. 역 간 연결 테이블
CREATE TABLE connections (
    connection_id INT PRIMARY KEY AUTO_INCREMENT,
    from_station_id INT NOT NULL,
    to_station_id INT NOT NULL,
    travel_time INT DEFAULT 5 COMMENT '이동 시간(분)',
    FOREIGN KEY (from_station_id) REFERENCES stations(station_id) ON DELETE CASCADE,
    FOREIGN KEY (to_station_id) REFERENCES stations(station_id) ON DELETE CASCADE,
    UNIQUE KEY unique_connection (from_station_id, to_station_id)
) COMMENT='역 간 연결 정보';

-- 5. 환승역 테이블
CREATE TABLE transfer_stations (
    transfer_id INT PRIMARY KEY AUTO_INCREMENT,
    station_id INT NOT NULL UNIQUE,
    transfer_time INT DEFAULT 3 COMMENT '환승 소요 시간(분)',
    FOREIGN KEY (station_id) REFERENCES stations(station_id) ON DELETE CASCADE
) COMMENT='환승역 정보';

-- ====================================
-- 노선 데이터 삽입
-- ====================================

INSERT INTO lines (line_no, line_name, line_color) VALUES
(1, '신분당선', 'RED'),
(2, '수인분당선', 'YELLOW'),
(3, '3호선', 'ORANGE');

-- ====================================
-- 역 데이터 삽입
-- ====================================

-- 신분당선 역들
INSERT INTO stations (station_no, station_name, fast_exit) VALUES
(1, '광교중앙', '2-1'),
(2, '상현', '2-1'),
(3, '성복', '2-1'),
(4, '수지구청', '2-1'),
(5, '동천', '2-1'),
(6, '미금', '2-1'),
(7, '정자', '2-1'),
(8, '판교', '2-1'),
(9, '청계산입구', '2-1'),
(10, '양재시민의숲', '2-1'),
(11, '양재', '2-1'),
(12, '강남', '2-1'),
(13, '신논현', '2-1'),
(14, '논현', '2-1'),
(15, '신사', '2-1');

-- 수인분당선 역들 (미금, 정자 제외)
INSERT INTO stations (station_no, station_name, fast_exit) VALUES
(16, '기흥', '4-1'),
(17, '신갈', '4-1'),
(18, '구성', '4-1'),
(19, '보정', '4-1'),
(20, '죽전', '4-1'),
(21, '오리', '4-1'),
-- (6, '미금', '2-1'),  -- 이미 신분당선에 있음
-- (7, '정자', '2-1'),  -- 이미 신분당선에 있음
(22, '수내', '4-1'),
(23, '서현', '4-1'),
(24, '이매', '4-1'),
(25, '야탑', '4-1'),
(26, '모란', '4-1'),
(27, '태평', '4-1'),
(28, '가천대', '4-1'),
(29, '복정', '4-1'),
(30, '수서', '4-1'),
(31, '대모산입구', '4-1'),
(32, '개포동', '4-1'),
(33, '구룡', '4-1'),
(34, '도곡', '4-1');

-- 3호선 역들 (도곡, 수서, 양재, 신사 제외)
INSERT INTO stations (station_no, station_name, fast_exit) VALUES
-- (30, '수서', '4-1'),  -- 이미 수인분당선에 있음
(35, '일원', '6-1'),
(36, '대청', '6-1'),
(37, '학여울', '6-1'),
(38, '대치', '6-1'),
-- (34, '도곡', '4-1'),  -- 이미 수인분당선에 있음
(39, '매봉', '6-1'),
-- (11, '양재', '2-1'),  -- 이미 신분당선에 있음
(40, '남부터미널', '6-1'),
(41, '교대', '6-1'),
(42, '고속터미널', '6-1'),
(43, '잠원', '6-1');
-- (15, '신사', '2-1')   -- 이미 신분당선에 있음

-- ====================================
-- 역-노선 관계 데이터 삽입
-- ====================================

-- 신분당선
INSERT INTO station_lines (station_id, line_id, station_order) VALUES
(1, 1, 1), (2, 1, 2), (3, 1, 3), (4, 1, 4), (5, 1, 5),
(6, 1, 6), (7, 1, 7), (8, 1, 8), (9, 1, 9), (10, 1, 10),
(11, 1, 11), (12, 1, 12), (13, 1, 13), (14, 1, 14), (15, 1, 15);

-- 수인분당선
INSERT INTO station_lines (station_id, line_id, station_order) VALUES
(16, 2, 1), (17, 2, 2), (18, 2, 3), (19, 2, 4), (20, 2, 5),
(21, 2, 6), (6, 2, 7), (7, 2, 8), (22, 2, 9), (23, 2, 10),
(24, 2, 11), (25, 2, 12), (26, 2, 13), (27, 2, 14), (28, 2, 15),
(29, 2, 16), (30, 2, 17), (31, 2, 18), (32, 2, 19), (33, 2, 20), (34, 2, 21);

-- 3호선
INSERT INTO station_lines (station_id, line_id, station_order) VALUES
(30, 3, 1), (35, 3, 2), (36, 3, 3), (37, 3, 4), (38, 3, 5),
(34, 3, 6), (39, 3, 7), (11, 3, 8), (40, 3, 9), (41, 3, 10),
(42, 3, 11), (43, 3, 12), (15, 3, 13);

-- ====================================
-- 환승역 데이터 삽입
-- ====================================

INSERT INTO transfer_stations (station_id, transfer_time) VALUES
(6, 3),   -- 미금 (신분당선 ↔ 수인분당선)
(7, 3),   -- 정자 (신분당선 ↔ 수인분당선)
(11, 3),  -- 양재 (신분당선 ↔ 3호선)
(15, 3),  -- 신사 (신분당선 ↔ 3호선)
(30, 3),  -- 수서 (수인분당선 ↔ 3호선)
(34, 3);  -- 도곡 (수인분당선 ↔ 3호선)

-- ====================================
-- 연결 데이터 삽입 (양방향)
-- ====================================

-- 신분당선 연결
INSERT INTO connections (from_station_id, to_station_id, travel_time) VALUES
-- 광교중앙 ↔ 상현
(1, 2, 5), (2, 1, 5),
-- 상현 ↔ 성복
(2, 3, 5), (3, 2, 5),
-- 성복 ↔ 수지구청
(3, 4, 5), (4, 3, 5),
-- 수지구청 ↔ 동천
(4, 5, 5), (5, 4, 5),
-- 동천 ↔ 미금
(5, 6, 5), (6, 5, 5),
-- 미금 ↔ 정자
(6, 7, 5), (7, 6, 5),
-- 정자 ↔ 판교
(7, 8, 5), (8, 7, 5),
-- 판교 ↔ 청계산입구
(8, 9, 5), (9, 8, 5),
-- 청계산입구 ↔ 양재시민의숲
(9, 10, 5), (10, 9, 5),
-- 양재시민의숲 ↔ 양재
(10, 11, 5), (11, 10, 5),
-- 양재 ↔ 강남
(11, 12, 5), (12, 11, 5),
-- 강남 ↔ 신논현
(12, 13, 5), (13, 12, 5),
-- 신논현 ↔ 논현
(13, 14, 5), (14, 13, 5),
-- 논현 ↔ 신사
(14, 15, 5), (15, 14, 5);

-- 수인분당선 연결
INSERT INTO connections (from_station_id, to_station_id, travel_time) VALUES
-- 기흥 ↔ 신갈
(16, 17, 5), (17, 16, 5),
-- 신갈 ↔ 구성
(17, 18, 5), (18, 17, 5),
-- 구성 ↔ 보정
(18, 19, 5), (19, 18, 5),
-- 보정 ↔ 죽전
(19, 20, 5), (20, 19, 5),
-- 죽전 ↔ 오리
(20, 21, 5), (21, 20, 5),
-- 오리 ↔ 미금
(21, 6, 5), (6, 21, 5),
-- 정자 ↔ 수내
(7, 22, 5), (22, 7, 5),
-- 수내 ↔ 서현
(22, 23, 5), (23, 22, 5),
-- 서현 ↔ 이매
(23, 24, 5), (24, 23, 5),
-- 이매 ↔ 야탑
(24, 25, 5), (25, 24, 5),
-- 야탑 ↔ 모란
(25, 26, 5), (26, 25, 5),
-- 모란 ↔ 태평
(26, 27, 5), (27, 26, 5),
-- 태평 ↔ 가천대
(27, 28, 5), (28, 27, 5),
-- 가천대 ↔ 복정
(28, 29, 5), (29, 28, 5),
-- 복정 ↔ 수서
(29, 30, 5), (30, 29, 5),
-- 수서 ↔ 대모산입구
(30, 31, 5), (31, 30, 5),
-- 대모산입구 ↔ 개포동
(31, 32, 5), (32, 31, 5),
-- 개포동 ↔ 구룡
(32, 33, 5), (33, 32, 5),
-- 구룡 ↔ 도곡
(33, 34, 5), (34, 33, 5);

-- 3호선 연결
INSERT INTO connections (from_station_id, to_station_id, travel_time) VALUES
-- 수서 ↔ 일원
(30, 35, 5), (35, 30, 5),
-- 일원 ↔ 대청
(35, 36, 5), (36, 35, 5),
-- 대청 ↔ 학여울
(36, 37, 5), (37, 36, 5),
-- 학여울 ↔ 대치
(37, 38, 5), (38, 37, 5),
-- 대치 ↔ 도곡
(38, 34, 5), (34, 38, 5),
-- 도곡 ↔ 매봉
(34, 39, 5), (39, 34, 5),
-- 매봉 ↔ 양재
(39, 11, 5), (11, 39, 5),
-- 양재 ↔ 남부터미널
(11, 40, 5), (40, 11, 5),
-- 남부터미널 ↔ 교대
(40, 41, 5), (41, 40, 5),
-- 교대 ↔ 고속터미널
(41, 42, 5), (42, 41, 5),
-- 고속터미널 ↔ 잠원
(42, 43, 5), (43, 42, 5),
-- 잠원 ↔ 신사
(43, 15, 5), (15, 43, 5);

-- ====================================
-- 데이터 확인 쿼리
-- ====================================

-- 전체 역 개수 확인
SELECT '총 역 개수' as description, COUNT(*) as count FROM stations;

-- 노선별 역 개수
SELECT l.line_name, COUNT(sl.station_id) as station_count
FROM lines l
LEFT JOIN station_lines sl ON l.line_id = sl.line_id
GROUP BY l.line_id, l.line_name
ORDER BY l.line_no;

-- 환승역 목록
SELECT s.station_name, COUNT(sl.line_id) as line_count
FROM stations s
INNER JOIN station_lines sl ON s.station_id = sl.station_id
GROUP BY s.station_id, s.station_name
HAVING COUNT(sl.line_id) > 1
ORDER BY s.station_no;

-- 연결 개수 확인
SELECT '총 연결 개수' as description, COUNT(*) as count FROM connections;

SELECT '데이터베이스 초기화 완료!' as status;
