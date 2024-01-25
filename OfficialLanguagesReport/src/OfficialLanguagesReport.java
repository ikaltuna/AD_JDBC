import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficialLanguagesReport {


		private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/world";
	    private static final String USERNAME = "birt";
	    private static final String PASSWORD = "birt";

	    public static void main(String[] args) {
	        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)) {
	        	mostrarEventos(connection);
	        } catch (SQLException e) {
	            System.err.println("Error al conectarse a la base de datos: " + e.getMessage());
	        }
	    }

	    private static void mostrarEventos(Connection connection) {
	    	
	    	
	    	System.out.println("Introduce el nombre del continente para ver sus idiomas oficiales:");
            String Continente = Consola.readString();
	    	
	    	System.out.println("Evento" + "\t\t\t\t"  + "| Asistentes | Ubicación" + "\t\t\t\t" + "| Direccion");
	    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
	        String paisesQuery = "Select name, Language, Percentage from country\r\n"
	        		+ "inner join countrylanguage on countrylanguage.countryCode = country.Code\r\n"
	        		+ "where IsOfficial=\"T\" and Continent = ?;";

	        try (PreparedStatement paisesStmt = connection.prepareStatement(paisesQuery)){
	        	paisesStmt.setString(1, Continente);
	        	try (ResultSet rs = paisesStmt.executeQuery()) {
	                while (rs.next()) {
	                	System.out.println("Pais Idiomaa Oficial Hablantes");
	                	System.out.println(rs.getString("name") + rs.getString("Language") + rs.getString("Percentage") + "%");
	                	
	            }
	        };
	        
	        } catch (SQLException e) {
	            System.err.println("Error al obtener datos: " + e.getMessage());
	        }
	    }

	

}
