package ru.rienel.shs.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class PropertyReader {

	private static final String TAG = PropertyReader.class.getName();

	private Context context;

	private Properties properties;

	public PropertyReader(Context context) {
		this.context = context;
		properties = new Properties();
	}

	public Properties getProperties(String FileName) {
		try {
			AssetManager assetManager = context.getAssets();
			InputStream inputStream = assetManager.open(FileName);
			properties.load(inputStream);
		} catch (IOException e) {
			Log.e(TAG, "PropertyReader", e);
		}
		return properties;
	}
}
