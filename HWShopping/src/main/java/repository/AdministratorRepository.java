package repository;

import model.Administrator;

import java.util.List;

public class AdministratorRepository implements Repository<Administrator>{
    @Override
    public int save(Administrator administrator) {
        return 0;
    }

    @Override
    public void upDate(Administrator administrator) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Administrator find(int id) {
        return null;
    }

    @Override
    public List<Administrator> findAll() {
        return null;
    }
}
