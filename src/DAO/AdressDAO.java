package DAO;

import Domein.Adress;
import Domein.Reiziger;

import java.util.List;

public interface AdressDAO {

    boolean save(Adress adress);
    boolean update(Adress adress);
    boolean delete(Adress adress);
    Adress findByReiziger(Reiziger reiziger);
    List<Adress> findAll();
}
