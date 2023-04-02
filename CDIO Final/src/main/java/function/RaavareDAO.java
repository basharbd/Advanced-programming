package function;
import data.RaavareDTO;
import exception.DALException;
import interfaces.IRaavareDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaavareDAO implements IRaavareDAO {

    Connection connection = null;
    private List<RaavareDTO> raavareList = new ArrayList<>();


    public RaavareDAO() {
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
    public RaavareDTO getRaavare(int raavareId) throws DALException {
        String sql = "SELECT * FROM item WHERE ItemID="+raavareId;

        RaavareDTO raavare = new RaavareDTO();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                raavare.setRaavareId(rs.getInt(1));
                raavare.setRaavareNavn(rs.getString(2));
            } else {
                throw new DALException("Ingen råvarer med denne ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raavare;
    }

    @Override
    public List<RaavareDTO> getRaavareList() throws DALException {
        raavareList = new ArrayList<>();

        String sql = "SELECT * FROM item";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                RaavareDTO raavare = new RaavareDTO();
                raavare.setRaavareId(rs.getInt(1));
                raavare.setRaavareNavn(rs.getString(2));
                raavareList.add(raavare);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(raavareList.isEmpty()) {
            throw new DALException("Ingen brugere");
        }
        return raavareList;
    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws DALException {
        String sql = "INSERT INTO item VALUES(?,?)";

        for (RaavareDTO r: getRaavareList()) {
            if(r.getRaavareId() == raavare.getRaavareId()) {
                throw new DALException("Allerede en råvare med denne ID");
            }
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, raavare.getRaavareId());
            preparedStatement.setString(2, raavare.getRaavareNavn());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException {
        RaavareDTO r = getRaavare(raavare.getRaavareId());

        if(r == null) {
            throw new DALException("Ingen bruger med denne ID");
        }

        String sql = "UPDATE item SET ItemName = ? WHERE ItemID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, raavare.getRaavareNavn());
            preparedStatement.setInt(2, raavare.getRaavareId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> visRaavareNavn(int raavareId) {
        ArrayList<String> array = new ArrayList<>();

        String sql = "SELECT itemName FROM item WHERE ItemID = " + raavareId;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                array.add(rs.getString("itemName"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return array;
    }
}
