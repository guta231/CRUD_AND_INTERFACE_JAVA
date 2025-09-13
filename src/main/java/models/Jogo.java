package models;

public class Jogo {

    private int id;
    private String nome;
    private String status;
    private String genero;
    private String plataforma;
    private int anoLancamento;

    public Jogo(String nome, String status, String genero, String plataforma, int anoLancamento) {
        this.nome = nome;
        this.status = status;
        this.genero = genero;
        this.plataforma = plataforma;
        this.anoLancamento = anoLancamento;
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
    public String getPlataforma(){return this.plataforma;}
    public String setPlataforma(String plataforma){return this.plataforma = plataforma;}
    public int getAnoLancamento(){return this.anoLancamento;}
    public int setAnoLancamento(int anoLancamento){return this.anoLancamento = anoLancamento;}
    @Override
    public String toString(){
        return "Jogo [id=" + id + ", nome=" + nome + ", genero=" + genero + ", status=" + status + "]";
    }


}
