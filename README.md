# Cat Shop

A secure REST API for managing cats and their owners with role-based access control.

## Overview

This service provides:
- CRUD operations for cats (name, breed, color, friends)
- Owner management (name, birthdate, cats)
- Spring Security with two roles:
  - `ADMIN`: Full access + user management
  - `USER`: Access only to their own cats
- Filtering capabilities (e.g., find cats by color)

## Key Features

### Core Functionality
- PostgreSQL database with Hibernate
- Controller-Service-DAO architecture
- DTO-based REST API (Spring Boot)
- Spring Data JPA repositories
- Multi-module Maven/Gradle build

### Security
- JWT authentication
- Endpoint protection:
    - `/api/cats/{id}` - Owner/Admin only
    - `/api/cats/filter` - All authenticated users (returns owner-specific data)
- 1:1 User-Owner relationship

## Quick Start

1. **Requirements**:
    - Java 17+
    - PostgreSQL 15+

2. **Setup**:
   ```bash
   git clone [your-repo]
   mvn install
   ```