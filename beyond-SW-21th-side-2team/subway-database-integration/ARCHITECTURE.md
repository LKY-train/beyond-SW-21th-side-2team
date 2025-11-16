# 🏗️ Subway Project Architecture

## System Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         PRESENTATION LAYER                       │
│  ┌──────────────┐  ┌──────────────┐  ┌─────────────────────┐  │
│  │ SubwayMenu   │  │StationPrinter│  │   Application       │  │
│  │   (View)     │  │   (View)     │  │   (Main Entry)      │  │
│  └──────────────┘  └──────────────┘  └─────────────────────┘  │
└────────────┬────────────────────────────────────┬───────────────┘
             │                                    │
             ▼                                    ▼
┌─────────────────────────────────────────────────────────────────┐
│                       CONTROLLER LAYER                           │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │              SubwayManager                                │  │
│  │  - Handles user requests                                  │  │
│  │  - Delegates to Service layer                            │  │
│  └──────────────────────────────────────────────────────────┘  │
└────────────┬────────────────────────────────────────────────────┘
             │
             ▼
┌─────────────────────────────────────────────────────────────────┐
│                        SERVICE LAYER                             │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │              SubwayService                                │  │
│  │  - Business logic                                         │  │
│  │  - Transaction management                                 │  │
│  │  - Coordinates multiple DAOs                             │  │
│  │  ┌────────────────────────────────────────────────────┐  │  │
│  │  │ Methods:                                            │  │  │
│  │  │  • getAllStations()                                 │  │  │
│  │  │  • getStationsByLine(lineNo)                        │  │  │
│  │  │  • findShortestPath(start, end)                     │  │  │
│  │  │  • calculateTravelTime(start, end)                  │  │  │
│  │  │  • getTransferStations()                            │  │  │
│  │  └────────────────────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────────────────────┘  │
└────────────┬────────────────────────────────────────────────────┘
             │
             ▼
┌─────────────────────────────────────────────────────────────────┐
│                         DAO LAYER                                │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐ │
│  │ StationDAO   │  │   LineDAO    │  │  ConnectionDAO       │ │
│  │ (Interface)  │  │ (Interface)  │  │   (Interface)        │ │
│  └──────────────┘  └──────────────┘  └──────────────────────┘ │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │          TransferStationDAO (Interface)                  │  │
│  └──────────────────────────────────────────────────────────┘  │
└────────────┬────────────────────────────────────────────────────┘
             │
             ▼
┌─────────────────────────────────────────────────────────────────┐
│                      MYBATIS MAPPER LAYER                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐ │
│  │Station       │  │   Line       │  │  Connection          │ │
│  │Mapper.xml    │  │Mapper.xml    │  │  Mapper.xml          │ │
│  └──────────────┘  └──────────────┘  └──────────────────────┘ │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │          TransferStationMapper.xml                       │  │
│  └──────────────────────────────────────────────────────────┘  │
└────────────┬────────────────────────────────────────────────────┘
             │
             ▼
┌─────────────────────────────────────────────────────────────────┐
│                    CONFIGURATION LAYER                           │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │              MyBatisConfig                                │  │
│  │  - SqlSessionFactory                                      │  │
│  │  - Connection pooling                                     │  │
│  └──────────────────────────────────────────────────────────┘  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │            mybatis-config.xml                             │  │
│  │  - Database settings                                      │  │
│  │  - Mapper registrations                                   │  │
│  └──────────────────────────────────────────────────────────┘  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │            database.properties                            │  │
│  │  - DB URL, username, password                            │  │
│  └──────────────────────────────────────────────────────────┘  │
└────────────┬────────────────────────────────────────────────────┘
             │
             ▼
┌─────────────────────────────────────────────────────────────────┐
│                      DATABASE LAYER                              │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │                    MySQL Database                         │  │
│  │                      (subway_db)                          │  │
│  │  ┌────────────┐  ┌────────────┐  ┌──────────────────┐   │  │
│  │  │  stations  │  │   lines    │  │  station_lines   │   │  │
│  │  └────────────┘  └────────────┘  └──────────────────┘   │  │
│  │  ┌────────────┐  ┌──────────────────────────────────┐   │  │
│  │  │connections │  │  transfer_stations               │   │  │
│  │  └────────────┘  └──────────────────────────────────┘   │  │
│  └──────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

## Data Flow Example: Finding Shortest Path

```
1. USER INPUT
   ↓
   "광교중앙" → "강남"
   ↓
2. VIEW LAYER (SubwayMenu)
   ↓
   Calls: SubwayManager.bfs()
   ↓
3. CONTROLLER LAYER (SubwayManager)
   ↓
   Delegates to: SubwayService.findShortestPath()
   ↓
4. SERVICE LAYER (SubwayService)
   ↓
   • Calls: StationDAO.selectStationByName("광교중앙")
   • Calls: StationDAO.selectStationByName("강남")
   • Calls: ConnectionDAO.selectConnectedStationIds() (in BFS loop)
   ↓
5. DAO LAYER (StationDAO, ConnectionDAO)
   ↓
   Returns: Station and Connection objects
   ↓
6. MYBATIS MAPPER LAYER
   ↓
   Executes SQL:
   • SELECT * FROM stations WHERE station_name = ?
   • SELECT to_station_id FROM connections WHERE from_station_id = ?
   ↓
7. DATABASE LAYER (MySQL)
   ↓
   Returns: Result sets
   ↓
8. BACK UP THE CHAIN
   ↓
   SERVICE processes BFS algorithm
   ↓
   Returns path string: "광교중앙-상현-...-강남"
   ↓
9. VIEW LAYER
   ↓
   Displays: "광교중앙 → 상현 → ... → 강남"
```

## Database Schema Relationships

```
┌──────────────┐         ┌──────────────────┐         ┌──────────────┐
│    lines     │         │  station_lines   │         │   stations   │
│──────────────│         │──────────────────│         │──────────────│
│ line_id (PK) │◄────────│ line_id (FK)     │────────►│station_id(PK)│
│ line_no      │         │ station_id (FK)  │         │ station_no   │
│ line_name    │         │ station_order    │         │ station_name │
│ line_color   │         └──────────────────┘         │ fast_exit    │
└──────────────┘                                       └──────┬───────┘
                                                              │
                 ┌────────────────────────────────────────────┼────────┐
                 │                                            │        │
                 ▼                                            ▼        ▼
        ┌──────────────────┐                      ┌─────────────────────┐
        │   connections    │                      │ transfer_stations   │
        │──────────────────│                      │─────────────────────│
        │connection_id (PK)│                      │transfer_id (PK)     │
        │from_station_id   │◄─────────────────────│station_id (FK,UNIQ) │
        │to_station_id     │                      │transfer_time        │
        │travel_time       │                      └─────────────────────┘
        └──────────────────┘
```

## Key Design Patterns Used

### 1. DAO Pattern (Data Access Object)
```
Purpose: Separate data access logic from business logic
Benefits:
  • Easy to switch databases
  • Testable without database
  • Single responsibility principle
```

### 2. Service Layer Pattern
```
Purpose: Encapsulate business logic
Benefits:
  • Transaction management
  • Coordinate multiple DAOs
  • Reusable business logic
```

### 3. Singleton Pattern (MyBatisConfig)
```
Purpose: Single SqlSessionFactory instance
Benefits:
  • Resource efficiency
  • Thread-safe
  • Consistent configuration
```

### 4. Factory Pattern (SqlSessionFactory)
```
Purpose: Create SqlSession objects
Benefits:
  • Encapsulate object creation
  • Connection pooling
  • Lifecycle management
```

## Transaction Flow

```
┌─────────────────────────────────────────────────────┐
│  SubwayService.addStation(station)                  │
├─────────────────────────────────────────────────────┤
│  1. SqlSession session = getSqlSession(false)       │ ← Manual commit
│  2. try {                                           │
│       StationDAO dao = session.getMapper()          │
│       dao.insertStation(station)                    │
│       session.commit() ─────────────────────────────┼─► Database commit
│  3. } catch (Exception e) {                         │
│       session.rollback() ───────────────────────────┼─► Database rollback
│  4. } finally {                                     │
│       session.close()                               │
│     }                                               │
└─────────────────────────────────────────────────────┘
```

## MyBatis Query Mapping Flow

```
Java Interface Method
    ↓
StationDAO.selectAllStations()
    ↓
MyBatis looks up mapper
    ↓
StationMapper.xml
    ↓
<select id="selectAllStations" ...>
    SELECT * FROM stations
</select>
    ↓
Execute SQL on database
    ↓
Map ResultSet to StationDTO
    ↓
Return List<StationDTO>
```

## Configuration Loading Order

```
1. Application starts
   ↓
2. MyBatisConfig static block executes
   ↓
3. Load mybatis-config.xml
   ↓
4. Load database.properties
   ↓
5. Parse <settings>, <typeAliases>
   ↓
6. Configure DataSource (connection pool)
   ↓
7. Register Mapper XML files
   ↓
8. Build SqlSessionFactory
   ↓
9. Ready to serve requests
```

## File Organization by Layer

```
Presentation Layer:
  └── view/
      ├── SubwayMenu.java
      └── StationByLinePrinter.java

Controller Layer:
  └── controller/
      └── SubwayManager.java

Service Layer:
  └── service/
      └── SubwayService.java

DAO Layer:
  └── model/dao/
      ├── StationDAO.java
      ├── LineDAO.java
      ├── ConnectionDAO.java
      └── TransferStationDAO.java

Model/DTO Layer:
  └── model/dto/
      ├── StationDTO.java
      ├── LineDTO.java
      ├── ConnectionDTO.java
      └── TransferStationDTO.java

Configuration Layer:
  ├── config/
  │   └── MyBatisConfig.java
  └── resources/
      ├── mybatis-config.xml
      ├── config/database.properties
      └── mappers/
          ├── StationMapper.xml
          ├── LineMapper.xml
          ├── ConnectionMapper.xml
          └── TransferStationMapper.xml

Entry Point:
  └── run/
      ├── Application.java
      └── DatabaseExampleUsage.java
```

This architecture follows clean code principles and makes your application:
- ✅ Maintainable
- ✅ Testable
- ✅ Scalable
- ✅ Easy to understand
