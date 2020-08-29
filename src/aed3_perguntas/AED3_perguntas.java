/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed3_perguntas;

import Class_Dados.Livro;
import Manipular_dados.CRUD;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lmaga
 */
public class AED3_perguntas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Cadastrar Livros");
            Livro livro = new Livro();
            livro.setTitulo("Flashpoint");
            livro.setAutor("Geoff Johns");
            livro.setPreco(80.00f);
            livro.setData_publicacao("05/03/2011");
            CRUD crud = new CRUD(Livro.class,"Livros");
            crud.create(livro);
            System.out.println("Livro Cadastrado 1 ");
            // livro 2
            System.out.println("Cadastrar Livro 2 ");
            livro = new Livro();
            livro.setTitulo("Livro 2");
            livro.setAutor("Autor 2");
            livro.setPreco(80.00f);
            livro.setData_publicacao("02/02/2002");
            crud.create(livro);
            // livro 3
            System.out.println("Cadastrar Livro 3 ");
            livro = new Livro();
            livro.setTitulo("Livro 3");
            livro.setAutor("Autor 3");
            livro.setPreco(80.00f);
            livro.setData_publicacao("03/03/2003");
            crud.create(livro);
            
            // System update
            livro.setId(2);
            livro.setTitulo("Batman - O Cavaleiro de Gotham");
            livro.setAutor("Brian Azzarello");
            livro.setPreco(150.00f);
            livro.setData_publicacao("08/08/2008");  
            crud.update(livro);
            // ler registro 
            
            
            livro = (Livro) crud.read_id(2);
            livro.println();
            
            // TODO code application logic here
        } catch (ParseException ex) {
            Logger.getLogger(AED3_perguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AED3_perguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
