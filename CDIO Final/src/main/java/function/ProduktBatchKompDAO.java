package function;

import data.ProduktBatchKompDTO;
import exception.DALException;
import interfaces.IProduktBatchKompDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchKompDAO implements IProduktBatchKompDAO {


    private Connection connection = null;
    private List<ProduktBatchKompDTO> productkomp = new ArrayList<>();

    public ProduktBatchKompDAO() {
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
    public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
        String sql = "SELECT * FROM productcomp WHERE ProductBatchID=" + pbId + " AND BatchID = " + rbId;

        ProduktBatchKompDTO pkomp = new ProduktBatchKompDTO();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                pkomp.setPbId(rs.getInt(1));
                pkomp.setRbId(rs.getInt(2));
                pkomp.setTara(rs.getDouble(3));
                pkomp.setNetto(rs.getDouble(4));
                pkomp.setOprId(rs.getInt(5));
            } else {
                throw new DALException("Ingen produkt komponenter med denne ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pkomp;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
        productkomp = new ArrayList<>();

        String sql = "SELECT * FROM productcomp WHERE ProductBatchID =" + pbId;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ProduktBatchKompDTO pkomp = new ProduktBatchKompDTO();
                pkomp.setPbId(rs.getInt(1));
                pkomp.setRbId(rs.getInt(2));
                pkomp.setTara(rs.getDouble(3));
                pkomp.setNetto(rs.getDouble(4));
                pkomp.setOprId(rs.getInt(5));
                productkomp.add(pkomp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (productkomp.isEmpty()) {
            throw new DALException("Ingen produkt komponenter");
        }
        return productkomp;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
        productkomp = new ArrayList<>();

        String sql = "SELECT * FROM productcomp";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ProduktBatchKompDTO pkomp = new ProduktBatchKompDTO();
                pkomp.setPbId(rs.getInt(1));
                pkomp.setRbId(rs.getInt(2));
                pkomp.setTara(rs.getDouble(3));
                pkomp.setNetto(rs.getDouble(4));
                pkomp.setOprId(rs.getInt(5));
                productkomp.add(pkomp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (productkomp.isEmpty()) {
            throw new DALException("Ingen produkt komponenter");
        }
        return productkomp;
    }

    @Override
    public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
        String sql = "INSERT INTO productcomp VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, produktbatchkomponent.getPbId());
            preparedStatement.setInt(2, produktbatchkomponent.getRbId());
            preparedStatement.setDouble(3, produktbatchkomponent.getTara());
            preparedStatement.setDouble(4, produktbatchkomponent.getNetto());
            preparedStatement.setInt(5, produktbatchkomponent.getOprId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
        ProduktBatchKompDTO pkomp = getProduktBatchKomp(produktbatchkomponent.getPbId(), produktbatchkomponent.getRbId());

        if (pkomp == null) {
            throw new DALException("Ingen recept komponenter med denne ID");
        }

        String sql = "UPDATE productcomp SET BatchID = ?, Tara = ?, Netto = ?, UserID = ? WHERE ProductBatchID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, produktbatchkomponent.getRbId());
            preparedStatement.setDouble(2, produktbatchkomponent.getTara());
            preparedStatement.setDouble(3, produktbatchkomponent.getNetto());
            preparedStatement.setInt(4, produktbatchkomponent.getOprId());
            preparedStatement.setInt(5, produktbatchkomponent.getPbId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
