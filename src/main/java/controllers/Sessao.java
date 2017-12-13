package controllers;

import models.utilizadores.Ator;

/**
 * Created by danys on 12-Dec-17.
 */
public class Sessao {

    private static Ator _utilizador;

    public static Ator getUtilizador() {
        return _utilizador;
    }

    public static void criar(Ator utilizador) {
        _utilizador = utilizador;
    }
    public static void terminar() {
        _utilizador = null;
    }
}
