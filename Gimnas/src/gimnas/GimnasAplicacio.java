
package gimnas;

import java.sql.SQLException;

public class GimnasAplicacio {
    
    public static void main(String[] args) throws SQLException {
        
        Connexio_bd con = new Connexio_bd();
        //  Cridem la funcio de conectar-se a base de dades
        con.getConBD();
        
        Gimnas gym = new Gimnas();
        
        gym.gestionarGimnas();
        
        //  Desconnectem de la base de dades
        //con.DesConnexio();
    }
    
}
