# Subway Project Database Setup Guide with MyBatis

## Overview
This guide will help you add MySQL database integration to your subway project using MyBatis.

## Step 1: Database Schema Design

### Tables Structure

```sql
-- Create database
CREATE DATABASE IF NOT EXISTS subway_db;
USE subway_db;

-- Lines table (노선 정보)
CREATE TABLE lines (
    line_id INT PRIMARY KEY AUTO_INCREMENT,
    line_no INT NOT NULL UNIQUE,
    line_name VARCHAR(50) NOT NULL,
    line_color VARCHAR(20)
);

-- Stations table (역 정보)
CREATE TABLE stations (
    station_id INT PRIMARY KEY AUTO_INCREMENT,
    station_no INT NOT NULL UNIQUE,
    station_name VARCHAR(50) NOT NULL,
    fast_exit VARCHAR(10)
);

-- Station_Lines table (역과 노선의 관계 - 다대다)
CREATE TABLE station_lines (
    station_line_id INT PRIMARY KEY AUTO_INCREMENT,
    station_id INT NOT NULL,
    line_id INT NOT NULL,
    station_order INT NOT NULL, -- 해당 노선에서의 순서
    FOREIGN KEY (station_id) REFERENCES stations(station_id),
    FOREIGN KEY (line_id) REFERENCES lines(line_id)
);

-- Connections table (역 간 연결 정보)
CREATE TABLE connections (
    connection_id INT PRIMARY KEY AUTO_INCREMENT,
    from_station_id INT NOT NULL,
    to_station_id INT NOT NULL,
    travel_time INT DEFAULT 5, -- 기본 5분
    FOREIGN KEY (from_station_id) REFERENCES stations(station_id),
    FOREIGN KEY (to_station_id) REFERENCES stations(station_id)
);

-- Transfer_Stations table (환승역 정보)
CREATE TABLE transfer_stations (
    transfer_id INT PRIMARY KEY AUTO_INCREMENT,
    station_id INT NOT NULL,
    transfer_time INT DEFAULT 3, -- 환승 소요 시간 (분)
    FOREIGN KEY (station_id) REFERENCES stations(station_id)
);
```

### Sample Data Insertion

```sql
-- Insert Lines
INSERT INTO lines (line_no, line_name, line_color) VALUES
(1, '신분당선', 'RED'),
(2, '수인분당선', 'YELLOW'),
(3, '3호선', 'ORANGE');

-- Insert Stations (예시 - 모든 역 추가 필요)
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

-- Insert Station-Line relationships
INSERT INTO station_lines (station_id, line_id, station_order) VALUES
-- 신분당선
(1, 1, 1), (2, 1, 2), (3, 1, 3), (4, 1, 4), (5, 1, 5),
(6, 1, 6), (7, 1, 7), (8, 1, 8), (9, 1, 9), (10, 1, 10),
(11, 1, 11), (12, 1, 12), (13, 1, 13), (14, 1, 14), (15, 1, 15);

-- 미금과 정자는 수인분당선에도 속함
INSERT INTO station_lines (station_id, line_id, station_order) VALUES
(6, 2, 7), -- 미금
(7, 2, 8); -- 정자

-- Insert Transfer Stations
INSERT INTO transfer_stations (station_id, transfer_time) VALUES
(6, 3),  -- 미금
(7, 3),  -- 정자
(11, 3), -- 양재
(15, 3); -- 신사

-- Insert Connections (양방향으로 추가)
INSERT INTO connections (from_station_id, to_station_id, travel_time) VALUES
(1, 2, 5), (2, 1, 5),
(2, 3, 5), (3, 2, 5),
(3, 4, 5), (4, 3, 5);
-- ... (모든 연결 추가 필요)
```

## Step 2: Update build.gradle

Add MyBatis and MySQL dependencies:

```gradle
plugins {
    id 'java'
}

group = 'com.E'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    // MyBatis
    implementation 'org.mybatis:mybatis:3.5.13'
    
    // MySQL Connector
    implementation 'mysql:mysql-connector-java:8.0.33'
    
    // HikariCP (Connection Pool)
    implementation 'com.zaxxer:HikariCP:5.0.1'
    
    // Testing
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

test {
    useJUnitPlatform()
}
```

## Step 3: Project Structure

```
src/main/
├── java/com/team2/project/
│   ├── config/
│   │   └── MyBatisConfig.java
│   ├── controller/
│   │   └── SubwayManager.java
│   ├── model/
│   │   ├── dto/
│   │   │   ├── StationDTO.java
│   │   │   ├── LineDTO.java
│   │   │   ├── ConnectionDTO.java
│   │   │   └── TransferStationDTO.java
│   │   └── dao/
│   │       ├── StationDAO.java
│   │       ├── LineDAO.java
│   │       ├── ConnectionDAO.java
│   │       └── TransferStationDAO.java
│   ├── service/
│   │   └── SubwayService.java
│   ├── view/
│   └── run/
│       └── Application.java
└── resources/
    ├── config/
    │   └── database.properties
    └── mappers/
        ├── StationMapper.xml
        ├── LineMapper.xml
        ├── ConnectionMapper.xml
        └── TransferStationMapper.xml
```

## Step 4: Configuration Files

### database.properties
```properties
# MySQL Database Configuration
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/subway_db?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true
db.username=root
db.password=your_password_here
```

## Step 5: MyBatis Configuration

See the MyBatisConfig.java file for the configuration class.

## Step 6: DTO Classes

Your existing DTOs need minor updates, and you'll need additional ones.

## Step 7: DAO Interfaces

These define the methods for database operations.

## Step 8: MyBatis Mapper XML Files

These contain the actual SQL queries.

## Step 9: Service Layer

Business logic that uses DAOs.

## Step 10: Update Controller

Modify SubwayManager to use the database instead of hardcoded data.

## Benefits of This Approach

1. **Separation of Concerns**: Data is stored in the database, not hardcoded
2. **Scalability**: Easy to add new stations, lines, or connections
3. **Persistence**: Data survives application restarts
4. **Query Flexibility**: Complex queries through MyBatis
5. **Maintainability**: Changes to data don't require code changes

## Next Steps

1. Install MySQL and create the database
2. Update build.gradle and sync dependencies
3. Create all the Java files provided
4. Create mapper XML files
5. Update database.properties with your MySQL credentials
6. Test the application

## Common Issues and Solutions

### Connection Issues
- Check if MySQL is running: `sudo systemctl status mysql`
- Verify credentials in database.properties
- Ensure database exists: `mysql -u root -p` then `SHOW DATABASES;`

### MyBatis Mapping Issues
- Check that mapper XML namespace matches DAO interface fully qualified name
- Verify SQL syntax in mapper files
- Enable MyBatis logging for debugging

### Dependency Issues
- Run `./gradlew clean build` to refresh dependencies
- Check for version conflicts
