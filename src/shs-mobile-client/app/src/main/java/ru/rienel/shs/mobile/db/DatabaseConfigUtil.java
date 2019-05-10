package ru.rienel.shs.mobile.db;

import java.io.File;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import ru.rienel.shs.mobile.domain.IndicationRecord;
import ru.rienel.shs.mobile.domain.Person;
import ru.rienel.shs.mobile.domain.ResourceBill;
import ru.rienel.shs.mobile.domain.ResourceMeter;
import ru.rienel.shs.mobile.domain.User;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	private static final Class<?>[] classes = new Class[]{
			User.class,
			Person.class,
			ResourceBill.class,
			ResourceMeter.class,
			IndicationRecord.class
	};

	public static void main(String[] args) throws Exception {
		writeConfigFile(new File("src/main/res/raw/ormlite_config.txt"), classes);
	}
}
