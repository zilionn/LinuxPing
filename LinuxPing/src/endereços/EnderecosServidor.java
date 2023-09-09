package endereços;

public class EnderecosServidor {
    public static String getServerAddress(String endereco) {
        switch (endereco) {
            case "UOL":
                return "www.uol.com.br";
            case "Terra":
                return "www.terra.com.br";
            case "Google":
                return "www.google.com.br";
            default:
                return "";
        }
    }
}