
package gimnas;

public class Telefon {
    private int telefon;

    public Telefon() {
    }
    
    public Telefon(int telefon) {
        this.telefon = telefon;
    }
    
    @Override
    public String toString() {
        return "" +telefon;
    }
    
    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }
    
    public int getTelefon() {
        return telefon;
    }
    
    public boolean validaTelefon(int telefon){
        if(100000000 >= telefon && telefon >= 999999999){
            System.out.println("El telefon no te els 9 caracters");
            return false;
        }
        return true;
    }
}
