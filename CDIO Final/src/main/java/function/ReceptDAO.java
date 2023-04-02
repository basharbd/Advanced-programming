package function;

import data.ReceptDTO;
import exception.DALException;
import interfaces.IReceptDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceptDAO implements IReceptDAO {

    private Connection connection = null;
    private List<ReceptDTO> recepter = new ArrayList<>();

    public ReceptDAO() {
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
    public ReceptDTO getRecept(int receptId) throws DALException {
        String sql = "SELECT * FROM recipe WHERE RecipeID="+receptId;

        ReceptDTO recept = new ReceptDTO();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                recept.setReceptId(rs.getInt(1));
                recept.setReceptNavn(rs.getString(2));
            } else {
                throw new DALException("Ingen recepter med denne ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recept;
    }

    @Override
    public List<ReceptDTO> getReceptList() throws DALException {
        recepter = new ArrayList<>();

        String sql = "SELECT * FROM recipe";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ReceptDTO recept = new ReceptDTO();
                recept.setReceptId(rs.getInt(1));
                recept.setReceptNavn(rs.getString(2));
                recepter.add(recept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(recepter.isEmpty()) {
            throw new DALException("Ingen recepter");
        }
        return recepter;
    }

    @Override
    public void createRecept(ReceptDTO recept) throws DALException {
        String sql = "INSERT INTO recipe VALUES(?,?)";

        for (ReceptDTO r: getReceptList()) {
            if(r.getReceptId() == recept.getReceptId()) {
                throw new DALException("Allerede en recept med denne ID");
            }
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, recept.getReceptId());
            preparedStatement.setString(2, recept.getReceptNavn());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
           e.printStackTrace();
        }

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws DALException {
        ReceptDTO r = getRecept(recept.getReceptId());

        if(r == null) {
            throw new DALException("Ingen recepter med denne ID");
        }

        String sql = "UPDATE recipe SET RecipeName = ? WHERE RecipeID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recept.getReceptNavn());
            preparedStatement.setInt(2, recept.getReceptId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
