package view;
import controller.ControllerPing;

public class Principal {
    public static void main(String[] args) {
        ControllerPing cont = new ControllerPing();
        String[] endereços = {"UOL", "Terra", "Google"};

        for (String site : endereços) {
           cont.ping(site);
        }
    }
}