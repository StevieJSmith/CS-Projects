import java.util.HashMap;

public class LoginInfomation {

    private HashMap<String, String> loginInfo = new HashMap<String, String>();

    LoginInfomation() {
        loginInfo.put("John", "1234");
        loginInfo.put("Steven", "ss9876");
        loginInfo.put("Amy", "password");
        loginInfo.put("Rose", "!RMC1990");

    }

    protected HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }



}
