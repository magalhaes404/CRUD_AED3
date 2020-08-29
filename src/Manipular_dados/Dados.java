/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manipular_dados;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.RandomAccessFile;


/**
 *
 * @author lmaga
 */
public interface Dados 
{
    
    public int getId();

    public void setId(int id);
    
    //public String chaveSecundaria();

    public byte[] toByteArray() throws IOException;
    
    public void fromByteArray(RandomAccessFile entrada) throws IOException;
    
    public void setTamanho_registro(int tamanho_registro);
    //public void println();
}

