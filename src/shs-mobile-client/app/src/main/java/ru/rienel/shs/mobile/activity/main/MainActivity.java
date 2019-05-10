package ru.rienel.shs.mobile.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.config.AppConfig;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		TextView view = findViewById(R.id.main_activity_text);
		AppConfig config = new AppConfig(this);

		String endpoint = config.getHeadControllerEndpoint();
		if (endpoint != null) {
			view.setText(endpoint);
		} else {
			view.setText(R.string.app_name);
		}
	}
}
