#!/bin/bash

# HRMS Database
DB_NAME="emp_hrms"
DB_USER="emp_hrms"
DB_PASSWORD="emp_hrms@123"

# Keycloak Database
KeyCloak_DB_NAME="keycloak"
KeyCloak_DB_USER="keycloak"
KeyCloak_DB_PASSWORD="keycloak@123"

echo "Creating databases and users..."

psql -U postgres <<EOF

CREATE DATABASE ${DB_NAME};
CREATE DATABASE ${KeyCloak_DB_NAME};

CREATE USER ${DB_USER} WITH PASSWORD '${DB_PASSWORD}';
CREATE USER ${KeyCloak_DB_USER} WITH PASSWORD '${KeyCloak_DB_PASSWORD}';

GRANT ALL PRIVILEGES ON DATABASE ${DB_NAME} TO ${DB_USER};
GRANT ALL PRIVILEGES ON DATABASE ${KeyCloak_DB_NAME} TO ${KeyCloak_DB_USER};

EOF

echo "Creating HRMS schemas..."

psql -U postgres -d ${DB_NAME} <<EOF

CREATE SCHEMA hrms AUTHORIZATION ${DB_USER};
CREATE SCHEMA email AUTHORIZATION ${DB_USER};

GRANT ALL PRIVILEGES ON SCHEMA hrms TO ${DB_USER};
GRANT ALL PRIVILEGES ON SCHEMA email TO ${DB_USER};

EOF

echo "Creating Keycloak schema..."

psql -U postgres -d ${KeyCloak_DB_NAME} <<EOF

CREATE SCHEMA keycloak AUTHORIZATION ${KeyCloak_DB_USER};

GRANT ALL PRIVILEGES ON SCHEMA keycloak TO ${KeyCloak_DB_USER};

EOF

echo "Database setup completed successfully."
