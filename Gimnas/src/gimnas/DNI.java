
package gimnas;

public class DNI {
    
    private String dni;
    private char lletra;

    public DNI(String dni) {
        this.dni = dni;
    }
    
    public DNI(){
        
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return dni;
    }
    
    public boolean validarDni(String dni){
        
        if (dni.length() !=9 ){
            System.out.println("El DNI no te els 9 caracters");
            return false;
        }
        
        char ultlletra = dni.charAt(8);
        
        if(!(Character.isLetter(ultlletra))){
            System.out.println("El ultim caracter del DNI no es una lletra");
            return false;
        }
        
        lletra = Character.toUpperCase(ultlletra);
        
        String dninum=dni.substring(0,8);
        
        
        
        return true;
        
    }
    
}
