package DAOpsql;

import DAO.ReizigerDAO;
import Domein.Adress;
import Domein.Reiziger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    Connection connection;

    public ReizigerDAOPsql(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        int id = reiziger.getId();
        String voorletters = reiziger.getVoorletters();
        String tussenvoegsel = reiziger.getTussenvoegsel();
        String achternaam = reiziger.getAchternaam();
        Date geboortedatum = reiziger.getGeboortedatum();
        java.sql.Date sqlDate = new java.sql.Date(geboortedatum.getTime());
        String query = "INSERT INTO reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1,id);
            pst.setString(2,voorletters);
            pst.setString(3,tussenvoegsel);
            pst.setString(4,achternaam);
            pst.setDate(5,sqlDate);
            ResultSet rs = pst.executeQuery();
            pst.close();
            rs.close();
        }catch (SQLException ex){
            System.err.println("error "+ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try{
                    String query = "UPDATE reiziger SET reiziger_id=?, voorletters=?,"+
                            " tussenvoegsel=?, achternaam=?, geboortedatum=?  WHERE reiziger_id=?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setInt(1,reiziger.getId());
                    pst.setString(2,reiziger.getVoorletters());
                    pst.setString(3, reiziger.getTussenvoegsel());
                    pst.setString(4, reiziger.getAchternaam());

                    java.sql.Date sqlDate = new java.sql.Date(reiziger.getGeboortedatum().getTime());
                    pst.setDate(5,sqlDate);
                    pst.setInt(6,reiziger.getId());

                    ResultSet rs = pst.executeQuery();
                    pst.close();
                    rs.close();
        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try{

            String query = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1,reiziger.getId());

            ResultSet rs = pst.executeQuery();
            pst.close();
            rs.close();

        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }

        return true;
    }

    @Override
    public Reiziger findById(int id) {
        for(Reiziger r:findAll()){
            if(r.getId()==id){
                return r;
            }
        }


        return null;
    }

    @Override
    public List<Reiziger> findByDate(String datum) {
        try{
        List lijst = new ArrayList();
        for(Reiziger r:findAll()){
            if(r.getGeboortedatum().equals(datum)){
                lijst.add(r);
            }
        }
        return lijst;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {

        try {


            Statement st = connection.createStatement();

            String query = "SELECT ALL reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum FROM reiziger";


            ResultSet rs = st.executeQuery(query);

            List<Reiziger> reizigers = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                if (tussenvoegsel==null) {
                    tussenvoegsel = "";
                }
                String achternaam = rs.getString("achternaam");
                Date geboortedatum = rs.getDate("geboortedatum");



                Reiziger reiziger = new Reiziger(id,voorletters,tussenvoegsel,achternaam,geboortedatum);

                String query1 = "SELECT adres_id,postcode,huisnummer,straat,woonplaats FROM adres WHERE reiziger_id=?";
                PreparedStatement pst = connection.prepareStatement(query1);
                pst.setInt(1,id);

                ResultSet rs2 = pst.executeQuery();
                if(rs2.next()){
                int id2 = rs2.getInt("adres_id");
                String postcode = rs2.getString("postcode");
                String huisnummer = rs2.getString("huisnummer");
                String straat = rs2.getString("straat");
                String woonplaats = rs2.getString("woonplaats");

                Adress adress = new Adress(id2,postcode,huisnummer,straat,woonplaats,id);

                reiziger.setAdress(adress);

                reizigers.add(reiziger);
                }else {
                    reizigers.add(reiziger);
                }

            }

            st.close();
            rs.close();
            return reizigers;

        }catch (SQLException ex){
            System.err.println("error"+ex.getMessage());
        }
        return null;
    }
}
