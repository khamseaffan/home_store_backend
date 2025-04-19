#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE homestore_user_db;
    CREATE DATABASE homestore_product_db;
    CREATE DATABASE homestore_store_db;
EOSQL
