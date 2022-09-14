package DAO;

import Domein.Reiziger;

import java.util.List;

public interface ReizigerDAO {

    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findById(int id);
    List<Reiziger> findByDate(String datum);
    List<Reiziger> findAll();
}
