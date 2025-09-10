package models;

public class Jogo {

    private int id;
    private String nome;
    private String status;
    private String genero;

    public Jogo(String nome, String status, String genero) {
        this.nome = nome;
        this.status = status;
        this.genero = genero;
    }

    public int getId(){
        return this.id;
    }
    public int setId(int id){
        return this.id = id;
    }
    public String getNome(){
        return this.nome;
    }
    public String setNome(String nome){
        return this.nome = nome;
    }
    public String getStatus(){
        return this.status;
    }
    public String setStatus(String status){
        return this.status = status;
    }
    public String getGenero(){
        return this.genero;
    }
    public String setGenero(String genero){
        return this.genero = genero;
    }

    @Override
    public String toString(){
        return "Jogo [id=" + id + ", nome=" + nome + ", genero=" + genero + ", status=" + status + "]";
    }


}
