package br.com.sample.agendaapp.core;

public class ControllerProfileActivity {

    private static ControllerProfileActivity instance = null;
    private String value;

    private ControllerProfileActivity(){

    }

    public static ControllerProfileActivity getInstance(){
        if(instance == null){
            instance = new ControllerProfileActivity();
        }
        return instance;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
