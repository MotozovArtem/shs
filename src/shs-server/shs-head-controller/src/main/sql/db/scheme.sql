-- Util tables
CREATE TABLE IF NOT EXISTS 'User'
(
	'id' INTEGER PRIMARY KEY
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
	'id'             INTEGER PRIMARY KEY,
	'serialNumber'   VARCHAR(100) NOT NULL,
	'costPerUnit'    DECIMAL(5, 2),
	'summary'        DECIMAL(8, 2),
	'person'         INTEGER,
	'lastIndication' INTEGER,
	FOREIGN KEY ('person') REFERENCES Person ('id'),
	FOREIGN KEY ('lastIndication') REFERENCES IndicationRecord ('id')
);

CREATE TABLE IF NOT EXISTS 'IndicationRecord'
(
	'id'         INTEGER PRIMARY KEY,
	'recordUuid' VARCHAR(60) UNIQUE NOT NULL,
	'value'      DECIMAL(10, 3),
	'recordDate' INTEGER,
	'delta'      DECIMAL(10, 3)
);

CREATE TABLE IF NOT EXISTS 'ResourceMeter'
(
	'id'           INTEGER PRIMARY KEY,
	'serialNumber' VARCHAR(32),
	'type'         VARCHAR(100),
	'verification' TEXT,
	'addedTime'    INTEGER
);

CREATE TABLE IF NOT EXISTS 'ResourceMeterNeighbors'
(
	'id'                      INTEGER PRIMARY KEY,
	'resourceMeterId'         INTEGER,
	'neighborResourceMeterId' INTEGER,
	FOREIGN KEY ('resourceMeterId') REFERENCES ResourceMeter ('id'),
	FOREIGN KEY ('neighborResourceMeterId') REFERENCES ResourceMeter ('id')
);