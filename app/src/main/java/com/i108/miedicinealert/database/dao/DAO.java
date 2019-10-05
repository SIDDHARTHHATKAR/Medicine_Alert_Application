package com.i108.miedicinealert.database.dao;

public interface DAO<T> {
	long insert(T type);
	void update(T type);
	void remove(long id);
	T get(long id);
	T[] getAll();
}
