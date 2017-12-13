package views.menus;
/**
 * Esta classe implementa um menu em modo texto.
 * 
 * @author Josá Creissac Campos 
 * @version v2.1 (20170504)
 */

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class Menu {

    private List<String> opcoes;
    private int op;
    private String cabecalho;

    public Menu() {}

    public Menu(String[] _opcoes, String _cabecalho) {
        this.opcoes = Arrays.asList(_opcoes);
        this.op = 0;
        this.cabecalho = _cabecalho;
    }

    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);

        redireciona();
    }

    private void showMenu() {
        System.out.println("\n" + cabecalho);
        for (int i=0; i<this.opcoes.size(); i++) {

            if (this.opcoes.get(i).contains("Sair") || this.opcoes.get(i).contains("Logout"))
                System.out.print(0);
            else
                System.out.print(i+1);

            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
    }

    private int lerOpcao() {
        //int op;
        Scanner is = new Scanner(System.in);
              System.out.print("Opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) {
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }

    public abstract void redireciona();

    public int getOpcao() {
        return this.op;
    }
}
