package Class_Dados;

import Manipular_dados.Dados;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lmaga
 */
public class Livro implements Dados {

    int id;
    String titulo, autor;
    float preco;
    long data_publicacao;
    boolean excluido;
    int tamanho_registro;

    public Livro() {
        this.id = -1;
        this.titulo = "";
        this.autor = "";
        this.preco = 0;
        this.data_publicacao = 0;
        this.excluido = false;
        this.tamanho_registro = 0;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public long getData_publicacao() {
        return data_publicacao;
    }

    public String getData() {
        SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        data.setTime(this.getData_publicacao());
        return format.format(data);
    }

    public void setData_publicacao(String data_publicacao) throws ParseException {
        SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
        Date data = format.parse(data_publicacao);
        long millis = data.getTime();
        this.data_publicacao = millis;
    }

    public void setData_publicacao(long data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public int getTamanho_registro() {
        return tamanho_registro;
    }

    public void setTamanho_registro(int tamanho_registro) {
        this.tamanho_registro = tamanho_registro;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream(dados);
        saida.writeInt(this.id);
        saida.writeUTF(this.titulo);
        saida.writeUTF(this.autor);
        saida.writeFloat(this.preco);
        saida.writeLong(this.data_publicacao);
        saida.writeBoolean(this.excluido);
        saida.writeInt(this.getTamanho_registro());
        // Escrever os demais atributos do objeto usando métodos como writeInt(), writeUTF(), writeFloat(), ...
        return dados.toByteArray();
    }

    @Override
    public void fromByteArray(RandomAccessFile entrada) throws IOException {
        this.setId(entrada.readInt());
        this.setTitulo(entrada.readUTF());
        this.setAutor(entrada.readUTF());
        this.setPreco(entrada.readFloat());
        this.setData_publicacao(entrada.readShort());
        this.setTamanho_registro(entrada.readInt());
    }

    public void println() {
        System.out.println("Titulo: " + this.getTitulo());
        System.out.println("Autor: " + this.getAutor());
        System.out.println("Data Publicacao: " + this.getData());
    }

}
