package function;

import data.ReceptKompDTO;
import exception.DALException;
import interfaces.IReceptKompDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceptKompDAO implements IReceptKompDAO {

    private Connection connection = null;
    private List<ReceptKompDTO> receptKomp = new ArrayList<>();

    public ReceptKompDAO() {
        String url = "jdbc:mysql://cdiofinal.cfdiv8cdpgdk.us-east-2.rds.amazonaws.com:3306/medicinal";
        String username = "admin";
        String password = "final123";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
        String sql = "SELECT * FROM recipecomp WHERE RecipeID=" + receptId + " AND ItemID = " + raavareId;

        ReceptKompDTO komp = new ReceptKompDTO();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                komp.setReceptId(rs.getInt(1));
                komp.setRaavareId(rs.getInt(2));
                komp.setNonNetto(rs.getDouble(3));
                komp.setTolerance(rs.getDouble(4));
            } else {
                throw new DALException("Ingen receptkomponent med denne ID");
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return komp;
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
        receptKomp = new ArrayList<>();

        String sql = "SELECT * FROM recipecomp WHERE RecipeID =" + receptId;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ReceptKompDTO komp = new ReceptKompDTO();
                komp.setReceptId(rs.getInt(1));
                komp.setRaavareId(rs.getInt(2));
                komp.setNonNetto(rs.getDouble(3));
                komp.setTolerance(rs.getDouble(4));
                receptKomp.add(komp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (receptKomp.isEmpty()) {
            throw new DALException("Ingen recept komponenter");
        }
        return receptKomp;
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList() throws DALException {
        receptKomp = new ArrayList<>();

        String sql = "SELECT * FROM recipecomp";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ReceptKompDTO komp = new ReceptKompDTO();
                komp.setReceptId(rs.getInt(1));
                komp.setRaavareId(rs.getInt(2));
                komp.setNonNetto(rs.getDouble(3));
                komp.setTolerance(rs.getDouble(4));
                receptKomp.add(komp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (receptKomp.isEmpty()) {
            throw new DALException("Ingen recept komponenter");
        }
        return receptKomp;
    }

    @Override
    public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
        String sql = "INSERT INTO recipecomp VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, receptkomponent.getReceptId());
            preparedStatement.setInt(2, receptkomponent.getRaavareId());
            preparedStatement.setDouble(3, receptkomponent.getNonNetto());
            preparedStatement.setDouble(4, receptkomponent.getTolerance());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
        ReceptKompDTO r = getReceptKomp(receptkomponent.getReceptId(), receptkomponent.getRaavareId());

        if (r == null) {
            throw new DALException("Ingen recept komponenter med denne ID");
        }

        String sql = "UPDATE recipecomp SET RecipeID = ?, ItemID = ?, NonNet = ?, Tolerance = ? WHERE RecipeID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, receptkomponent.getReceptId());
            preparedStatement.setInt(2, receptkomponent.getRaavareId());
            preparedStatement.setDouble(3, receptkomponent.getNonNetto());
            preparedStatement.setDouble(4, receptkomponent.getTolerance());
            preparedStatement.setInt(5, receptkomponent.getReceptId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

