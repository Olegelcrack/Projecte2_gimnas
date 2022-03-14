
package gimnas;

public class CompteBancari {
    private String comptebancari;
    private String lletres;
    private String numeros;
    
    public CompteBancari(String comptebancari) {
        this.comptebancari = comptebancari;
    }

    public CompteBancari(){
    
    }

    public void setComptebancari(String comptebancari) {
        this.comptebancari = comptebancari;
    }

    public String getComptebancari() {
        return comptebancari;
    }
    
    @Override
    public String toString() {
        return comptebancari;
    }

    public boolean validaCompteBancari(String comptebancari){
        if(comptebancari.length()!=24){
            System.out.println("El IBAN no te els 24 digits");
            return false;
        }
        
        lletres = comptebancari.substring(0,2); 
        
        if(lletres == "ES"){
            System.out.println("Els primers dos digits no som ES");
            return false;
        }
        
        numeros = comptebancari.substring(2,24);
                
        if(!numeros.chars().allMatch(Character::isDigit)){
            System.out.println(numeros);
            System.out.println("El IBAN es incorrecte");
            return false;
        }
        
        return true;
    }
}
