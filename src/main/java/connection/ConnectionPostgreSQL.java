package connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Slf4j
public class ConnectionPostgreSQL {

    private static final String URL = "jdbc:postgresql://localhost:5432/Agenda";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão realizada com sucesso!");
        }catch(SQLException ex){
            System.out.println("Erro ao tentar conecar ao BD!" + ex.getMessage());
        }

        return connection;
    }
}
