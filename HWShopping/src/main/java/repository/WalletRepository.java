package repository;

import model.Wallet;

import java.util.List;

public class WalletRepository implements Repository<Wallet>{
    @Override
    public int save(Wallet wallet) {
        return 0;
    }

    @Override
    public void upDate(Wallet wallet) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Wallet find(int id) {
        return null;
    }

    @Override
    public List<Wallet> findAll() {
        return null;
    }
}
