package DAOpsql;

import DAO.ReizigerDAO;
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
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        return false;
    }

    @Override
    public Reiziger findById(int id) {
        return null;
    }

    @Override
    public List<Reiziger> findByDate(String datum) {
        return null;
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

                reizigers.add(reiziger);
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
