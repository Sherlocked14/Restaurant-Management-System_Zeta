# Restaurant Management System

A robust Java-based restaurant management solution leveraging JDBC and PostgreSQL technologies, delivering comprehensive functionality for order processing, billing operations, table reservations, and customer relationship management.

## ✨ Key Features

### Primary Capabilities
- **User Administration**: Role-based authentication system supporting Managers, Waiters, and Kitchen Personnel
- **Customer Relations**: Complete customer registration and profile administration
- **Table Operations**: Dynamic table allocation with real-time status monitoring and capacity oversight
- **Order Processing**: End-to-end order lifecycle management from initial placement through completion
- **Financial Management**: Automated invoice generation incorporating tax calculations and discount applications
- **Transaction Processing**: Multi-channel payment acceptance (Cash, Card, UPI, Digital Wallet)
- **Reservation System**: Advanced table booking with comprehensive date/time scheduling
- **Analytics Dashboard**: Comprehensive sales reporting and business intelligence

### Technical Architecture
- **Data Access Layer**: Implements DAO pattern for clean data abstraction
- **Business Logic**: Dedicated service layer for application logic separation
- **Object Creation**: Factory pattern implementation for centralized DAO instantiation
- **Database Integration**: Seamless PostgreSQL connectivity and transaction management
- **User Interface**: Interactive command-line interface with intuitive navigation

## �️ System Architecture

The application employs a sophisticated layered architecture approach:

```
src/main/java/org/example/
├── model/              # Domain entities (User, Customer, Order, etc.)
├── dao/               # Data persistence layer
│   ├── interfaces/    # Contract definitions for data access
│   └── impl/          # Concrete DAO implementations
├── service/           # Application business logic
│   ├── interfaces/    # Service contract definitions
│   └── impl/          # Business logic implementations
├── controller/        # Presentation layer controllers
├── util/              # System utilities (DatabaseUtil)
└── Main.java          # Application bootstrap
```

## 📋 System Requirements

- **Java Runtime**: Version 17 or later
- **Database**: PostgreSQL 12 or later
- **Build Tool**: Maven 3.6 or later

## � Installation Guide

### 1. Repository Setup
```bash
git clone <repository-url>
cd restaurant-management-system
```

### 2. Database Configuration

Initialize PostgreSQL database with required schema:

```sql
-- Database creation
CREATE DATABASE restaurant_management;

-- Schema initialization
\c restaurant_management;

-- User accounts table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    role VARCHAR(20) NOT NULL,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Customer registry table
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Restaurant tables registry
CREATE TABLE tables (
    table_id SERIAL PRIMARY KEY,
    table_number INTEGER UNIQUE NOT NULL,
    capacity INTEGER NOT NULL,
    status VARCHAR(20) DEFAULT 'Available',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Order tracking table
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    table_id INTEGER REFERENCES tables(table_id),
    waiter_id INTEGER REFERENCES users(user_id),
    order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Placed',
    total_amount DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Billing information table
CREATE TABLE bills (
    bill_id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES orders(order_id),
    total_amount DECIMAL(10,2) NOT NULL,
    discount DECIMAL(10,2) DEFAULT 0,
    tax DECIMAL(10,2) DEFAULT 0,
    final_amount DECIMAL(10,2) NOT NULL,
    payment_status VARCHAR(20) DEFAULT 'Unpaid',
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Payment transactions table
CREATE TABLE payments (
    payment_id SERIAL PRIMARY KEY,
    bill_id INTEGER REFERENCES bills(bill_id),
    amount_paid DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    transaction_id VARCHAR(100),
    payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Successful'
);

-- Reservation management table
CREATE TABLE table_bookings (
    booking_id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customers(customer_id),
    table_id INTEGER REFERENCES tables(table_id),
    booking_date DATE NOT NULL,
    booking_time TIME NOT NULL,
    party_size INTEGER,
    status VARCHAR(20) DEFAULT 'Confirmed',
    special_requests TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 3. Database Connection Setup

Configure database credentials in `src/main/java/org/example/util/DatabaseUtil.java`:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/restaurant_management";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

### 4. Application Deployment

```bash
# Project compilation
mvn clean compile

# Application execution
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## � Application Usage

### Primary Navigation Menu

Upon application launch, the following options are presented:

```
=== Restaurant Management System ===
1. User Management
2. Customer Management
3. Table Management
4. Order Management
5. Bill Management
6. Payment Management
7. Table Booking Management
8. Exit
```

### Operational Workflows

#### 1. Initial System Configuration
```
1. Configure User Accounts (Manager, Waiters, Kitchen Personnel)
2. Initialize Table Configuration (table numbers and seating capacity)
3. Register Customer Database (customer profile creation)
```

#### 2. Order Processing Pipeline
```
1. Customer arrival → Verify table availability
2. Customer seating → Waiter assignment
3. Order placement → Status: "Placed"
4. Kitchen preparation → Status: "Preparing"
5. Food service → Status: "Served"
6. Order completion → Status: "Completed"
```

#### 3. Financial Processing Pipeline
```
1. Invoice generation for completed orders
2. Discount application and tax computation
3. Customer bill presentation
4. Payment processing (Cash/Card/UPI/Wallet)
5. Payment status confirmation
6. Table availability restoration
```

#### 4. Reservation Management Pipeline
```
1. Customer reservation request
2. Table availability verification for requested date/time
3. Booking record creation
4. Reservation confirmation
5. Table status update to "Reserved"
```

## 📊 Database Schema Overview

### Core Business Entities

- **Users**: System operators with designated roles (Manager, Waiter, KitchenStaff)
- **Customers**: Restaurant patrons and their contact information
- **Tables**: Physical restaurant tables with capacity and availability status
- **Orders**: Customer orders with comprehensive item tracking and status monitoring
- **Bills**: Financial documents with tax calculations and discount applications
- **Payments**: Transaction records supporting multiple payment channels
- **Table Bookings**: Advance reservations with comprehensive scheduling information

### Relationship Structure

```mermaid
erDiagram

    User {
        INT user_id PK
        VARCHAR username
        VARCHAR password
        VARCHAR email
        VARCHAR phone
        ENUM role
        BOOLEAN is_active
        TIMESTAMP created_at
    }

    Customer {
        INT customer_id PK
        VARCHAR name
        VARCHAR phone
        VARCHAR email
        BOOLEAN is_registered
        TIMESTAMP created_at
    }

    Table {
        INT table_id PK
        INT table_number
        INT capacity
        ENUM status
    }

    TableBooking {
        INT booking_id PK
        INT customer_id FK
        INT table_id FK
        DATE booking_date
        TIME booking_time
        ENUM status
        TIMESTAMP created_at
    }

    MenuItem {
        INT item_id PK
        VARCHAR name
        TEXT description
        DECIMAL price
        ENUM category
        BOOLEAN availability
        TIMESTAMP created_at
    }

    Order {
        INT order_id PK
        INT table_id FK
        INT waiter_id FK
        TIMESTAMP order_time
        ENUM status
    }

    OrderItem {
        INT order_item_id PK
        INT order_id FK
        INT menu_item_id FK
        INT quantity
        ENUM status
    }

    Bill {
        INT bill_id PK
        INT order_id FK
        DECIMAL total_amount
        DECIMAL discount
        DECIMAL tax
        DECIMAL final_amount
        ENUM payment_status
        TIMESTAMP generated_at
    }

    Payment {
        INT payment_id PK
        INT bill_id FK
        ENUM payment_method
        DECIMAL amount_paid
        TIMESTAMP payment_time
        ENUM status
    }

    Employee {
        INT employee_id PK
        INT user_id FK
        VARCHAR designation
        TIME shift_start
        TIME shift_end
        DATE joined_date
    }

    SalesReport {
        INT report_id PK
        DATE report_date
        DECIMAL total_sales
        INT total_orders
        TEXT top_items
        INT generated_by FK
    }

    %% Entity Relationships
    Customer ||--o{ TableBooking : creates
    Table ||--o{ TableBooking : accommodates
    User ||--o{ Employee : represents
    Employee ||--o{ Order : processes
    Table ||--o{ Order : serves
    Order ||--o{ OrderItem : includes
    MenuItem ||--o{ OrderItem : comprises
    Order ||--|| Bill : generates
    Bill ||--|| Payment : settles
    User ||--o{ SalesReport : produces

```

## ⚙️ System Configuration

### Database Connection Properties
- Connection URL: `jdbc:postgresql://localhost:5432/restaurant_management`
- JDBC Driver: `org.postgresql.Driver`
- Connection Management: Basic pooling implementation

### Authorization and Access Control
- **Manager**: Complete system administration, user management, reporting capabilities
- **Waiter**: Order processing, customer service, billing operations
- **KitchenStaff**: Order status management, kitchen workflow operations

## 🎨 System Design Documentation

### Class Architecture

```mermaid
classDiagram
    %% Domain Models
    class User {
        -int userId
        -String username
        -String password
        -String email
        -String phone
        -Role role
        -boolean isActive
        -Timestamp createdAt
        +getUserId() int
        +setUserId(int) void
        +getUsername() String
        +setUsername(String) void
        +getRole() Role
        +setRole(Role) void
    }

    class Customer {
        -int customerId
        -String name
        -String phone
        -String email
        -boolean isActive
        -Timestamp createdAt
        +getCustomerId() int
        +setCustomerId(int) void
        +getName() String
        +setName(String) void
        +getPhone() String
        +setPhone(String) void
    }

    class Table {
        -int tableId
        -int tableNumber
        -int capacity
        -Status status
        +getTableId() int
        +setTableId(int) void
        +getTableNumber() int
        +setTableNumber(int) void
        +getStatus() Status
        +setStatus(Status) void
    }

    %% Data Access Implementations
    class UserDaoImpl {
        -Connection connection
        +addUser(User) void
        +getUserById(int) User
        +getAllUsers() List
        +updateUser(User) void
        +deleteUser(int) void
    }

    class OrderDaoImpl {
        -Connection connection
        +addOrder(Order) void
        +getOrderById(int) Order
        +getAllOrders() List
        +updateOrder(Order) void
        +deleteOrder(int) void
    }

    %% Factory Implementation
    class RestaurantDaoFactory {
        +getUserDAO() UserDao
        +getOrderDAO() OrderDao
        +getBillDAO() BillDao
        +getPaymentDAO() PaymentDao
        +getTableDAO() TableDao
        +getCustomerDAO() CustomerDao
        +getTableBookingDAO() TableBookingDao
    }

    %% Database Utility
    class DatabaseUtil {
        -String URL
        -String USER
        -String PASSWORD
        +getConnection() Connection
        +closeConnection(Connection) void
    }

    %% Entity Relationships
    Order --> Bill
    Bill --> Payment
    Customer --> TableBooking
    Table --> TableBooking
    Table --> Order
    User --> Order
    UserDao <|.. UserDaoImpl
    OrderDao <|.. OrderDaoImpl
    RestaurantDaoFactory --> UserDao
    RestaurantDaoFactory --> OrderDao
    RestaurantDaoFactory --> BillDao
    UserDaoImpl --> DatabaseUtil
    OrderDaoImpl --> DatabaseUtil
```

### Use Case Architecture

```mermaid
flowchart TD
    Manager[Manager]
    Waiter[Waiter]
    KitchenStaff[Kitchen Staff]
    Customer[Customer]
    
    subgraph System[Restaurant Management System]
        UC1[Manage Users]
        UC2[Register Customer]
        UC3[Manage Tables]
        UC4[Take Order]
        UC5[Update Order Status]
        UC6[Generate Bill]
        UC7[Process Payment]
        UC8[Book Table]
        UC9[View Kitchen Orders]
        UC10[Generate Reports]
    end
    
    Manager --> UC1
    Manager --> UC3
    Manager --> UC10
    
    Waiter --> UC2
    Waiter --> UC4
    Waiter --> UC6
    Waiter --> UC7
    Waiter --> UC8
    
    KitchenStaff --> UC5
    KitchenStaff --> UC9
    
    Customer -.-> UC4
    Customer -.-> UC8
```

### Process Flow Documentation

#### Order Processing Sequence

```mermaid
sequenceDiagram
    participant C as Customer
    participant W as Waiter
    participant S as System
    participant DB as Database
    participant K as Kitchen
    
    C->>W: Requests to place order
    W->>S: Check available tables
    S->>DB: Query tables with status Available
    DB-->>S: Return available tables
    S-->>W: Display available tables
    
    W->>S: Select table for customer
    S->>DB: Check table availability
    DB-->>S: Confirm table is available
    
    W->>S: Enter order details
    Note over W,S: Table ID, Waiter ID, Menu Items
    
    S->>S: Create Order object
    S->>S: Set status to Placed
    S->>DB: Save order to database
    DB-->>S: Order saved successfully
    
    S->>DB: Update table status to Occupied
    DB-->>S: Table status updated
    
    S->>K: Send order to kitchen
    K-->>S: Order received
    S->>DB: Update order status to Preparing
    
    S-->>W: Order placed successfully
    W-->>C: Order confirmation
    
    Note over K: Kitchen prepares food
    K->>S: Update order status to Served
    S->>DB: Update order status
    
    W->>C: Serve food
    W->>S: Update order status to Completed
    S->>DB: Update order status
```

#### Financial Processing Sequence

```mermaid
sequenceDiagram
    participant C as Customer
    participant W as Waiter
    participant S as System
    participant DB as Database
    participant P as Payment System
    
    C->>W: Request bill
    W->>S: Generate bill for order
    S->>DB: Get order details
    DB-->>S: Return order information
    
    S->>S: Calculate total amount
    W->>S: Enter discount if any
    S->>S: Apply discount
    S->>S: Calculate tax
    S->>S: Calculate final amount
    
    S->>S: Create Bill object
    S->>DB: Save bill to database
    DB-->>S: Bill saved successfully
    
    S-->>W: Bill generated
    W->>C: Present bill
    
    C->>W: Choose payment method
    W->>S: Process payment
    S->>P: Process payment transaction
    P-->>S: Payment confirmation
    
    S->>S: Create Payment record
    S->>DB: Save payment details
    S->>DB: Update bill status to Paid
    DB-->>S: Payment recorded
    
    S->>DB: Update table status to Available
    DB-->>S: Table status updated
    
    S-->>W: Payment successful
    W-->>C: Payment receipt
```

#### Reservation Management Sequence

```mermaid
sequenceDiagram
    participant C as Customer
    participant S as Staff
    participant Sys as System
    participant DB as Database
    
    C->>S: Request table booking
    S->>Sys: Check customer in system
    Sys->>DB: Query customer by phone or email
    
    alt Customer exists
        DB-->>Sys: Return customer details
        Sys-->>S: Customer found
    else New customer
        DB-->>Sys: Customer not found
        S->>Sys: Create new customer
        Sys->>DB: Save customer details
        DB-->>Sys: Customer created
    end
    
    S->>Sys: Enter booking details
    Note over S,Sys: Date, Time, Party Size
    
    Sys->>DB: Check table availability
    DB-->>Sys: Return available tables
    
    alt Tables available
        Sys-->>S: Show available tables
        S->>Sys: Select table
        Sys->>Sys: Create booking record
        Sys->>DB: Save booking
        DB-->>Sys: Booking confirmed
        
        Sys->>DB: Update table status to Reserved
        DB-->>Sys: Table status updated
        
        Sys-->>S: Booking successful
        S-->>C: Booking confirmation
        
    else No tables available
        Sys-->>S: No tables available
        S->>C: Suggest alternative times
        
        alt Customer accepts alternative
            C->>S: Accept new time
            S->>Sys: Update booking details
        else Customer declines
            C->>S: Cancel booking request
            S-->>C: Booking cancelled
        end
    end
```

### Activity Flow Documentation

#### Order Placement Workflow

```mermaid
flowchart TD
    A[Customer Arrives] --> B{Table Available?}
    B -->|No| C[Wait for Table]
    C --> B
    B -->|Yes| D[Seat Customer at Table]
    
    D --> E[Waiter Approaches Table]
    E --> F[Present Menu to Customer]
    F --> G[Customer Reviews Menu]
    
    G --> H{Ready to Order?}
    H -->|No| I[Customer Needs More Time]
    I --> G
    H -->|Yes| J[Customer Places Order]
    
    J --> K[Waiter Records Order Details]
    K --> L[Enter Table ID in System]
    L --> M[Enter Waiter ID]
    M --> N[Add Menu Items to Order]
    
    N --> O{More Items?}
    O -->|Yes| P[Add Another Item]
    P --> N
    O -->|No| Q[Calculate Order Total]
    
    Q --> R[Set Order Status to Placed]
    R --> S[Save Order to Database]
    S --> T[Update Table Status to Occupied]
    
    T --> U[Send Order to Kitchen]
    U --> V[Kitchen Receives Order]
    V --> W[Update Order Status to Preparing]
    
    W --> X[Kitchen Prepares Food]
    X --> Y[Update Order Status to Served]
    Y --> Z[Waiter Delivers Food]
    Z --> AA[Update Order Status to Completed]
    
    AA --> BB[Order Process Complete]
```

#### Financial Processing Workflow

```mermaid
flowchart TD
    A[Customer Requests Bill] --> B[Waiter Initiates Billing]
    B --> C[Retrieve Order Details]
    C --> D[Get Order ID from System]
    
    D --> E[Calculate Base Amount]
    E --> F[Enter Total Amount]
    F --> G{Apply Discount?}
    
    G -->|Yes| H[Enter Discount Amount]
    G -->|No| I[Set Discount to 0]
    H --> J[Calculate After Discount]
    I --> J
    
    J --> K{Apply Tax?}
    K -->|Yes| L[Calculate Tax Amount]
    K -->|No| M[Set Tax to 0]
    L --> N[Calculate Final Amount]
    M --> N
    
    N --> O[Final Amount = Total - Discount + Tax]
    O --> P[Set Payment Status to Unpaid]
    P --> Q[Set Generated Timestamp]
    
    Q --> R[Save Bill to Database]
    R --> S{Bill Saved Successfully?}
    
    S -->|No| T[Display Error Message]
    T --> U[Retry Bill Generation]
    U --> R
    
    S -->|Yes| V[Print Bill for Customer]
    V --> W[Present Bill to Customer]
    W --> X[Customer Reviews Bill]
    
    X --> Y{Bill Acceptable?}
    Y -->|No| Z[Customer Disputes Bill]
    Z --> AA[Manager Reviews Dispute]
    AA --> BB[Adjust Bill if Necessary]
    BB --> V
    
    Y -->|Yes| CC[Customer Ready to Pay]
    CC --> DD[Proceed to Payment]
```

#### Reservation Management Workflow

```mermaid
flowchart TD
    A[Customer Wants to Book Table] --> B{Booking Method}
    B -->|Phone Call| C[Staff Receives Call]
    B -->|In Person| D[Customer Visits Restaurant]
    B -->|Online| E[Customer Uses Online System]
    
    C --> F[Staff Opens Booking System]
    D --> F
    E --> F
    
    F --> G[Enter Customer Details]
    G --> H{Existing Customer?}
    H -->|No| I[Create New Customer Record]
    H -->|Yes| J[Retrieve Customer ID]
    I --> K[Get Customer ID]
    J --> K
    
    K --> L[Customer Specifies Requirements]
    L --> M[Enter Booking Date]
    M --> N[Enter Booking Time]
    N --> O[Enter Party Size]
    
    O --> P[Check Table Availability]
    P --> Q{Tables Available?}
    
    Q -->|No| R[Suggest Alternative Times]
    R --> S{Customer Accepts Alternative?}
    S -->|No| T[End Booking Process]
    S -->|Yes| U[Update Booking Details]
    U --> P
    
    Q -->|Yes| V[Display Available Tables]
    V --> W[Select Suitable Table]
    W --> X[Enter Table ID]
    
    X --> Y[Set Booking Status to Confirmed]
    Y --> Z[Set Created Timestamp]
    Z --> AA[Save Booking to Database]
    
    AA --> BB{Booking Saved Successfully?}
    BB -->|No| CC[Display Error Message]
    CC --> DD[Retry Booking]
    DD --> AA
    
    BB -->|Yes| EE[Generate Booking Confirmation]
    EE --> FF[Update Table Status to Reserved]
    FF --> GG{Notification Method}
    
    GG -->|SMS| HH[Send SMS Confirmation]
    GG -->|Email| II[Send Email Confirmation]
    GG -->|Phone| JJ[Call Customer to Confirm]
    
    HH --> KK[Booking Process Complete]
    II --> KK
    JJ --> KK
    
    KK --> LL[Set Reminder for Booking Date]
    LL --> MM[Monitor for Customer Arrival]
```

---

**Engineered with precision for optimal restaurant operations**
