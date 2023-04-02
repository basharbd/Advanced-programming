package function;
import data.RaavareBatchDTO;
import exception.DALException;
import interfaces.IRaavareBatchDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaavareBatchDAO implements IRaavareBatchDAO {

    private Connection connection = null;
    private List<RaavareBatchDTO> batch = new ArrayList<>();

    public RaavareBatchDAO() {
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
    public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
        String sql = "SELECT * FROM itembatch WHERE BatchID="+rbId;

        RaavareBatchDTO rb = new RaavareBatchDTO();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                rb.setRbId(rs.getInt(1));
                rb.setRaavareId(rs.getInt(2));
                rb.setMaengde(rs.getDouble(3));
                rb.setLeverandoer(rs.getString(4));
            } else {
                throw new DALException("Ingen råvarebatch med denne ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rb;
    }

    @Override
    public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
        batch = new ArrayList<>();

        String sql = "SELECT * FROM itembatch";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                RaavareBatchDTO rb = new RaavareBatchDTO();
                rb.setRbId(rs.getInt(1));
                rb.setRaavareId(rs.getInt(2));
                rb.setMaengde(rs.getDouble(3));
                rb.setLeverandoer(rs.getString(4));
                batch.add(rb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(batch.isEmpty()) {
            throw new DALException("Ingen råvarebatch");
        }
        return batch;
    }

    @Override
    public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
        return null;
    }

    @Override
    public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
        String sql = "INSERT INTO itembatch VALUES(?,?,?,?)";

        for (RaavareBatchDTO rb: getRaavareBatchList()) {
            if(rb.getRbId() == raavarebatch.getRbId()) {
                throw new DALException("Allerede en råvarebatch med denne ID");
            }
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, raavarebatch.getRbId());
            preparedStatement.setInt(2, raavarebatch.getRaavareId());
            preparedStatement.setDouble(3, raavarebatch.getMaengde());
            preparedStatement.setString(4, raavarebatch.getLeverandoer());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
        RaavareBatchDTO rb = getRaavareBatch(raavarebatch.getRbId());

        if(rb == null) {
            throw new DALException("Ingen råvarebatch med denne ID");
        }

        String sql = "UPDATE itembatch SET ItemID = ?, Amount = ?, Supplier = ? WHERE BatchID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, raavarebatch.getRaavareId());
            preparedStatement.setDouble(2, raavarebatch.getMaengde());
            preparedStatement.setString(3, raavarebatch.getLeverandoer());
            preparedStatement.setInt(4, raavarebatch.getRbId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
