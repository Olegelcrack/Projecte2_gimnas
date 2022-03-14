
package gimnas;

import java.sql.*;

public class Activitat {
    private String nom_activitat;
    private String descripcio;
    private double durada;
    private String hora_inici;
    private int num_sala;
    
    public void VisualitzarActivitat() throws SQLException{
        Connexio_bd con = new Connexio_bd();
        Connection connexio = con.getConBD();

        String consulta = "SELECT * FROM ACTIVITATS WHERE DIA = CURDATE()";
        PreparedStatement ps = connexio.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            System.out.println(rs.getString("NOM_ACT"));
            System.out.println(rs.getString("DESCRIPCIO"));
            System.out.println(rs.getString("DURADA"));
            System.out.println(rs.getString("HORA_INICI"));
            System.out.println(rs.getInt("NUM_SALA"));
        }
    }
}
