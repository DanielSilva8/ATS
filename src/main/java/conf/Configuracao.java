package conf;

/**
 * Created by danys on 12-Dec-17.
 */
public class Configuracao {

    private static String CAMINHO_BIN = System.getProperty("user.dir") + "/src/main/resources/Appdadosbin";
    private static String CAMINHO_TXT = System.getProperty("user.dir") + "/src/main/resources/Appdadosbin.txt";

    public static void setCaminhoBin(String caminhoBin) {
        CAMINHO_BIN = System.getProperty("user.dir") + caminhoBin;
    }

    public static void setCaminhoTxt(String caminhoTxt) {
        CAMINHO_TXT = System.getProperty("user.dir") + caminhoTxt;
    }

    public static String getCaminhoBin() {
        return CAMINHO_BIN;
    }

    public static String getCaminhoTxt() {
        return CAMINHO_TXT;
    }
}
