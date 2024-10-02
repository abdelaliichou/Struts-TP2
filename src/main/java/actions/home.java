package actions;

import com.opensymphony.xwork2.ActionSupport;
import facade.FacadeParisStaticImpl;
import model.Utilisateur;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.ApplicationAware;

import java.util.Map;
import java.util.Objects;

public class home extends ActionSupport implements ApplicationAware, SessionAware {
    private FacadeParisStaticImpl facadeParisStatic;

    private Map<String, Object> session;

    @Override
    public String execute() throws Exception {
        Utilisateur user = (Utilisateur)  this.session.get("user");
        this.facadeParisStatic.deconnexion(user.getLogin());
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
