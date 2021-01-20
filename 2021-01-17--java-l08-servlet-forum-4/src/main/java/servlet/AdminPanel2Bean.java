package servlet;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class AdminPanel2Bean {
    private String welcome = "Hello World!";

    public String getWelcome() {
        return welcome;
    }
}
