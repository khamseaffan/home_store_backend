#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE homestore_product_db;
    CREATE DATABASE homestore_store_db;
    CREATE DATABASE homestore_inventory_db;
    CREATE DATABASE homestore_cart_db;
    CREATE DATABASE homestore_order_db;
EOSQL

echo "All databases created successfully!"
