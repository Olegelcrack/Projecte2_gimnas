
package gimnas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gimnas {
    private String nom;
    private String CIF;
    private String telefon;
    
    private Client client = new Client();
    private Activitat act = new Activitat();
    private boolean sortir=false;
    private boolean enrere1=false;
    private boolean enrere2=false;
    
    ArrayList<Client> clients;
    ArrayList<Activitat> activitats;
    
    public Gimnas(){
        this.clients = new ArrayList();
        this.activitats = new ArrayList();
    }
    
    public void gestionarGimnas() throws SQLException{
        Scanner ans = new Scanner(System.in);
        do{
            System.out.println("************Menu Gimnas************");

            System.out.println("1. Gestio client(V/I/E/M)");
            System.out.println("2. Visualitzar els clients per criteris");
            System.out.println("3. Visualitzar l'activitat del dia");
            System.out.println("4. Sortir");
            
            String answ = ans.next();
            char opcio = answ.charAt(0);
            
            System.out.println("\nHeu triat la opcio: " +opcio);
            
            switch (opcio){
                case '1':
                    do{
                        System.out.println("1. Visualitzar clients");
                        System.out.println("2. Consulta clients per DNI");
                        System.out.println("3. Inserir clients");
                        System.out.println("4. Esborrar clients");
                        System.out.println("5. Modificar clients");
                        System.out.println("6. Tornar enrere");
                        
                        answ = ans.next();
                        opcio = answ.charAt(0);
                        
                        System.out.println("Heu triat la opcio: " +opcio);
                        
                        switch(opcio){
                            case '1':
                                client.MostrarClient();
                                break;
                            case'2':
                                client.ConsultaClientDNI();
                                break;
                            case '3':
                                client.AfegirClient();
                                break;
                            case '4':
                                client.BorrarClientDNI();
                                break;
                            case '5':
                                client.ModificarClientDNI();
                                break;
                            case '6':
                                enrere1=true;
                                break;
                            default:
                                System.out.println("Opció no valida");
                        }
                    }while(!enrere1);
                    break;
                case '2':
                    do{
                        System.out.println("1. Visualitzar per Cognom");
                        System.out.println("2. Visualitzar per Edat");
                        System.out.println("3. Visualitzar per Reserves fetes");
                        System.out.println("4. Tornar enrere");
                        
                        answ = ans.next();
                        opcio = answ.charAt(0);
                        
                        System.out.println("Heu triat la opcio: " +opcio);
                        
                        switch(opcio){
                            case '1':
                                this.clients = client.ClientsPerCriterisCognom();
                                Visualitzar();
                                break;
                            case'2':
                                this.clients = client.ClientsPerCriterisEdat();
                                Visualitzar();
                                break;
                            case '3':
                                this.clients = client.ClientsPerCriterisReserves();
                                Visualitzar();
                                break;
                            case '4':
                                enrere2=true;
                                break;
                            default:
                                System.out.println("Opció no valida");
                        }
                    }while(!enrere2);
                case'3':
                    act.VisualitzarActivitat();
                    break;
                case'4':
                    sortir=true;
                    break;
            }
        }while(!sortir);    
    }
    
    private void Visualitzar(){
        for(Client cli: this.clients){
            System.out.println(cli);
        }
    }
}
