-- Script to create databases, user, and grant necessary privileges for HomeStore project.
-- Assumes Spring Boot / Hibernate will manage table creation (e.g., ddl-auto=update/create).
--
-- How to Run:
-- 1. Save this file as create_homestore_infra.sql
-- 2. Run using a PostgreSQL superuser (e.g., postgres):
--    psql -U postgres -f create_homestore_infra.sql
-- 3. Enter the superuser password when prompted.
--
-- WARNING ABOUT PASSWORD:
-- This script includes the password 'user_homestore' directly in the CREATE ROLE command
-- for simplicity during initial developer setup. In staging or production environments,
-- handle password management securely (e.g., using environment variables during deployment,
-- prompting for passwords, or using managed database services with secure credential handling).
-- Consider changing the password immediately after setup in non-development environments.

\set ON_ERROR_STOP on

-- === User and Database Creation (run from default 'postgres' db) ===
\echo 'Connecting to default database as superuser...'

-- Create user 'homestore_user' if not exists
DO
$do$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_roles
      WHERE  rolname = 'homestore_user') THEN

      CREATE ROLE homestore_user WITH LOGIN PASSWORD 'user_homestore';
      RAISE NOTICE 'User "homestore_user" created.';
   ELSE
      RAISE NOTICE 'User "homestore_user" already exists, skipping creation.';
      -- Optional: Uncomment to update password if user exists
      -- ALTER ROLE homestore_user WITH PASSWORD 'user_homestore';
      -- RAISE NOTICE 'Password for user "homestore_user" potentially updated.';
   END IF;
END
$do$;

-- Define database names
\set db_product 'homestore_product_db'
\set db_store   'homestore_store_db'
\set db_user    'homestore_user_db'
\set app_user   'homestore_user'

-- Create databases if they don't exist using \gexec for idempotency
SELECT 'CREATE DATABASE ' || quote_ident(:'db_product')
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = :'db_product')\gexec
SELECT 'CREATE DATABASE ' || quote_ident(:'db_store')
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = :'db_store')\gexec
SELECT 'CREATE DATABASE ' || quote_ident(:'db_user')
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = :'db_user')\gexec

-- Grant connect privileges on databases to the application user
GRANT ALL PRIVILEGES ON DATABASE :"db_product" TO :"app_user";
GRANT ALL PRIVILEGES ON DATABASE :"db_store" TO :"app_user";
GRANT ALL PRIVILEGES ON DATABASE :"db_user" TO :"app_user";

\echo 'User and databases checked/created, connect privileges granted.'

-- === Grant Schema and Default Privileges (Essential for Spring Boot Schema Management) ===

-- Function to grant necessary schema privileges within a database
\newcommand grant_schema_privs_in_db [1]
\echo 'Connecting to database:' :'1' 'to grant schema privileges...'
\c :'1'
-- Grant usage on the public schema
GRANT USAGE ON SCHEMA public TO :"app_user";
-- Set default privileges for future objects created by any role (usually the app user via JPA)
-- This ensures the app_user can use tables/sequences it (or another role) creates later.
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO :"app_user";
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT USAGE, SELECT ON SEQUENCES TO :"app_user";
\echo 'Granted USAGE ON SCHEMA public and set default privileges in database:' :'1' 'for user:' :"app_user"

-- Apply schema privileges to each database
\grant_schema_privs_in_db `echo $db_product`
\grant_schema_privs_in_db `echo $db_store`
\grant_schema_privs_in_db `echo $db_user`


-- === Script Finished ===
\echo 'Database infrastructure setup script completed successfully.'
\echo 'User homestore_user can now connect to the databases and manage schema objects.'
-- Reconnect to default DB if needed for subsequent commands in an interactive session
-- \c postgres

