/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manipular_dados;

import index.HashExtensivel;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

/**
 *
 * @author lmaga
 */
public class CRUD<T extends Dados> {

    public final String diretório = "dados";
    public RandomAccessFile arquivo;
    public HashExtensivel indiceDireto;

    public Constructor<T> construtor;
    public Class<T> instance;

    public CRUD(Class<T> instance, String nomeArquivo) throws Exception {

        this.instance = instance;
        File d = new File(this.diretório);
        if (!d.exists()) {
            d.mkdir();
        }
        arquivo = new RandomAccessFile(this.diretório + "/" + nomeArquivo + ".db", "rw");
        if (arquivo.length() < 4) {
            arquivo.write(0);  // cabeçalho do arquivo
        }
        //System.out.println("Nome da Class="+instance.getSimpleName());

        indiceDireto = new HashExtensivel(10,
                this.diretório + "/diretorio." + nomeArquivo + ".idx",
                this.diretório + "/cestos." + nomeArquivo + ".idx");
    }

    public int create(T usuario) throws Exception {
        arquivo.seek(0);
        int id = arquivo.read() + 1;
        arquivo.seek(0);
        arquivo.write(id);
        usuario.setId(id);

        long endereco = arquivo.length();
        usuario.setTamanho_registro(usuario.getClass().toString().getBytes().length);
        // gravar index
        indiceDireto.create(id, endereco);
        //System.out.println("Nome Class "+instance.getName()+"");
        //aeds3_amigo_oculto.Usuario
        arquivo.seek(endereco);
        arquivo.writeBoolean(true);
        arquivo.write(usuario.toByteArray());

        return usuario.getId();
    }

    public T read_id(int id) throws Exception {
        long endereco = indiceDireto.read(id);
        arquivo.seek(endereco);
        T usuario = instance.newInstance();
        if (arquivo.readBoolean() == true) {
            usuario.fromByteArray(arquivo);
            id = usuario.getId();
        } else {
            new Exception("Usuario Não Cadastrado");
        }
        return usuario;
    }

    public void update(T user) throws Exception {
        long endereco = indiceDireto.read(user.getId());
        T user_antigo = read_id(user.getId());
        byte[] dadosNovo = user.toByteArray();
        byte[] dadosAntigo = user_antigo.toByteArray();
        if (dadosAntigo.length == dadosNovo.length) {
            arquivo.seek(endereco);
            arquivo.writeBoolean(true);
            arquivo.write(dadosNovo);
        } else {
            // invalidar local antigo
            arquivo.seek(endereco);
            arquivo.writeBoolean(false);

            // nova area valida
            arquivo.seek(arquivo.length());
            indiceDireto.update(user.getId(), arquivo.length());
            arquivo.writeBoolean(true);
            arquivo.write(dadosNovo);
        }
    }

    public void deletar(int id) throws Exception {
        long endereco = indiceDireto.read(id);
        arquivo.seek(endereco);
        arquivo.writeBoolean(false);
        T user = read_id(id);
        indiceDireto.delete(user.getId());
    }

    

}
