package repository;

import java.util.List;

public interface Repository<T> {
    int save(T t);
    void upDate(T t);
    void delete(int id);
    T find(int id);
    List<T> findAll();
}
