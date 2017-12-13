/**
 * Exemplo de aplicação com menu em modo texto.

 * 
 * @author José Creissac Campos
 * @version 1.0
 */


import controllers.UtilizadorController;
import exceptions.EmailAlreadyInUseException;
import models.DB;
import models.utilizadores.Utilizadores;
import views.menus.MenuPrincipal;

public class App {

//    static Utilizadores utilizadores;
    public static void main(String[] args) {

//        try {
//            utilizadores = new Utilizadores();
//            utilizadores.adiciona("email","nome","password","morada","17-01-1995",null);
//            utilizadores.adiciona("email1","nome1","password","morada","17-01-1995",null);
//            utilizadores.adiciona("email2","nome2","password","morada","17-01-1995",null);
//            utilizadores.adiciona("email3","nome3","password","morada","17-01-1995",null);
//            utilizadores.adiciona("user","user","password","morada","17-01-1995");
//            utilizadores.adiciona("user1","user1","password","morada","17-01-1995");
//            utilizadores.adiciona("user2","user2","password","morada","17-01-1995");
//            utilizadores.adiciona("user3","user3","password","morada","17-01-1995");
//
//            DB.setUtilizadores(utilizadores);
//            DB.termina();
//        } catch (EmailAlreadyInUseException e) {
//            e.printStackTrace();
//        }
        if ( DB.inicializa()) {
            new MenuPrincipal().executa();
            DB.termina();
        }
    }

}


