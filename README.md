# 🚗 Car Dealership App (Java + SQL)

This is a Java-based **Car Dealership Management Application** developed in IntelliJ by **Umut Tikbas (Exaaiser)** and **Ozan Gül** as part of a full-stack database project.

Unlike the SQL-only version, this project offers a **complete interactive system** with a CLI interface that allows users to:
- 🔍 View and search vehicles
- ➕ Add and remove vehicles
- 📄 Create and store **sales** and **lease contracts**

---

## 📦 Features & Functionality

### Vehicle Search
Users can search for vehicles using various filters:
- 💰 By price range  
- 🚘 By make/model  
- 📅 By year range  
- 🎨 By color  
- 📉 By mileage range  
- 🛻 By vehicle type  

###  Vehicle Management
- Add new vehicles to the system  
- Remove existing vehicles from the database  

### 📝 Contract Management
- **SalesDao** and **LeaseDao** classes manage contracts  
- Vehicle sales and leases are stored directly into the database  

---

## 🛠 Technologies Used

- Java (OOP & JDBC)
- IntelliJ IDEA
- MySQL / SQL scripts
- Maven (dependency management)

---

## 🗃️ Database Structure

The application interacts with a relational database consisting of the following tables:

- **dealerships** – Info about branches  
- **vehicles** – All available and sold cars  
- **inventory** – Maps vehicles to dealerships  
- **sales_contracts** – Stores completed sales  
- **lease_contracts** – Stores vehicle leases  

---

## 👨‍💻 Developers

- **Ozan Gül**
- **Umut Tikbas** – [Exaaiser](https://github.com/Exaaiser)  

---

## 🔁 Reusability

The database schema is designed to be **re-runnable**:  
It drops existing tables and rebuilds them cleanly when needed.

---

> 💡 *Tip: Don't forget to import your SQL schema before launching the app!*
