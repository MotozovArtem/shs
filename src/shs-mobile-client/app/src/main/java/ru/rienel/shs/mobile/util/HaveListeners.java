package ru.rienel.shs.mobile.util;

import java.util.EventListener;

public interface HaveListeners {
	void addListener(EventListener listener);

	void removeListener(EventListener listener);
}
