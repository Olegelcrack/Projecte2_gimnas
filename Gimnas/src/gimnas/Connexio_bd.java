
package gimnas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connexio_bd {
    
    private boolean connectat;
    private String user="root";
    private String pasw="";
    private String server="jdbc:mysql://localhost:3306/";
    private String bd="gim";
    private Connection conBD;

    public Connection getConBD() {
        return conBD;
    }
    
    public void conectar(){
        this.connectat=true;
    }
    
    public Connexio_bd(){
        
            try{
                conBD = DriverManager.getConnection(server + bd, user, pasw);
            }catch(SQLException ex){
                ex.printStackTrace();
            }
    }
    
    public void DesConnexio(){
        connectat=false;
    }
    
}
