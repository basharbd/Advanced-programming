package test;
import data.BrugerDTO;
import exception.DALException;
import function.BrugerDAO;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class BrugerDAOTest {



    @Test
    void checkUserUpdate() throws DALException {

        BrugerDAO b = new BrugerDAO();
        BrugerDTO a = b.getBruger(15);

        String beforeNameChange = b.getBruger(15).getOprNavn();

        a.setOprNavn("Ændret navn");
        b.updateBruger(a);

        String afterNameChange = b.getBruger(15).getOprNavn();

        a.setOprNavn("Sid Mounib");
        b.updateBruger(a);

        assertNotEquals(beforeNameChange, afterNameChange);



    }


    @Test
        void checkUserInformation() throws DALException {
            BrugerDAO b = new BrugerDAO();
            assertEquals("Sid Mounib", b.getBruger(15).getOprNavn());

            assertEquals("SM", b.getBruger(15).getIni());

            assertEquals("260799-4321", b.getBruger(15).getCpr());

            assertEquals("Farmaceut", b.getBruger(15).getRolle());

            assertEquals("Aktiv", b.getBruger(15).getStatus());

    }

    @Test
    void checkUserCreation() throws DALException{

        BrugerDAO b = new BrugerDAO();
        BrugerDTO a = new BrugerDTO();

        boolean doesUserExist;
        boolean doesNewUserExist;

        try{
            System.out.println(b.getBruger(35).getOprNavn());
            doesUserExist = true;
        }catch(DALException e){

            doesUserExist = false;
        }

        a.setOprId(35);
        a.setOprNavn("TestSubject");
        a.setIni("TS");
        a.setCpr("123456-1234");
        a.setRolle("Farmaceut");
        a.setStatus("Aktiv");
        b.createBruger(a);

        try{
            b.getBruger(35).getOprNavn();
            doesNewUserExist = true;

        }catch(DALException e){
            doesNewUserExist = false;
        }

        assertNotEquals(doesUserExist,doesNewUserExist);


    }

}