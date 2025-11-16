# 🚇 Subway Project - Database Integration Quick Reference

## 📦 Files Overview

### Configuration Files
- **database.properties** - MySQL connection settings
- **mybatis-config.xml** - MyBatis configuration
- **build.gradle** - Updated with MyBatis and MySQL dependencies

### Java Classes

#### Configuration
- **MyBatisConfig.java** - SqlSession factory configuration

#### DTO (Data Transfer Objects)
- **StationDTO_Updated.java** - Updated station data model
- **ConnectionDTO.java** - Connection between stations
- **TransferStationDTO.java** - Transfer station information
- **LineDTO.java** - Subway line information (existing, no changes needed)

#### DAO (Data Access Objects - Interfaces)
- **StationDAO.java** - Station database operations
- **LineDAO.java** - Line database operations
- **ConnectionDAO.java** - Connection database operations
- **TransferStationDAO.java** - Transfer station operations

#### MyBatis Mappers (XML)
- **StationMapper.xml** - SQL queries for stations
- **LineMapper.xml** - SQL queries for lines
- **ConnectionMapper.xml** - SQL queries for connections
- **TransferStationMapper.xml** - SQL queries for transfer stations

#### Service Layer
- **SubwayService.java** - Business logic using DAOs

#### Examples
- **DatabaseExampleUsage.java** - Usage examples

### Database
- **init_subway_database.sql** - Complete database initialization script

### Documentation
- **SETUP_INSTRUCTIONS.md** - Step-by-step setup guide
- **subway-database-setup-guide.md** - Detailed technical guide

---

## 🚀 Quick Start (5 Minutes)

### 1. Install MySQL
```bash
# Check if MySQL is installed
mysql --version
```

### 2. Create Database
```bash
mysql -u root -p < init_subway_database.sql
```

### 3. Configure Connection
Edit `src/main/resources/config/database.properties`:
```properties
db.password=YOUR_PASSWORD_HERE
```

### 4. Place Files

**Java files go to:**
```
src/main/java/com/team2/project/
  ├── config/MyBatisConfig.java
  ├── model/
  │   ├── dto/ (all DTO files)
  │   └── dao/ (all DAO files)
  ├── service/SubwayService.java
  └── run/DatabaseExampleUsage.java
```

**Resource files go to:**
```
src/main/resources/
  ├── config/database.properties
  ├── mappers/ (all XML files)
  └── mybatis-config.xml
```

### 5. Build & Run
```bash
./gradlew clean build
./gradlew run
```

---

## 💡 Common Usage Patterns

### Pattern 1: Query All Records
```java
SubwayService service = new SubwayService();
List<StationDTO> stations = service.getAllStations();
```

### Pattern 2: Query by ID/Name
```java
StationDTO station = service.getStationByName("강남");
```

### Pattern 3: Query with Filter
```java
List<StationDTO> stations = service.getStationsByLine(1);
```

### Pattern 4: Complex Query (BFS Algorithm)
```java
String path = service.findShortestPath("광교중앙", "강남");
```

### Pattern 5: Calculate/Aggregate
```java
int time = service.calculateTravelTime("미금", "양재");
```

---

## 🗄️ Database Schema

### Tables
1. **lines** - Subway lines (신분당선, 수인분당선, 3호선)
2. **stations** - All stations
3. **station_lines** - Many-to-many relationship between stations and lines
4. **connections** - Connections between adjacent stations
5. **transfer_stations** - Transfer station information

### Key Relationships
- Station ↔ Line: Many-to-Many (through station_lines)
- Station ↔ Connection: One-to-Many
- Station ↔ Transfer: One-to-One (optional)

---

## 🔧 Method Reference

### SubwayService Methods

| Method | Parameters | Returns | Description |
|--------|-----------|---------|-------------|
| `getAllStations()` | none | `List<StationDTO>` | Get all stations |
| `getStationsByLine(lineNo)` | `int` | `List<StationDTO>` | Get stations by line |
| `getTransferStations()` | none | `List<StationDTO>` | Get all transfer stations |
| `getStationByName(name)` | `String` | `StationDTO` | Find station by name |
| `findStationNo(name)` | `String` | `Integer` | Get station number |
| `findShortestPath(start, end)` | `String, String` | `String` | Find shortest path |
| `calculateTravelTime(start, end)` | `String, String` | `int` | Calculate travel time |
| `addStation(station)` | `StationDTO` | `boolean` | Add new station |
| `addBidirectionalConnection(...)` | `int, int, int` | `boolean` | Add connection |
| `getTransferStationInfo(name)` | `String` | `TransferStationDTO` | Get transfer info |

---

## 🎯 Integration Steps for Existing Code

### Step 1: Update SubwayManager
Replace hardcoded data with database calls:

```java
public class SubwayManager {
    private SubwayService service = new SubwayService();
    
    public void setUp() {
        // Remove hardcoded data initialization
        // Database is already populated
    }
    
    public int findStationNo(String name) {
        return service.findStationNo(name);
    }
    
    public String bfs(int start, int end) {
        // Use service.findShortestPath() instead
        StationDTO startStation = service.getStationByName(...);
        StationDTO endStation = service.getStationByName(...);
        return service.findShortestPath(
            startStation.getStationName(), 
            endStation.getStationName()
        );
    }
}
```

### Step 2: Update SubwayMenu
No major changes needed, just ensure SubwayManager uses the service.

### Step 3: Test
Run `DatabaseExampleUsage` to verify everything works.

---

## 🐛 Troubleshooting Quick Fixes

| Problem | Quick Fix |
|---------|-----------|
| Connection refused | Start MySQL: `sudo systemctl start mysql` |
| Access denied | Check password in `database.properties` |
| Database not found | Run: `mysql -u root -p < init_subway_database.sql` |
| Mapper not found | Check file paths in `mybatis-config.xml` |
| Class not found | Run: `./gradlew clean build` |

---

## 📊 Database Statistics

- **Total Stations:** 43
- **Lines:** 3
- **Transfer Stations:** 6
- **Connections:** 84 (42 bidirectional pairs)

---

## 🎓 Learning Objectives

By completing this integration, you'll learn:

✅ Database schema design and normalization  
✅ MyBatis configuration and usage  
✅ DAO pattern implementation  
✅ CRUD operations with MyBatis  
✅ Complex queries with joins  
✅ Transaction management  
✅ Connection pooling  
✅ Separation of concerns (DAO, Service, Controller)  

---

## 📝 Next Steps

1. ✅ Set up database (you're here!)
2. ⏳ Integrate with existing code
3. ⏳ Add CRUD operations UI
4. ⏳ Implement advanced features
5. ⏳ Add error handling
6. ⏳ Write unit tests
7. ⏳ Deploy application

---

## 🆘 Need Help?

1. Check **SETUP_INSTRUCTIONS.md** for detailed steps
2. Check **subway-database-setup-guide.md** for technical details
3. Run **DatabaseExampleUsage.java** to see working examples
4. Review MyBatis documentation: https://mybatis.org/mybatis-3/

Good luck with your project! 🚀
