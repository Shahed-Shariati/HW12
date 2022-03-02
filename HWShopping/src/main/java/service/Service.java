package service;

import java.util.List;

public interface Service<T> {

    T find(int id);
    List<T> findAll();
    void upDate(T t);
    void delete(int id);

}
