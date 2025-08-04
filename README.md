# Restaurant Management System

A comprehensive Java-based restaurant management system built with JDBC and PostgreSQL, featuring complete order management, billing, table booking, and customer management capabilities.

## 🚀 Features

### Core Functionality
- **User Management**: Role-based access for Managers, Waiters, and Kitchen Staff
- **Customer Management**: Customer registration and profile management
- **Table Management**: Table allocation, status tracking, and capacity management
- **Order Management**: Complete order lifecycle from placement to completion
- **Billing System**: Automated bill generation with tax and discount calculations
- **Payment Processing**: Multiple payment methods (Cash, Card, UPI, Wallet)
- **Table Booking**: Advance table reservations with date/time management
- **Sales Reporting**: Daily sales reports and analytics

### Technical Features
- **DAO Pattern**: Clean separation of data access logic
- **Service Layer**: Business logic abstraction
- **Factory Pattern**: Centralized DAO creation and management
- **PostgreSQL Integration**: Robust database connectivity
- **Console-based UI**: Interactive command-line interface

## 🏗️ Architecture

The project follows a layered architecture pattern:

```
src/main/java/org/example/
├── model/              # Entity classes (User, Customer, Order, etc.)
├── dao/               # Data Access Objects
│   ├── interfaces/    # DAO interfaces
│   └── impl/          # DAO implementations
├── service/           # Business logic layer
│   ├── interfaces/    # Service interfaces
│   └── impl/          # Service implementations
├── controller/        # UI controllers
├── util/              # Utility classes (DatabaseUtil)
└── Main.java          # Application entry point
```

## 📋 Prerequisites

- **Java 17** or higher
- **PostgreSQL 12** or higher
- **Maven 3.6** or higher

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd restaurant-management-system
```

### 2. Database Setup

Create a PostgreSQL database and tables:

```sql
-- Create database
CREATE DATABASE restaurant_management;

-- Connect to the database and create tables
\c restaurant_management;

-- Users table
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

-- Customers table
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tables table
CREATE TABLE tables (
    table_id SERIAL PRIMARY KEY,
    table_number INTEGER UNIQUE NOT NULL,
    capacity INTEGER NOT NULL,
    status VARCHAR(20) DEFAULT 'Available',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Orders table
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

-- Bills table
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

-- Payments table
CREATE TABLE payments (
    payment_id SERIAL PRIMARY KEY,
    bill_id INTEGER REFERENCES bills(bill_id),
    amount_paid DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    transaction_id VARCHAR(100),
    payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Successful'
);

-- Table Bookings table
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

### 3. Configure Database Connection

Update database credentials in `src/main/java/org/example/util/DatabaseUtil.java`:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/restaurant_management";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

### 4. Build and Run

```bash
# Compile the project
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## 🎯 Usage

### Main Menu Options

When you run the application, you'll see:

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

### Sample Workflows

#### 1. Setting Up the System
```
1. Add Users (Manager, Waiters, Kitchen Staff)
2. Add Tables (Configure table numbers and capacity)
3. Add Customers (Register customer profiles)
```

#### 2. Order Management Workflow
```
1. Customer arrives → Check available tables
2. Seat customer → Waiter takes order
3. Order placed → Status: "Placed"
4. Kitchen prepares → Status: "Preparing"
5. Food served → Status: "Served"
6. Customer finishes → Status: "Completed"
```

#### 3. Billing Workflow
```
1. Generate bill for completed order
2. Apply discounts and calculate tax
3. Present bill to customer
4. Process payment (Cash/Card/UPI/Wallet)
5. Update payment status
6. Clear table for next customer
```

#### 4. Table Booking Workflow
```
1. Customer requests reservation
2. Check table availability for date/time
3. Create booking record
4. Confirm reservation
5. Update table status to "Reserved"
```

## 📊 Database Schema

### Key Entities

- **Users**: System users with roles (Manager, Waiter, KitchenStaff)
- **Customers**: Restaurant customers and their profiles
- **Tables**: Restaurant tables with capacity and status
- **Orders**: Customer orders with items and status tracking
- **Bills**: Generated bills with tax and discount calculations
- **Payments**: Payment records with multiple payment methods
- **Table Bookings**: Advance reservations with date/time

### Entity Relationships

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

    %% Relationships
    Customer ||--o{ TableBooking : makes
    Table ||--o{ TableBooking : is_booked_in
    User ||--o{ Employee : is
    Employee ||--o{ Order : takes
    Table ||--o{ Order : is_for
    Order ||--o{ OrderItem : contains
    MenuItem ||--o{ OrderItem : is_part_of
    Order ||--|| Bill : generates
    Bill ||--|| Payment : is_paid_by
    User ||--o{ SalesReport : generates

```

## 🔧 Configuration

### Application Properties
- Database URL: `jdbc:postgresql://localhost:5432/restaurant_management`
- Driver: `org.postgresql.Driver`
- Connection pooling: Basic connection management

### User Roles and Permissions
- **Manager**: Full system access, user management, reports
- **Waiter**: Order management, customer service, billing
- **KitchenStaff**: Order status updates, kitchen operations

## 🎨 System Diagrams

### Class Diagram

```mermaid
classDiagram
    %% Model Classes
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

    class Order {
        -int orderId
        -int tableId
        -int waiterId
        -Timestamp orderTime
        -Status status
        -double totalAmount
        +getOrderId() int
        +setOrderId(int) void
        +getTableId() int
        +setTableId(int) void
        +getStatus() Status
        +setStatus(Status) void
    }

    class Bill {
        -int billId
        -int orderId
        -double totalAmount
        -double discount
        -double tax
        -double finalAmount
        -PaymentStatus paymentStatus
        -Timestamp generatedAt
        +getBillId() int
        +setBillId(int) void
        +calculateFinalAmount() void
        +getPaymentStatus() PaymentStatus
    }

    class Payment {
        -int paymentId
        -int billId
        -PaymentMethod paymentMethod
        -double amountPaid
        -Timestamp paymentTime
        -Status status
        +getPaymentId() int
        +setPaymentId(int) void
        +getPaymentMethod() PaymentMethod
        +processPayment() void
    }

    class TableBooking {
        -int bookingId
        -int customerId
        -int tableId
        -Date bookingDate
        -Time bookingTime
        -int partySize
        -Status status
        -Timestamp createdAt
        +getBookingId() int
        +setBookingId(int) void
        +confirmBooking() void
        +cancelBooking() void
    }

    %% DAO Interfaces
    class UserDao {
        <<interface>>
        +addUser(User) void
        +getUserById(int) User
        +getAllUsers() List
        +updateUser(User) void
        +deleteUser(int) void
    }

    class OrderDao {
        <<interface>>
        +addOrder(Order) void
        +getOrderById(int) Order
        +getAllOrders() List
        +updateOrder(Order) void
        +deleteOrder(int) void
    }

    class BillDao {
        <<interface>>
        +generateBill(Bill) void
        +getBillByOrderId(int) Bill
        +getUnpaidBills() List
        +updateBill(Bill) void
    }

    %% DAO Implementations
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

    %% Factory
    class RestaurantDaoFactory {
        +getUserDAO() UserDao
        +getOrderDAO() OrderDao
        +getBillDAO() BillDao
        +getPaymentDAO() PaymentDao
        +getTableDAO() TableDao
        +getCustomerDAO() CustomerDao
        +getTableBookingDAO() TableBookingDao
    }

    %% Utility
    class DatabaseUtil {
        -String URL
        -String USER
        -String PASSWORD
        +getConnection() Connection
        +closeConnection(Connection) void
    }

    %% Relationships
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

### Use Case Diagram

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

### Sequence Diagrams

#### Order Placement Sequence

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

#### Billing Process Sequence

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

#### Table Booking Sequence

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

### Activity Diagrams

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

#### Billing Workflow

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

#### Table Booking Workflow

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

**Built with ❤️ for efficient restaurant management**
# RMS_Zeta
# RMS_Zeta
