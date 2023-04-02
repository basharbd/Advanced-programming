package function;
import data.BrugerDTO;
import exception.DALException;
import interfaces.IBrugerDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrugerDAO implements IBrugerDAO {

    private Connection connection = null;
    private List<BrugerDTO> dto = new ArrayList<>();

    public BrugerDAO() {
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
    public BrugerDTO getBruger(int oprId) throws DALException {

        String sql = "SELECT * FROM user WHERE UserID="+oprId;

        BrugerDTO opr = new BrugerDTO();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                opr.setOprId(rs.getInt(1));
                opr.setOprNavn(rs.getString(2));
                opr.setIni(rs.getString(3));
                opr.setCpr(rs.getString(4));
                opr.setRolle(rs.getString(5));
                opr.setStatus(rs.getString(6));
            } else {
                throw new DALException("Ingen bruger med denne ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opr;
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws DALException {

        dto = new ArrayList<>();

        String sql = "SELECT * FROM user";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BrugerDTO opr = new BrugerDTO();
                opr.setOprId(rs.getInt(1));
                opr.setOprNavn(rs.getString(2));
                opr.setIni(rs.getString(3));
                opr.setCpr(rs.getString(4));
                opr.setRolle(rs.getString(5));
                opr.setStatus(rs.getString(6));
                dto.add(opr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(dto.isEmpty()) {
            throw new DALException("Ingen brugere");
        }
        return dto;
    }

    @Override
    public void createBruger(BrugerDTO opr) throws DALException {
        String sql = "INSERT INTO user VALUES(?,?,?,?,?,?)";

        for (BrugerDTO b: getBrugerList()) {
            if(b.getOprId() == opr.getOprId()) {
                throw new DALException("Allerede en bruger med denne ID");
            }
        }
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, opr.getOprId());
            preparedStatement.setString(2, opr.getOprNavn());
            preparedStatement.setString(3, opr.getIni());
            preparedStatement.setString(4, opr.getCpr());
            preparedStatement.setString(5, opr.getRolle());
            preparedStatement.setString(6, opr.getStatus());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBruger(BrugerDTO opr) throws DALException {
        BrugerDTO b = getBruger(opr.getOprId());

        if(b == null) {
            throw new DALException("Ingen bruger med denne ID");
        }

        String sql = "UPDATE user SET UserName = ?, UserIni = ?, UserCPR = ?, UserRole = ?, Status = ? WHERE UserID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, opr.getOprNavn());
            preparedStatement.setString(2, opr.getIni());
            preparedStatement.setString(3, opr.getCpr());
            preparedStatement.setString(4, opr.getRolle());
            preparedStatement.setString(5, opr.getStatus());
            preparedStatement.setInt(6, opr.getOprId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BrugerDTO findCpr(String cpr) {
        String sql = "SELECT * FROM user WHERE UserCPR="+cpr;

        BrugerDTO opr = new BrugerDTO();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                opr.setOprId(rs.getInt(1));
                opr.setOprNavn(rs.getString(2));
                opr.setIni(rs.getString(3));
                opr.setCpr(rs.getString(4));
                opr.setRolle(rs.getString(5));
                opr.setStatus(rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return opr;
    }
}

