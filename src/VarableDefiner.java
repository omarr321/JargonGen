import java.util.Dictionary;
import java.util.Hashtable;

public class VarableDefiner {
    private Dictionary<String, String> currVars = new Hashtable<>();

    public VarableDefiner(){}
    public void addVar(String key, String value) {
        if (!checkVar(key)) {
            currVars.put(key, value);
        }
    }

    public String getVar(String key) {
        if (checkVar(key)) {
            return "";
        } else {
            return currVars.get(key);
        }
    }

    public boolean checkVar(String key) {
        return currVars.get(key) != null;
    }
}
