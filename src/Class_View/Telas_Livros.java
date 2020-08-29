/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class_View;

import Class_Dados.Livro;
import java.util.Scanner;

public class Telas_Livros {

    Scanner ler = new Scanner(System.in);

    public void cadastro() {
        Livro livro = new Livro();
        System.out.println("Digite o titulo do livro ");
        String nome = ler.nextLine();
        if (nome.length() > 2) {
            livro.setTitulo(nome);
            System.out.println("Digite o autor do livro ");
            String autor = ler.nextLine();
            if (autor.length() > 2) {
                livro.setAutor(autor);
                System.out.println("Digite a data que foi publicada ");

            }
        }

    }

}
