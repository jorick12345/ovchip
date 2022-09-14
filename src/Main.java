
import DAO.ReizigerDAO;
import DAOpsql.ReizigerDAOPsql;
import Domein.Reiziger;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String [] args){


        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=postgres";
        try {
            Connection conn = DriverManager.getConnection(url);
            ReizigerDAO rdao = new ReizigerDAOPsql(conn);
            testReizigerDAO(rdao);

            conn.close();

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
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
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
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }
}