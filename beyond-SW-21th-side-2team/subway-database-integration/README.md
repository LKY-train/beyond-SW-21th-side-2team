# 🚇 Subway Project - Database Integration Package

Welcome! This package contains everything you need to integrate a MySQL database with MyBatis into your subway project.

## 📦 What's Included

### 📖 Documentation (Start Here!)
1. **README.md** (this file) - Overview
2. **QUICK_REFERENCE.md** - Fast lookup guide
3. **SETUP_INSTRUCTIONS.md** - Detailed step-by-step setup
4. **subway-database-setup-guide.md** - Technical documentation
5. **ARCHITECTURE.md** - System architecture diagrams

### ☕ Java Source Files

#### Configuration
- `MyBatisConfig.java` - MyBatis configuration class

#### Data Access Layer (DAO)
- `StationDAO.java` - Station database operations interface
- `LineDAO.java` - Line database operations interface
- `ConnectionDAO.java` - Connection database operations interface
- `TransferStationDAO.java` - Transfer station operations interface

#### Data Transfer Objects (DTO)
- `StationDTO_Updated.java` - Enhanced station model
- `ConnectionDTO.java` - Connection data model
- `TransferStationDTO.java` - Transfer station data model

#### Service Layer
- `SubwayService.java` - Business logic implementation

#### Examples
- `DatabaseExampleUsage.java` - Working code examples

### 🗄️ Database Files
- `init_subway_database.sql` - Complete database setup script

### ⚙️ Configuration Files
- `mybatis-config.xml` - MyBatis configuration
- `database.properties` - Database connection settings
- `build.gradle` - Updated Gradle dependencies

### 🗺️ MyBatis Mapper Files (XML)
- `StationMapper.xml` - Station SQL queries
- `LineMapper.xml` - Line SQL queries
- `ConnectionMapper.xml` - Connection SQL queries
- `TransferStationMapper.xml` - Transfer station SQL queries

## 🚀 Quick Start (3 Steps)

### Step 1: Set Up Database
```bash
mysql -u root -p < init_subway_database.sql
```

### Step 2: Configure Connection
Edit `database.properties` and set your MySQL password:
```properties
db.password=YOUR_PASSWORD_HERE
```

### Step 3: Place Files in Project
```
src/
├── main/
│   ├── java/com/team2/project/
│   │   ├── config/MyBatisConfig.java
│   │   ├── model/
│   │   │   ├── dto/*.java
│   │   │   └── dao/*.java
│   │   ├── service/SubwayService.java
│   │   └── run/DatabaseExampleUsage.java
│   └── resources/
│       ├── config/database.properties
│       ├── mappers/*.xml
│       └── mybatis-config.xml
└── build.gradle (replace existing)
```

Then run:
```bash
./gradlew clean build
```

## 📚 Documentation Guide

**New to this project?** Read in this order:
1. This README (you are here!)
2. QUICK_REFERENCE.md
3. SETUP_INSTRUCTIONS.md

**Want to understand the architecture?**
- Read ARCHITECTURE.md

**Need detailed technical info?**
- Read subway-database-setup-guide.md

**Having issues?**
- Check "Troubleshooting" section in SETUP_INSTRUCTIONS.md

## 💡 What You'll Learn

✅ Database design and normalization  
✅ MyBatis ORM framework  
✅ DAO pattern implementation  
✅ Service layer architecture  
✅ SQL query optimization  
✅ Transaction management  
✅ JDBC and connection pooling  

## 🎯 Key Features

- **Complete Database Schema** - 5 tables with proper relationships
- **43 Stations** - All stations from 3 subway lines
- **MyBatis Integration** - Professional ORM setup
- **Service Layer** - Clean separation of concerns
- **Working Examples** - Ready-to-run code samples
- **BFS Algorithm** - Database-backed shortest path finding
- **Transaction Support** - ACID compliance

## 🗄️ Database Overview

### Tables
- `lines` - Subway lines (3 lines)
- `stations` - All stations (43 stations)
- `station_lines` - Many-to-many relationships
- `connections` - Station connections (84 connections)
- `transfer_stations` - Transfer points (6 stations)

### Sample Queries
```sql
-- Get all stations
SELECT * FROM stations;

-- Get stations by line
SELECT s.* FROM stations s
JOIN station_lines sl ON s.station_id = sl.station_id
JOIN lines l ON sl.line_id = l.line_id
WHERE l.line_no = 1;

-- Get transfer stations
SELECT s.* FROM stations s
JOIN transfer_stations ts ON s.station_id = ts.station_id;
```

## 💻 Code Examples

### Example 1: Get All Stations
```java
SubwayService service = new SubwayService();
List<StationDTO> stations = service.getAllStations();
for (StationDTO station : stations) {
    System.out.println(station.getStationName());
}
```

### Example 2: Find Shortest Path
```java
SubwayService service = new SubwayService();
String path = service.findShortestPath("광교중앙", "강남");
System.out.println(path); // 광교중앙-상현-성복-...-강남
```

### Example 3: Calculate Travel Time
```java
SubwayService service = new SubwayService();
int minutes = service.calculateTravelTime("미금", "양재");
System.out.println("예상 시간: " + minutes + "분");
```

More examples in `DatabaseExampleUsage.java`!

## 🏗️ Architecture Overview

```
┌─────────────┐
│    View     │ SubwayMenu, Printers
└──────┬──────┘
       │
┌──────▼──────┐
│ Controller  │ SubwayManager
└──────┬──────┘
       │
┌──────▼──────┐
│  Service    │ SubwayService (Business Logic)
└──────┬──────┘
       │
┌──────▼──────┐
│    DAO      │ Interfaces (StationDAO, etc.)
└──────┬──────┘
       │
┌──────▼──────┐
│  MyBatis    │ Mapper XML files
└──────┬──────┘
       │
┌──────▼──────┐
│   MySQL     │ subway_db database
└─────────────┘
```

## 🔧 Technologies Used

- **Java 17** - Programming language
- **MySQL 8.0** - Relational database
- **MyBatis 3.5.13** - ORM framework
- **Gradle 8.14** - Build tool
- **JDBC** - Database connectivity

## ✅ Checklist

Before you begin:
- [ ] MySQL installed and running
- [ ] Java 17+ installed
- [ ] Gradle available
- [ ] IDE ready (IntelliJ recommended)

Setup steps:
- [ ] Database created (`init_subway_database.sql`)
- [ ] Password configured (`database.properties`)
- [ ] Files placed in correct locations
- [ ] Gradle build successful
- [ ] Example code runs without errors

## 🆘 Getting Help

**Common Issues:**

1. **"Access denied"** → Check MySQL password in database.properties
2. **"Database not found"** → Run init_subway_database.sql
3. **"Mapper not found"** → Check mybatis-config.xml paths
4. **"Class not found"** → Run ./gradlew clean build

See SETUP_INSTRUCTIONS.md for detailed troubleshooting.

## 📝 Project Structure

```
beyond-SW-21th-side-2team/
├── src/main/
│   ├── java/com/team2/project/
│   │   ├── config/          # MyBatis configuration
│   │   ├── controller/      # Controllers
│   │   ├── model/
│   │   │   ├── dao/         # Data access interfaces
│   │   │   └── dto/         # Data transfer objects
│   │   ├── service/         # Business logic
│   │   ├── view/            # UI components
│   │   └── run/             # Entry points
│   └── resources/
│       ├── config/          # Properties files
│       ├── mappers/         # MyBatis XML mappers
│       └── mybatis-config.xml
├── build.gradle
└── README.md
```

## 🎓 Learning Path

1. **Beginner**: Run DatabaseExampleUsage.java
2. **Intermediate**: Modify SubwayService methods
3. **Advanced**: Add new tables and relationships
4. **Expert**: Implement caching and optimization

## 🚦 Next Steps

After setup:
1. Run `DatabaseExampleUsage.java` to verify setup
2. Update `SubwayManager` to use `SubwayService`
3. Remove hardcoded data from existing code
4. Test all functionality
5. Add new features (CRUD operations, etc.)

## 📞 Support

For questions:
1. Check documentation in this package
2. Review code comments
3. Test with DatabaseExampleUsage.java
4. Check MyBatis documentation: https://mybatis.org/

## 🎉 You're Ready!

All files are in the `/mnt/user-data/outputs/` directory. Follow SETUP_INSTRUCTIONS.md for detailed guidance.

Good luck with your project! 🚀🚇

---

**Package Version:** 1.0  
**Last Updated:** November 16, 2025  
**Compatible with:** Java 17+, MySQL 8.0+, MyBatis 3.5+
