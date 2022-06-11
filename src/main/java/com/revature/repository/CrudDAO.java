package com.revature.repository;

import java.util.List;

public interface CrudDAO<E> {
    E create(E e);
    List<E> getAll();
    E getById(int id);
    E update(E e);
    boolean deleteById(int id);
    int count();
}
