
package gimnas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String email;

    public Email(){
        
    }
    
    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }
    
    public boolean validaEmail(String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = pattern.matcher(email);
        return (m.matches());
    }
}
