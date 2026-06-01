#!/bin/bash

# Variables
DB_NAME="emp_hrms"
DB_USER="emp_hrms"
DB_PASSWORD="emp_hrms@123"

echo "Creating database and user..."

psql -U postgres <<EOF

-- Create database
CREATE DATABASE ${DB_NAME};

-- Create user
CREATE USER ${DB_USER} WITH PASSWORD '${DB_PASSWORD}';

-- Grant database privileges
GRANT ALL PRIVILEGES ON DATABASE ${DB_NAME} TO ${DB_USER};

EOF

echo "Creating schemas..."

psql -U postgres -d ${DB_NAME} <<EOF

-- Create schemas
CREATE SCHEMA hrms AUTHORIZATION ${DB_USER};
CREATE SCHEMA email AUTHORIZATION ${DB_USER};

-- Grant schema privileges
GRANT ALL PRIVILEGES ON SCHEMA hrms TO ${DB_USER};
GRANT ALL PRIVILEGES ON SCHEMA email TO ${DB_USER};

EOF

echo "Database setup completed successfully."