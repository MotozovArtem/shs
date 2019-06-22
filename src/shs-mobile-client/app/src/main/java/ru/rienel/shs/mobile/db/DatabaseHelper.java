package ru.rienel.shs.mobile.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.domain.IndicationRecord;
import ru.rienel.shs.mobile.domain.Person;
import ru.rienel.shs.mobile.domain.ResourceBill;
import ru.rienel.shs.mobile.domain.ResourceMeter;
import ru.rienel.shs.mobile.domain.User;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String TAG = DatabaseHelper.class.getName();

	private static final String DATABASE_NAME = "storage.db";

	private static final int DATABASE_VERSION = 1;

	private Dao<User, Long> userDao;

	private Dao<Person, Long> personDao;

	private Dao<ResourceBill, Long> resourceBillDao;

	private Dao<ResourceMeter, Long> resourceMeterDao;

	private Dao<IndicationRecord, Long> indicationRecordDao;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, User.class);
			TableUtils.createTable(connectionSource, Person.class);
			TableUtils.createTable(connectionSource, ResourceBill.class);
			TableUtils.createTable(connectionSource, ResourceMeter.class);
			TableUtils.createTable(connectionSource, IndicationRecord.class);
		} catch (SQLException e) {
			Log.e(TAG, "Cannot create table", e);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, User.class, true);
			TableUtils.dropTable(connectionSource, Person.class, true);
			TableUtils.dropTable(connectionSource, ResourceBill.class, true);
			TableUtils.dropTable(connectionSource, ResourceMeter.class, true);
			TableUtils.dropTable(connectionSource, IndicationRecord.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			Log.e(TAG, "onUpgrade: cannot upgrade database", e);
		}
	}

	public Dao<User, Long> getUserDao() throws SQLException {
		if (userDao == null) {
			userDao = getDao(User.class);
		}
		return userDao;
	}

	public Dao<Person, Long> getPersonDao() throws SQLException {
		if (personDao == null) {
			personDao = getDao(Person.class);
		}
		return personDao;
	}

	public Dao<ResourceBill, Long> getResourceBillDao() throws SQLException {
		if (resourceBillDao == null) {
			resourceBillDao = getDao(ResourceBill.class);
		}
		return resourceBillDao;
	}

	public Dao<ResourceMeter, Long> getResourceMeterDao() throws SQLException {
		if (resourceMeterDao == null) {
			resourceMeterDao = getDao(ResourceMeter.class);
		}
		return resourceMeterDao;
	}

	public Dao<IndicationRecord, Long> getIndicationRecordDao() throws SQLException {
		if (indicationRecordDao == null) {
			indicationRecordDao = getDao(IndicationRecord.class);
		}
		return indicationRecordDao;
	}
}
