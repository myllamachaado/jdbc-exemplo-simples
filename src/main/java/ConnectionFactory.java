import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {

        Connection conn = null;

        try(InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")){
            Properties prop = new Properties();
            prop.load(input);

            String driver = prop.getProperty("jdbc.driver");
            String address = prop.getProperty("db.address");
            String port = prop.getProperty("db.port");
            String db_name = prop.getProperty("db.name");
            String timezone_info = prop.getProperty("db.timezone.info");
            String ssl = prop.getProperty("db.ssl");
            String usr = prop.getProperty("db.user.login");
            String psw = prop.getProperty("db.user.password");

            StringBuilder urlConnection = new StringBuilder("jdbc")
                    .append(":").append(driver)
                    .append("://").append(address)
                    .append(":").append(port)
                    .append("/").append(db_name)
                    .append("?").append(timezone_info)
                    .append("&").append(ssl);

            try{
                conn = DriverManager.getConnection(urlConnection.toString(), usr, psw);
                System.out.println("Connexão realizada com sucesso!");
            }
            catch (SQLException e){
                System.out.println("Falha na conexão!");
                e.printStackTrace();
            }
        }
        catch (IOException e){
            System.out.println("Erro ao ler arquivo connection.properties.");
            e.printStackTrace();
        }
        return conn;
    }
}
