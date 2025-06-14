
# Employee Management System 🚀

A **Java-based console application** using **Hibernate & JPA** to manage employee records, departments, projects, and addresses. Supports full **CRUD operations** and demonstrates **JPA relationships** like One-to-Many, Many-to-Many, and One-to-One.

---

## ✨ Features

- **Add Employee**: Name, phone, email, optional department, one project, and address. 📝  
- **Fetch Employee**: View complete details by ID. 🔍  
- **Update Employee**: Modify name, phone, and email. ✏️  
- **Delete Employee**: Remove an employee record. 🗑️  

### 🧩 Database Relationships

- 🏢 **One-to-Many**: Department → Employees  
- 🔗 **Many-to-Many**: Employee ↔ Project (single project for now)  
- 🏠 **One-to-One**: Employee ↔ Address  

### 💻 Console Interface

- Built with Java’s `Scanner` for interactive inputs.

---

## 🛠️ Tech Stack

- **Language**: Java 17+  
- **ORM**: Hibernate with JPA  
- **Database**: MySQL / PostgreSQL (configurable)  
- **Build Tool**: Maven  

---

## 📋 Prerequisites

- Java 17 or higher  
- Maven  
- MySQL or PostgreSQL  
- Hibernate & JDBC drivers  

---

## 🚀 Setup

### 1. Clone the Repository

```bash
git clone https://github.com/DC-HaD3S/Employee-Management-System.git
cd Employee-Management-System
````

### 2. Configure Database

Edit `src/main/resources/META-INF/persistence.xml`:

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/employee_db"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="your-password"/>
<property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
```

---

### ⚠️ Initial Data Setup Order

Before adding employees, make sure to insert:

* ✅ **Departments**
* ✅ **Projects**
* ✅ **Employees** (last)

> 🔁 This order is important because employees are mapped to both departments and projects.
> Adding employees without existing departments/projects may cause foreign key errors or null mappings.

You can add departments and projects through the app interface or pre-insert them directly in the database.

---

### 3. Build & Run

```bash
mvn clean install
java -cp target/employee-management-system.jar com.jsp.driver.Driver
```

---

## 🎮 Usage

After running the app, you'll see:

```text
Enter 1 To Add New Employee  
Enter 2 To Fetch The Details Of The Existing Employee  
Enter 3 To Update The Existing Employee Details  
Enter 4 To Delete The Existing Employee  
Enter 5 To Exit  
```

### Example: Adding an Employee

```
Enter The Employee Name: Rohit Sharma  
Enter The Employee Phone: 1234567890  
Enter The Employee Email: rohit@example.com  
Enter The Department ID (0 to skip): 1  
Enter Project ID (0 to skip): 1  
Enter The Street: 123 Main St  
Enter The City: Mumbai  
Enter The State: Maharashtra  
Enter The Country: India  
Enter The Pincode: 400001  
Employee added successfully: Rohit Sharma
```

---

## 📂 Project Structure

```
employee-management-system/
├── src/
│   └── main/
│       ├── java/com/jsp/
│       │   ├── entity/         # Entity classes
│       │   ├── dao/            # DAO classes
│       │   ├── service/        # Service layer
│       │   └── driver/         # Main driver class
│       └── resources/
│           └── META-INF/
│               └── persistence.xml
├── pom.xml
├── README.md
├── LICENSE
```

---

## 🗄️ Database Schema

* `emp`: Employee details
* `dept`: Department details
* `project`: Project details
* `project_employees`: Join table for Employee ↔ Project
* `add`: Address details (auto-created via Hibernate)

> Tables are auto-generated with `hbm2ddl.auto = create` or `update`

---

## 🌟 Future Enhancements

* Support multiple project assignments per employee
* Input validation and error handling
* GUI with JavaFX or Swing
* Unit testing with JUnit

---

## 🤝 Contributing

1. Fork the repo
2. Create a new branch:

```bash
git checkout -b feature/your-feature
```

3. Commit your changes:

```bash
git commit -m "Add feature"
```

4. Push to your fork:

```bash
git push origin feature/your-feature
```

5. Open a pull request

---

## 📜 License

[MIT License](LICENSE)

---

## 📬 Contact

For questions or suggestions contact: `rohitzirmute821@gmail.com`

