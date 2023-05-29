package automation;

import sun.awt.HKSCS;

public class Automation {
    String name;
    String username;

    public String getName() {

        return name;
    }

    public void insertName(String username) {
        this.username = username;
        name = username;
    }

}
