package ru.rienel.shs.mobile.activity;

public interface BaseView<T extends BasePresenter> {
	void setPresenter(T presenter);
}
