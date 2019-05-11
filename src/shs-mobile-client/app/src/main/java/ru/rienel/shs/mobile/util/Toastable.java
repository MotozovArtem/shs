package ru.rienel.shs.mobile.util;

public interface Toastable {
	void makeShortToast(int stringResource);

	void makeLongToast(int stringResource);

	@Deprecated
	void makeShortToastWithText(String message);
}
