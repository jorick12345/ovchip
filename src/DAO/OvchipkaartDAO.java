package DAO;

import Domein.Ovchipkaart;
import Domein.Reiziger;

import java.util.List;

public interface OvchipkaartDAO {

    boolean save(Ovchipkaart ovchipkaart);
    boolean update(Ovchipkaart ovchipkaart);
    boolean delete(Ovchipkaart ovchipkaart);
    List<Ovchipkaart> findByReiziger(Reiziger reiziger);

    List<Ovchipkaart> findAll();
}
