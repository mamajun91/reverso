package DAO;

import ReversoException.Dbexception;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnexionDAO {

    private static final Connection connection;
    static {
        try {
            Properties dataProperties = new Properties();
            File db = new File("db.properties");
            FileInputStream input = new FileInputStream(db);
            dataProperties.load(input);
            connection = DriverManager.getConnection(
                    dataProperties.getProperty("url"),
                    dataProperties.getProperty("login"),
                    dataProperties.getProperty("password")
            );
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnexionDAO(){}
    
    public Connection getConnection() throws Dbexception {
        if (connection == null){
            throw  new Dbexception("Vérifiez les données de connexion!");
        } else {
            return connection;
        }
    }
}
