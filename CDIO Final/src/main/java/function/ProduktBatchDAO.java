package function;

import data.ProduktBatchDTO;
import exception.DALException;
import interfaces.IProduktBatchDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchDAO implements IProduktBatchDAO {

    private Connection connection = null;
    private List<ProduktBatchDTO> pbatch = new ArrayList<>();


    public ProduktBatchDAO() {
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
    public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
        String sql = "SELECT * FROM productbatch WHERE ProductBatchID="+pbId;

        ProduktBatchDTO batch = new ProduktBatchDTO();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                batch.setPbId(rs.getInt(1));
                batch.setReceptId(rs.getInt(2));
                batch.setStatus(rs.getInt(3));
            } else {
                throw new DALException("Ingen produkt batches med denne ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batch;
    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
        pbatch = new ArrayList<>();

        String sql = "SELECT * FROM productbatch";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ProduktBatchDTO batch = new ProduktBatchDTO();
                batch.setPbId(rs.getInt(1));
                batch.setReceptId(rs.getInt(2));
                batch.setStatus(rs.getInt(3));
                pbatch.add(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(pbatch.isEmpty()) {
            throw new DALException("Ingen produkt batches");
        }
        return pbatch;
    }

    @Override
    public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
        String sql = "INSERT INTO productbatch VALUES(?,?,?)";

        for (ProduktBatchDTO batch: getProduktBatchList()) {
            if(batch.getPbId() == produktbatch.getPbId()) {
                throw new DALException("Allerede en produkt batch med denne ID");
            }
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, produktbatch.getPbId());
            preparedStatement.setInt(2, produktbatch.getReceptId());
            preparedStatement.setInt(3, produktbatch.getStatus());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
        ProduktBatchDTO batch = getProduktBatch(produktbatch.getPbId());

        if(batch == null) {
            throw new DALException("Ingen product batches med denne ID");
        }

        String sql = "UPDATE productbatch SET BatchStatus = ? WHERE ProductBatchID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, produktbatch.getReceptId());
            preparedStatement.setInt(1, produktbatch.getStatus());
            preparedStatement.setInt(2, produktbatch.getPbId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
