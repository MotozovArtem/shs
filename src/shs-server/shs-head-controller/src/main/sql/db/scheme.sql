-- Util tables
CREATE TABLE IF NOT EXISTS 'User'
(
	'id'       INTEGER PRIMARY KEY,
	'username' VARCHAR(100) UNIQUE NOT NULL,
	'password' VARCHAR(100)        NOT NULL,
	'person'   INTEGER             NOT NULL
);

-- Domain tables
CREATE TABLE IF NOT EXISTS 'Person'
(
	'id'         INTEGER PRIMARY KEY,
	'name'       VARCHAR(100),
	'surname'    VARCHAR(100),
	'patronymic' VARCHAR(100),
	'address'    TEXT
);

CREATE TABLE IF NOT EXISTS 'ResourceBill'
(
	'id'              INTEGER PRIMARY KEY,
	'serial_mumber'   VARCHAR(100) NOT NULL,
	'cost_per_unit'   DECIMAL(5, 2),
	'summary'         DECIMAL(8, 2),
	'person'          INTEGER,
	'last_indication' INTEGER,
	FOREIGN KEY ('person') REFERENCES Person ('id'),
	FOREIGN KEY ('last_indication') REFERENCES IndicationRecord ('id')
);

CREATE TABLE IF NOT EXISTS 'IndicationRecord'
(
	'id'          INTEGER PRIMARY KEY,
	'record_uuid' VARCHAR(60) UNIQUE NOT NULL,
	'value'       DECIMAL(10, 3),
	'record_date' INTEGER,
	'delta'       DECIMAL(10, 3)
);

CREATE TABLE IF NOT EXISTS 'ResourceMeter'
(
	'id'            INTEGER PRIMARY KEY,
	'serial_number' VARCHAR(32),
	'type'          VARCHAR(100),
	'verification'  TEXT,
	'addedTime'     INTEGER
);

CREATE TABLE IF NOT EXISTS 'ResourceMeterNeighbors'
(
	'id'                         INTEGER PRIMARY KEY,
	'resource_meter_id'          INTEGER,
	'neighbor_resource_meter_id' INTEGER,
	FOREIGN KEY ('resource_meter_id') REFERENCES ResourceMeter ('id'),
	FOREIGN KEY ('neighbor_resource_meter_id') REFERENCES ResourceMeter ('id')
);