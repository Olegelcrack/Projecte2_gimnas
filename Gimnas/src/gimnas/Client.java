
package gimnas;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    
    private DNI dni;
    private String nom;
    private String cognom;
    private String sexe;
    private LocalDate datanaixement;
    private Telefon telefon;
    private Email email;
    private CompteBancari comptebancari;
    private String usuari;
    private String contrasenya;
    private int publicitat;
    private int impediment_fisic;

    @Override
    public String toString() {
        return "Client{" + "dni=" + dni + ", nom=" + nom + ", cognom=" + cognom + ", sexe=" + sexe + ", datanaixement=" + datanaixement + ", telefon=" + telefon + ", email=" + email + ", comptebancari=" + comptebancari + ", usuari=" + usuari + ", contrasenya=" + contrasenya + ", publicitat=" + publicitat + ", impediment_fisic=" + impediment_fisic + '}';
    }
    
    //  fara falta variables de compte bancari i domicili, pot ser...
    
    public void MostrarClient() throws SQLException{
        Connexio_bd con = new Connexio_bd();
        Connection connexio = con.getConBD();
        
        String consulta = "SELECT * FROM clients";
        PreparedStatement ps = connexio.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()){
            System.out.println(rs.getString("DNI"));
        }
    }

    public DNI getDni() {
        return dni;
    }

    public void setDni(DNI dni) {
        this.dni = dni;
    }

    public Telefon getTelefon() {
        return telefon;
    }

    public void setTelefon(Telefon telefon) {
        this.telefon = telefon;
    }
    
    public void ConsultaClientDNI() throws SQLException{
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("****** Consulta Client ******");
        System.out.println("Introdueix el DNI del client que vols buscar: ");
        
        String dni = sc.next();
        
        Client client=ConsultaClient(dni);
        
        if(client==null){
            System.out.println("El client no existeix");
        }
    }
    
    public Client ConsultaClient(String dni) throws SQLException{
        Connexio_bd con = new Connexio_bd();
        Connection connexio = con.getConBD();
        
        String consulta ="SELECT * FROM clients WHERE dni=?";
        
        PreparedStatement sentencia = connexio.prepareStatement(consulta);
        sentencia.setString(1,dni);
        
        ResultSet rs = sentencia.executeQuery();
        
        if(rs.next()){
            this.dni = new DNI(rs.getString("DNI"));
            this.nom = rs.getString("NOM");
            System.out.println("Nom: \t\t\t" + nom);
            this.cognom = rs.getString("COGNOM");
            System.out.println("Cognom: \t\t" + cognom);
            this.sexe = rs.getString("SEXE");
            System.out.println("Sexe: \t\t\t" + sexe);
            this.datanaixement = rs.getDate("DATA_NAIX").toLocalDate();
            System.out.println("Data naixement: \t" + datanaixement);
            this.telefon = new Telefon(rs.getInt("TEL"));
            System.out.println("Telefon: \t\t" + telefon);
            this.email = new Email(rs.getString("EMAIL"));
            System.out.println("Email: \t\t\t" + email);
            this.comptebancari = new CompteBancari(rs.getString("IBAN"));
            System.out.println("IBAN: \t\t\t" + comptebancari);
            this.usuari=rs.getString("Usuari");
            System.out.println("Usuari: \t\t" + usuari);
            this.publicitat=rs.getInt("PUBLICITAT");
            System.out.println("Publicitat: \t\t" + publicitat);
            this.impediment_fisic=rs.getInt("IMPEDIMENT_FISIC");
            System.out.println("Impediment fisic: \t" + impediment_fisic);
            
            return this;
        }
        return null;
    }
    
    public void AfegirClient() throws SQLException{
        Scanner teclat = new Scanner(System.in);
        
        Connexio_bd con = new Connexio_bd();
        Connection connexio = con.getConBD();
        
        String consulta = ("INSERT INTO clients"
                +"(DNI, NOM, COGNOM, SEXE, DATA_NAIX, TEL, EMAIL, IBAN, USUARI, PASS, PUBLICITAT, IMPEDIMENT_FISIC)"
                +"VALUES "
                +"(?, ?, ?, ?, ?, ?, ?, ?, ?, MD5(?), ?, ?);");
        
        //  solicitem el dni a donar d'alta fins que sigui correcte
        
        DNI dniobj = new DNI();
                
        String dni;
        
        do{
            System.out.println("Introduex el dni del client que vols donar d'alta: ");
            dni=teclat.next();
            
        }while(!dniobj.validarDni(dni));
        dniobj.setDni(dni);
        this.dni = dniobj;
        
        PreparedStatement sentencia = null;
        
        System.out.println("Introdueix el nom");
        this.nom=teclat.next();
        System.out.println("Introdueix el cognom");
        this.cognom=teclat.next();
        System.out.println("Introdueix el sexe [home/dona]");
        this.sexe=teclat.next();
        
        do{
            System.out.println("Introdueix la data YYYY-MM-DD "); 
            datanaixement=LocalDate.parse(teclat.next());
        }while(datanaixement==null);
        
        Telefon telobj = new Telefon();
        
        int telefon;
        
        do{
            System.out.println("Introdueix el telefon");
            telefon=teclat.nextInt();
        }while(!telobj.validaTelefon(telefon));
        telobj.setTelefon(telefon);
        this.telefon = telobj;
        
        Email emaobj = new Email();
        
        String email;
        
        do{
            System.out.println("Introdueix el email");
            email=teclat.next();
        }while(!emaobj.validaEmail(email));
        emaobj.setEmail(email);
        this.email = emaobj;
        
        CompteBancari cbobj = new CompteBancari();
        
        String comptebancari;
        
        do{
            System.out.println("Introdueix el comptebancari");
            comptebancari=teclat.next();
        }while(!cbobj.validaCompteBancari(comptebancari));
        cbobj.setComptebancari(comptebancari);
        this.comptebancari = cbobj;
        
        System.out.println("Introdueix el Usuari");
        this.usuari=teclat.next();
        System.out.println("Introdueix la Contrasenya");
        this.contrasenya=teclat.next();
        System.out.println("Introdueix si el client vol rebre publicitats [1/0]");
        this.publicitat=teclat.nextInt();
        System.out.println("Introdueix si el client te algun impediment fisic [1/0]");
        this.impediment_fisic=teclat.nextInt();
        
        DateTimeFormatter formatejar = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatejarData = datanaixement.format(formatejar);
        
        try{
            sentencia = connexio.prepareStatement(consulta);
            sentencia.setString(1, this.dni.getDni());
            sentencia.setString(2, nom);
            sentencia.setString(3, cognom);
            sentencia.setString(4, sexe);
            sentencia.setString(5, formatejarData);
            sentencia.setInt(6, this.telefon.getTelefon());
            sentencia.setString(7, this.email.getEmail());
            sentencia.setString(8, this.comptebancari.getComptebancari());
            sentencia.setString(9, usuari);
            sentencia.setString(10, contrasenya);
            sentencia.setInt(11, publicitat);
            sentencia.setInt(12, impediment_fisic);
            sentencia.executeUpdate();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            if(sentencia != null)
            try{
                sentencia.close();
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }
        
    }
    
    public void BorrarClientDNI() throws SQLException{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("****** Borrar Client ******");
        System.out.println("Introdueix el DNI del client que vols borrar");
        
        String dni = sc.next();
        
        Client client=BorrarClient(dni);
        
        if(client==null){
            System.out.println("El client no existeix");
        }
    }
    
    public Client BorrarClient(String dni) throws SQLException{
        Connexio_bd con = new Connexio_bd();
        Connection connexio = con.getConBD();
        
        String consulta ="DELETE FROM clients WHERE DNI=?";
        
        PreparedStatement sentencia = connexio.prepareStatement(consulta);
        
        try {
            sentencia.setString(1, dni);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            //Tanquem els recursos oberts
            if (sentencia != null)
            try {
                sentencia.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return this;
    }
    
    public void ModificarClientDNI() throws SQLException{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("****** Modificar Client ******");
        System.out.println("Introdueix el DNI del client que vols modificar");
        
        String dni = sc.next();
        
        Client client=ModificarClient(dni);
        
        if(client==null){
            System.out.println("El client no existeix");
        }
    }
    
    public Client ModificarClient(String dni) throws SQLException{
        Connexio_bd con = new Connexio_bd();
        Connection connexio = con.getConBD();
        
        Scanner sc = new Scanner(System.in);
        
        String consulta = "UPDATE clients SET NOM = ?, COGNOM = ?, SEXE = ?, DATA_NAIX = ?, TEL = ?, EMAIL = ?, IBAN = ?, USUARI = ?, PASS = MD5(?), PUBLICITAT = ?, IMPEDIMENT_FISIC = ? WHERE DNI = ?";
        
        System.out.println("Posar el nou nom del client: ");
        this.nom=sc.next();
        System.out.println("Posar el nou cognom del client: ");
        this.cognom=sc.next();
        System.out.println("Posar el nou sexe del client [home/dona]: ");
        this.sexe=sc.next();
        
        do{
            System.out.println("Posar la nova data naixement YYYY-MM-DD: ");
            datanaixement=LocalDate.parse(sc.next());
        }while(datanaixement==null);
        
        Telefon telobj = new Telefon();
        
        int telefon;
        
        do{
            System.out.println("Posar el nou telefon: ");
            telefon=sc.nextInt();
        }while(!telobj.validaTelefon(telefon));
        telobj.setTelefon(telefon);
        this.telefon = telobj;
        
        Email emaobj = new Email();
        
        String email;
        
        do{
            System.out.println("Posar el nou email: ");
            email=sc.next();
        }while(!emaobj.validaEmail(email));
        emaobj.setEmail(email);
        this.email= emaobj;
        
        CompteBancari cbobj = new CompteBancari();
        
        String comptebancari;
        
        do{
            System.out.println("Posar el nou IBAN: ");
            comptebancari=sc.next();
        }while(!cbobj.validaCompteBancari(comptebancari));
        cbobj.setComptebancari(comptebancari);
        this.comptebancari=cbobj;
        
        System.out.println("Posar el nou usuari: ");
        this.usuari=sc.next();
        System.out.println("Posar la nova contrasenya: ");
        this.contrasenya=sc.next();
        System.out.println("Posar si vol rebre publicitats [1/0]: ");
        this.publicitat=sc.nextInt();
        System.out.println("Posar si te algun impediment fisic [1/0]: ");
        this.impediment_fisic=sc.nextInt();
        
        PreparedStatement sentencia = connexio.prepareStatement(consulta);
        
        DateTimeFormatter formatejar = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatejarData = datanaixement.format(formatejar);
        
        try{
            sentencia.setString(12, dni);
            sentencia.setString(1, nom);
            sentencia.setString(2, cognom);
            sentencia.setString(3, sexe);
            sentencia.setString(4, formatejarData);
            sentencia.setInt(5, this.telefon.getTelefon());
            sentencia.setString(6, this.email.getEmail());
            sentencia.setString(7, this.comptebancari.getComptebancari());
            sentencia.setString(8, usuari);
            sentencia.setString(9, contrasenya);
            sentencia.setInt(10, publicitat);
            sentencia.setInt(11, impediment_fisic);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            //Tanquem els recursos oberts
            if (sentencia != null)
            try {
                sentencia.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return null;
    }
    
    public ArrayList <Client> ClientsPerCriterisCognom() throws SQLException{
        ArrayList <Client> clients = new ArrayList<>(); 
        
        Connexio_bd con = new Connexio_bd();
        Connection connexio = con.getConBD();
        
        String consulta = "SELECT * FROM CLIENTS ORDER BY COGNOM, NOM";
        PreparedStatement ps = connexio.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()){
            Client cl = new Client();
            cl.obtenirResultatConsulta(rs);
            clients.add(cl);
        }
        return clients;
    }
    
    public ArrayList <Client>  ClientsPerCriterisEdat() throws SQLException{
        ArrayList <Client> clients = new ArrayList<>(); 
        
        Connexio_bd con = new Connexio_bd();
        Connection connexio = con.getConBD();
        
        String consulta = "SELECT * FROM CLIENTS ORDER BY DATA_NAIX ASC";
        PreparedStatement ps = connexio.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Client cl = new Client();
            cl.obtenirResultatConsulta(rs);
            clients.add(cl);
        }
        return clients;
    }
    
    public ArrayList <Client>  ClientsPerCriterisReserves() throws SQLException{
        ArrayList <Client> clients = new ArrayList<>(); 
        
        Connexio_bd con = new Connexio_bd();
        Connection connexio = con.getConBD();
        
        String consulta = "SELECT s.* FROM inscriuen i, clients s where i.DNI=s.DNI GROUP BY i.DNI ORDER BY COUNT(i.DNI) DESC";
        PreparedStatement ps = connexio.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Client cl = new Client();
            cl.obtenirResultatConsulta(rs);
            clients.add(cl);
        }
        return clients;
    }
    
    public void obtenirResultatConsulta(ResultSet rs) throws SQLException{
        this.dni = new DNI(rs.getString("DNI"));
        this.nom = rs.getString("NOM");
        this.cognom = rs.getString("COGNOM");
        this.sexe = rs.getString("SEXE");
        this.datanaixement = rs.getDate("DATA_NAIX").toLocalDate();
        this.telefon = new Telefon(rs.getInt("TEL"));
        this.email = new Email(rs.getString("EMAIL"));
        this.comptebancari = new CompteBancari(rs.getString("IBAN"));
        this.usuari = rs.getString("USUARI");
        this.contrasenya = rs.getString("PASS");
        this.publicitat = rs.getInt("PUBLICITAT");
        this.impediment_fisic=rs.getInt("IMPEDIMENT_FISIC");
    }
}
