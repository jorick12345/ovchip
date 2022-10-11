
import DAO.AdressDAO;
import DAO.OvchipkaartDAO;
import DAO.ReizigerDAO;
import DAOpsql.AdressDAOPsql;
import DAOpsql.OvchipkaartDAOpsql;
import DAOpsql.ReizigerDAOPsql;
import Domein.Adress;
import Domein.Ovchipkaart;
import Domein.Reiziger;
import lib.Conn;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String [] args){


        Connection conn = Conn.getConnection();
        try {
            ReizigerDAO rdao = new ReizigerDAOPsql(conn);

            AdressDAO adao = new AdressDAOPsql(conn);
//            testReizigerDAO(rdao,adao);

            OvchipkaartDAO odao = new OvchipkaartDAOpsql(conn);
            testOvchipkaartDAO(odao);

            Conn.closeConnection(conn);

        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }






    }
    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao,AdressDAO adao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Adress adress1 = new Adress(6,"2266ca","65","hoi","amsterdam",6);
        Reiziger sietske = new Reiziger(6, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");

        rdao.save(sietske);
        adao.save(adress1);
        List<Reiziger> reizigers2 = rdao.findAll();
        System.out.println(reizigers2.size() + " reizigers\n");
//
        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        sietske.setVoorletters("M");
        System.out.println("[Test] update "+ rdao.update(sietske));
        System.out.println(reizigers.size() + " reizigers\n");

        List<Reiziger> reizigers3 = rdao.findAll();
        List<Adress> adresses = adao.findAll();
        for(Adress a: adresses){
            if(a.getReizigerId()==1){
                System.out.println("Test DELETE"+ adao.delete(a));

            }
        }
        for(Reiziger i: reizigers3){
            if(i.getId()==1){
                System.out.println("Test DELETE"+ rdao.delete(i));
                }
        }
    }

    private static void testOvchipkaartDAO(OvchipkaartDAO odao) throws SQLException{
        System.out.println("TEST ovchipkaart.save");
        String geldigTot = "2022-05-31";
        Ovchipkaart ovchipkaart = new Ovchipkaart(11112,java.sql.Date.valueOf(geldigTot),2,0.00,3);
        odao.save(ovchipkaart);
        System.out.println("TEST save geslaagd");
        List<Ovchipkaart> kaarten = odao.findAll();
        for(Ovchipkaart o:kaarten){
            System.out.println(o);
        }
        System.out.println();

        System.out.println("TEST ovchipkaart.update");

        Ovchipkaart ovchipkaart1 = new Ovchipkaart(35283,java.sql.Date.valueOf(geldigTot),2,5,2);

        odao.update(ovchipkaart1);
        System.out.println("Test update geslaagd");
        System.out.println("Test ovchipkaart.delete");
        odao.delete(new Ovchipkaart(11112));
        List<Ovchipkaart> kaarten2 = odao.findAll();
        for(Ovchipkaart o2:kaarten2){
            System.out.println(o2);
        }
        System.out.println();
        System.out.println("delete geslaagd");
        System.out.println();

        System.out.println("TEST findByReiziger");
        System.out.println(odao.findByReiziger(new Reiziger(2)));
        System.out.println("findByReiziger geslaagd");



    }
}
