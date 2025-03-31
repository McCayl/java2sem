-- Отключаем все внешние ключи (если они есть), чтобы можно было удалить все таблицы
SET session_replication_role = replica;

-- Удаляем таблицы
DROP TABLE IF EXISTS computer_locations CASCADE;
DROP TABLE IF EXISTS assignments CASCADE;
DROP TABLE IF EXISTS offices CASCADE;
DROP TABLE IF EXISTS employees CASCADE;
DROP TABLE IF EXISTS computers CASCADE;

-- Включаем обратно внешние ключи (если нужно)
SET session_replication_role = DEFAULT;

-- Создаём таблицу с компьютерами
CREATE TABLE computers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpu VARCHAR(100) NOT NULL,
    ram INTEGER NOT NULL,
    storage VARCHAR(100) NOT NULL,
    os VARCHAR(100) NOT NULL
);

-- Создаём таблицу с сотрудниками
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL
);

-- Создаём таблицу с кабинетами, где стоят компьютеры
CREATE TABLE offices (
    id SERIAL PRIMARY KEY,
    room_number VARCHAR(50) UNIQUE NOT NULL,
    floor INTEGER NOT NULL,
    building VARCHAR(255) NOT NULL
);

-- Создаём таблицу для учёта назначений компьютеров сотрудникам
CREATE TABLE assignments (
    id SERIAL PRIMARY KEY,
    computer_id INTEGER REFERENCES computers(id) ON DELETE CASCADE,
    employee_id INTEGER REFERENCES employees(id) ON DELETE SET NULL,
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создаём таблицу для связи компьютеров и кабинетов
CREATE TABLE computer_locations (
    id SERIAL PRIMARY KEY,
    computer_id INTEGER REFERENCES computers(id) ON DELETE CASCADE,
    office_id INTEGER REFERENCES offices(id) ON DELETE CASCADE,
    placed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

