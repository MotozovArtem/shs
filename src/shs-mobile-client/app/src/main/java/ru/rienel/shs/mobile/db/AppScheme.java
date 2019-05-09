package ru.rienel.shs.mobile.db;

public class AppScheme {

	public static final class UserTable {
		public static final String NAME = "User";

		public static final class Columns {
			public static final String ID = "id";
			public static final String USERNAME = "username";
			public static final String PASSWORD = "password";
			public static final String PERSON = "person";
		}

		public static String[] allColumns() {
			return new String[]{
					Columns.ID,
					Columns.USERNAME,
					Columns.PASSWORD,
					Columns.PERSON
			};
		}
	}

	public static final class PersonTable {
		public static final String NAME = "Person";

		public static final class Columns {
			public static final String ID = "id";
			public static final String NAME = "name";
			public static final String SURNAME = "surname";
			public static final String PATRONYMIC = "patronymic";
			public static final String ADDRESS = "address";
		}

		public static String[] allColumns() {
			return new String[]{
					Columns.ID,
					Columns.NAME,
					Columns.SURNAME,
					Columns.PATRONYMIC,
					Columns.ADDRESS
			};
		}
	}

	public static final class ResourceBillTable {
		public static final String NAME = "ResourceBill";

		public static final class Columns {
			public static final String ID = "id";
			public static final String SERIAL_NUMBER = "serial_number";
			public static final String COST_PER_UNIT = "cost_per_unit";
			public static final String SUMMARY = "summary";
			public static final String PERSON = "person";
			public static final String LAST_INDICATION = "last_indication";
		}

		public static String[] allColumns() {
			return new String[]{
					Columns.ID,
					Columns.SERIAL_NUMBER,
					Columns.COST_PER_UNIT,
					Columns.SUMMARY,
					Columns.PERSON,
					Columns.LAST_INDICATION
			};
		}
	}

	public static final class ResourceMeterTable {
		public static final String NAME = "ResourceMeter";

		public static final class Columns {
			public static final String ID = "id";
			public static final String SERIAL_NUMBER = "serial_number";
			public static final String TYPE = "type";
			public static final String VERIFICATION = "verification";
			public static final String ADDED_TIME = "added_time";
		}

		public static String[] allColumns() {
			return new String[]{
					Columns.ID,
					Columns.SERIAL_NUMBER,
					Columns.TYPE,
					Columns.VERIFICATION,
					Columns.ADDED_TIME
			};
		}
	}

	public static final class IndicationRecordTable {
		public static final String NAME = "IndicationRecordT";

		public static final class Columns {
			public static final String ID = "id";
			public static final String RECORD_UUID = "record_uuid";
			public static final String VALUE = "value";
			public static final String RECORD_DATE = "record_date";
			public static final String DELTA = "delta";
		}

		public static String[] allColumns() {
			return new String[]{
					Columns.ID,
					Columns.RECORD_UUID,
					Columns.VALUE,
					Columns.RECORD_DATE,
					Columns.DELTA
			};
		}
	}
}
