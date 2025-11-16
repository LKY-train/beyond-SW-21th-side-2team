# 지하철 프로젝트 데이터베이스 설정 단계별 가이드

## 📋 필수 요구사항

- Java 17 이상
- MySQL 8.0 이상
- IntelliJ IDEA 또는 다른 Java IDE
- Gradle 8.14 (프로젝트에 포함됨)

---

## 🚀 설치 단계

### 1단계: MySQL 설치 및 설정

#### Windows:
```bash
# MySQL 설치 후 MySQL Command Line Client 실행
# 또는 MySQL Workbench 사용
```

#### macOS:
```bash
brew install mysql
brew services start mysql
mysql -u root -p
```

#### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
sudo mysql -u root -p
```

### 2단계: 데이터베이스 생성

MySQL에 접속한 후 다음 명령어 실행:

```sql
-- 루트로 접속
mysql -u root -p

-- 또는 제공된 SQL 파일 실행
mysql -u root -p < init_subway_database.sql
```

수동으로 실행하려면:

```sql
-- 데이터베이스 생성
CREATE DATABASE subway_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE subway_db;

-- init_subway_database.sql 파일의 내용 실행
-- (테이블 생성 및 데이터 삽입)
```

### 3단계: 프로젝트 구조 설정

다음과 같은 디렉토리 구조로 파일들을 배치:

```
beyond-SW-21th-side-2team/
├── build.gradle (업데이트)
├── src/
│   ├── main/
│   │   ├── java/com/team2/project/
│   │   │   ├── config/
│   │   │   │   └── MyBatisConfig.java
│   │   │   ├── controller/
│   │   │   │   └── SubwayManager.java (기존)
│   │   │   ├── model/
│   │   │   │   ├── dto/
│   │   │   │   │   ├── StationDTO.java (업데이트)
│   │   │   │   │   ├── LineDTO.java
│   │   │   │   │   ├── ConnectionDTO.java (새로 생성)
│   │   │   │   │   └── TransferStationDTO.java (새로 생성)
│   │   │   │   └── dao/
│   │   │   │       ├── StationDAO.java (새로 생성)
│   │   │   │       ├── LineDAO.java (새로 생성)
│   │   │   │       ├── ConnectionDAO.java (새로 생성)
│   │   │   │       └── TransferStationDAO.java (새로 생성)
│   │   │   ├── service/
│   │   │   │   └── SubwayService.java (새로 생성)
│   │   │   ├── view/
│   │   │   │   ├── SubwayMenu.java (기존)
│   │   │   │   └── StationByLinePrinter.java (기존)
│   │   │   └── run/
│   │   │       ├── Application.java (기존)
│   │   │       └── DatabaseExampleUsage.java (새로 생성)
│   │   └── resources/
│   │       ├── config/
│   │       │   └── database.properties
│   │       ├── mappers/
│   │       │   ├── StationMapper.xml
│   │       │   ├── LineMapper.xml
│   │       │   ├── ConnectionMapper.xml
│   │       │   └── TransferStationMapper.xml
│   │       └── mybatis-config.xml
```

### 4단계: 파일 배치

1. **Java 파일들을 올바른 패키지에 배치:**
   - `MyBatisConfig.java` → `src/main/java/com/team2/project/config/`
   - DAO 인터페이스들 → `src/main/java/com/team2/project/model/dao/`
   - 업데이트된 DTO들 → `src/main/java/com/team2/project/model/dto/`
   - `SubwayService.java` → `src/main/java/com/team2/project/service/`
   - `DatabaseExampleUsage.java` → `src/main/java/com/team2/project/run/`

2. **Resources 디렉토리 생성 및 파일 배치:**
   ```bash
   mkdir -p src/main/resources/config
   mkdir -p src/main/resources/mappers
   ```
   
   - `database.properties` → `src/main/resources/config/`
   - `mybatis-config.xml` → `src/main/resources/`
   - Mapper XML 파일들 → `src/main/resources/mappers/`

3. **build.gradle 업데이트**

### 5단계: 데이터베이스 연결 정보 설정

`src/main/resources/config/database.properties` 파일을 열고 수정:

```properties
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/subway_db?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&characterEncoding=UTF-8
db.username=root
db.password=YOUR_MYSQL_PASSWORD_HERE  # 여기에 실제 비밀번호 입력
```

**중요:** `YOUR_MYSQL_PASSWORD_HERE`를 실제 MySQL 비밀번호로 변경하세요!

### 6단계: Gradle 의존성 다운로드

```bash
# Windows
gradlew clean build

# macOS/Linux
./gradlew clean build
```

또는 IntelliJ IDEA에서:
- `View` → `Tool Windows` → `Gradle`
- `Reload All Gradle Projects` 클릭

### 7단계: 데이터베이스 연결 테스트

`DatabaseExampleUsage.java`를 실행하여 데이터베이스 연결 테스트:

```bash
# 명령줄에서
./gradlew run

# 또는 IntelliJ에서
DatabaseExampleUsage 클래스를 열고 main 메서드 옆의 실행 버튼 클릭
```

---

## 🔍 문제 해결

### 문제 1: MySQL 연결 실패
```
java.sql.SQLException: Access denied for user 'root'@'localhost'
```

**해결:**
- `database.properties`의 비밀번호가 정확한지 확인
- MySQL 서비스가 실행 중인지 확인: `sudo systemctl status mysql`

### 문제 2: 데이터베이스를 찾을 수 없음
```
java.sql.SQLException: Unknown database 'subway_db'
```

**해결:**
```sql
mysql -u root -p
CREATE DATABASE subway_db;
USE subway_db;
source /path/to/init_subway_database.sql;
```

### 문제 3: Mapper를 찾을 수 없음
```
org.apache.ibatis.binding.BindingException: Invalid bound statement
```

**해결:**
- `resources` 디렉토리가 올바른 위치에 있는지 확인
- `mybatis-config.xml`에서 mapper 경로 확인
- Gradle 빌드 재실행: `./gradlew clean build`

### 문제 4: 클래스를 찾을 수 없음
```
ClassNotFoundException: com.mysql.cj.jdbc.Driver
```

**해결:**
- `build.gradle`에 MySQL 의존성이 있는지 확인
- Gradle 리프레시 실행

---

## 📝 주요 메서드 사용 예제

### 예제 1: 모든 역 조회
```java
SubwayService service = new SubwayService();
List<StationDTO> stations = service.getAllStations();

for (StationDTO station : stations) {
    System.out.println(station.getStationName());
}
```

### 예제 2: 특정 노선의 역 조회
```java
SubwayService service = new SubwayService();
List<StationDTO> stations = service.getStationsByLine(1); // 1호선

for (StationDTO station : stations) {
    System.out.println(station.getStationName());
}
```

### 예제 3: 최단 경로 찾기
```java
SubwayService service = new SubwayService();
String path = service.findShortestPath("광교중앙", "강남");
System.out.println("경로: " + path);
```

### 예제 4: 소요 시간 계산
```java
SubwayService service = new SubwayService();
int time = service.calculateTravelTime("미금", "양재");
System.out.println("소요 시간: " + time + "분");
```

### 예제 5: 환승역 조회
```java
SubwayService service = new SubwayService();
List<StationDTO> transferStations = service.getTransferStations();

for (StationDTO station : transferStations) {
    System.out.println(station.getStationName() + " (환승역)");
}
```

---

## 🎯 다음 단계

1. **기존 SubwayManager 클래스 업데이트**
   - 하드코딩된 데이터 제거
   - SubwayService 사용하도록 변경

2. **SubwayMenu 클래스 업데이트**
   - 데이터베이스에서 데이터를 가져오도록 수정

3. **추가 기능 구현**
   - 역 추가/수정/삭제 기능
   - 노선 관리 기능
   - 실시간 열차 정보 (확장)

4. **테스트 코드 작성**
   - JUnit을 사용한 단위 테스트
   - 통합 테스트

---

## 📚 추가 학습 자료

- **MyBatis 공식 문서:** https://mybatis.org/mybatis-3/
- **MySQL 튜토리얼:** https://dev.mysql.com/doc/
- **JDBC 가이드:** https://docs.oracle.com/javase/tutorial/jdbc/

---

## ✅ 체크리스트

설정이 완료되었는지 확인:

- [ ] MySQL이 설치되고 실행 중
- [ ] subway_db 데이터베이스가 생성됨
- [ ] 모든 테이블에 데이터가 삽입됨
- [ ] 프로젝트 구조가 올바르게 설정됨
- [ ] database.properties에 올바른 비밀번호 설정
- [ ] Gradle 의존성이 다운로드됨
- [ ] DatabaseExampleUsage가 성공적으로 실행됨

모든 항목이 체크되면 데이터베이스 설정이 완료된 것입니다! 🎉
