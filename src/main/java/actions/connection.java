package actions;

import com.opensymphony.xwork2.ActionSupport;
import facade.FacadeParisStaticImpl;
import facade.exceptions.InformationsSaisiesIncoherentesException;
import facade.exceptions.UtilisateurDejaConnecteException;
import model.Utilisateur;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.ApplicationAware;

import java.util.Map;
import java.util.Objects;

public class connection extends ActionSupport implements ApplicationAware, SessionAware {
    private String login;
    private String password;

    private Map<String, Object> session;

    private FacadeParisStaticImpl facadeParisStatic;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void validate() {
        if (Objects.isNull(password) || password.isEmpty()){
            addFieldError("password", "You need to insert a password !");
        }
        if (Objects.isNull(login)|| login.isEmpty()){
            addFieldError("login", "You need to insert a login !");
        }
    }

    @Override
    public String execute() throws Exception {
        // here we need to call the connection function that exists in the facade and catch all the exceptions
        try{
            this.facadeParisStatic.connexion(login, password);
        }catch (InformationsSaisiesIncoherentesException e){
            addActionError("You entered a wrong password !");
            return INPUT;
        }catch(UtilisateurDejaConnecteException e1){
            addActionError(e1.getMessage());
            return INPUT;
        }
        session.put("user", new Utilisateur(login, password));
        return SUCCESS;
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        this.facadeParisStatic = (FacadeParisStaticImpl) map.get("facade");
        if (Objects.isNull(this.facadeParisStatic)){
            this.facadeParisStatic = new FacadeParisStaticImpl();
            map.put("facade", this.facadeParisStatic);
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
