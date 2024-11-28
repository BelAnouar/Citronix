# Citronix - Lemon Farm Management Application

## Project Overview

Citronix is a comprehensive Spring Boot-based application designed to streamline the management of lemon farms. The application helps farmers track and manage their farm operations, including farm, field, tree, harvest, and sales management.

### Key Features

- **Farm Management**
    - Create, modify, and view farm details
    - Supports multi-criteria search
    - Manage farm properties like name, location, area, and creation date

- **Field Management**
    - Associate fields with farms
    - Enforce field area constraints
    - Maximum of 10 fields per farm
    - Minimum field size: 0.1 hectares
    - Maximum field size: 50% of farm area

- **Tree Management**
    - Track tree details including planting date and age
    - Productivity calculation based on tree age:
        * Young trees (< 3 years): 2.5 kg/season
        * Mature trees (3-10 years): 12 kg/season
        * Old trees (> 10 years): 20 kg/season
    - Planting period: March to May
    - Maximum tree productivity: 20 years

- **Harvest Management**
    - Track seasonal harvests (Winter, Spring, Summer, Autumn)
    - One harvest per season
    - Record harvest date and total quantity
    - Track harvest details per tree

- **Sales Management**
    - Record sales with date, unit price, customer, and associated harvest
    - Calculate revenue (quantity * unit price)

## Technical Requirements

- **Backend Framework**: Spring Boot
- **Architecture**: Layered (Controller, Service, Repository, Entity)
- **Data Validation**: Spring annotations
- **Testing**: JUnit and Mockito
- **Additional Tools**:
    - Lombok
    - Builder Pattern
    - MapStruct for DTO conversion

## Prerequisites

- Java 17+
- Maven
- PostgreSQL (or your preferred database)

## Setup Instructions

1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/citronix.git
   cd citronix
   ```

2. Configure database connection in `application.properties`
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/citronix
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build the project
   ```bash
   mvn clean install
   ```

4. Run the application
   ```bash
   mvn spring-boot:run
   ```

## API Documentation

- Swagger UI: [Endpoint to be added]
- Postman Collection: [Link to be added]



## Testing

Run unit tests:
```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## link jira 

https://web-blog.atlassian.net/jira/software/c/projects/CIT/boards/7/backlog?issueParent=10128%2C10134%2C10140%2C10148%2C10155


