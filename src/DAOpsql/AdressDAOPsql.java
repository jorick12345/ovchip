package DAOpsql;

import DAO.AdressDAO;
import DAO.ReizigerDAO;
import Domein.Adress;
import Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdressDAOPsql implements AdressDAO {

    Connection connection;

    ReizigerDAO rdao;

    public AdressDAOPsql(Connection connection){
        this.connection = connection;
    }
    @Override
    public boolean save(Adress adress) {
        int id = adress.getId();
        String postcode = adress.getPostcode();
        String huisnummer = adress.getHuisnummer();
        String straat = adress.getStraat();
        String woonplaats = adress.getWoonplaats();
        int reizigerId = adress.getReizigerId();
        String query = "INSERT INTO adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1,id);
            pst.setString(2,postcode);
            pst.setString(3,huisnummer);
            pst.setString(4,straat);
            pst.setString(5,woonplaats);
            pst.setInt(6,reizigerId);

            ResultSet rs = pst.executeQuery();
            pst.close();
            rs.close();
        }catch (SQLException ex){
            System.err.println("error "+ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Adress adress) {
        try{
            String query = "UPDATE adres SET adres_id=?, postcode=?,"+
                    " huisnummer=?, straat=?, woonplaats=?, reiziger_id=?  WHERE adres_id=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1,adress.getId());
            pst.setString(2,adress.getPostcode());
            pst.setString(3, adress.getHuisnummer());
            pst.setString(4, adress.getStraat());
            pst.setString(5,adress.getWoonplaats());
            pst.setInt(6,adress.getReizigerId());
            pst.setInt(7,adress.getId());

            ResultSet rs = pst.executeQuery();
            pst.close();
            rs.close();
        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Adress adress) {
        try{

            String query = "DELETE FROM adres WHERE adres_id=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1,adress.getId());

            ResultSet rs = pst.executeQuery();
            pst.close();
            rs.close();

        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }

        return true;
    }

    @Override
    public Adress findByReiziger(Reiziger reiziger) {

        return null;
    }

    @Override
    public List<Adress> findAll() {
        try {


            Statement st = connection.createStatement();


            String query = "SELECT ALL adres_id,postcode,huisnummer,straat,woonplaats,reiziger_id FROM adres";

            ResultSet rs = st.executeQuery(query);

            List<Adress> adresses = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("adres_id");
                String postcode = rs.getString("postcode");
                String straat = rs.getString("straat");
                String woonplaats = rs.getString("woonplaats");
                String huisnummer = rs.getString("huisnummer");
                int rId = rs.getInt("reiziger_id");

                Adress adress = new Adress(id,postcode,huisnummer,straat,woonplaats,rId);
                adresses.add(adress);

                }



            st.close();
            rs.close();
            return adresses;

        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }
        return null;
    }

}
