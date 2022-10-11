package DAOpsql;

import DAO.OvchipkaartDAO;
import DAO.ReizigerDAO;
import Domein.Ovchipkaart;
import Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OvchipkaartDAOpsql implements OvchipkaartDAO {

    Connection connection;

    ReizigerDAO rdao;

    public OvchipkaartDAOpsql(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean save(Ovchipkaart ovchipkaart) {
        int kaartnummer = ovchipkaart.getKaartNummer();
        java.util.Date javaDateGeldigTot = ovchipkaart.getGeldigTot();
        java.sql.Date sqlDate = new java.sql.Date(javaDateGeldigTot.getTime());
        int klasse = ovchipkaart.getKlasse();
        double saldo = ovchipkaart.getSaldo();
        int reizigersId = ovchipkaart.getReizigersId();

        String query = "INSERT INTO ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1,kaartnummer);
            pst.setDate(2,sqlDate);
            pst.setInt(3,klasse);
            pst.setDouble(4,saldo);
            pst.setInt(5,reizigersId);

            ResultSet rs = pst.executeQuery();
            pst.close();
            rs.close();
        }catch (SQLException ex){
            System.err.println("error "+ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Ovchipkaart ovchipkaart) {
        try{
            String query = "UPDATE ov_chipkaart SET kaart_nummer=?, geldig_tot=?,"+
                    " klasse=?, saldo=?, reiziger_id=? WHERE kaart_nummer=?";
            PreparedStatement pst = connection.prepareStatement(query);
            java.util.Date javaDateGeldigTot = ovchipkaart.getGeldigTot();
            java.sql.Date sqlDate = new java.sql.Date(javaDateGeldigTot.getTime());
            pst.setInt(1,ovchipkaart.getKaartNummer());
            pst.setDate(2,sqlDate);
            pst.setInt(3, ovchipkaart.getKlasse());
            pst.setDouble(4, ovchipkaart.getSaldo());
            pst.setInt(5,ovchipkaart.getReizigersId());
            pst.setInt(6,ovchipkaart.getKaartNummer());

            ResultSet rs = pst.executeQuery();
            pst.close();
            rs.close();
        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Ovchipkaart ovchipkaart) {
        try{

            String query = "DELETE FROM ov_chipkaart WHERE kaart_nummer=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1,ovchipkaart.getKaartNummer());
            ResultSet rs = pst.executeQuery();
            pst.close();
            rs.close();

        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }

        return true;
    }

    @Override
    public List<Ovchipkaart> findByReiziger(Reiziger reiziger) {
        List<Ovchipkaart> kaarten = new ArrayList<>();
        try {
            String query = "select * from ov_chipkaart where reiziger_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1,reiziger.getId());
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                int kaartnummer = rs.getInt("kaart_nummer");
                Date geldig_tot = rs.getDate("geldig_tot");
                int klasse  = rs.getInt("klasse");
                double saldo = rs.getDouble("saldo");

                Ovchipkaart ovchipkaart = new Ovchipkaart(kaartnummer,geldig_tot,klasse,saldo,reiziger.getId());
                kaarten.add(ovchipkaart);


            }


        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }
        return kaarten;
    }

    @Override
    public List<Ovchipkaart> findAll(){
        List<Ovchipkaart> kaarten = new ArrayList<>();
        try {
            String query = "select * from ov_chipkaart";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                int kaartnummer = rs.getInt("kaart_nummer");
                Date geldig_tot = rs.getDate("geldig_tot");
                int klasse  = rs.getInt("klasse");
                double saldo = rs.getDouble("saldo");
                int reizigerId = rs.getInt("reiziger_id");

                Ovchipkaart ovchipkaart = new Ovchipkaart(kaartnummer,geldig_tot,klasse,saldo,reizigerId);
                kaarten.add(ovchipkaart);


            }


        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }
        return kaarten;
    }
}
