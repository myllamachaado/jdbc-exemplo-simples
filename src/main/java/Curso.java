import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Curso{
    int id;
    String nome;
    float duracao;

    public Curso(){

    }

    public Curso(String nome, float duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    public Curso(int id, String nome, float duracao) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Curso{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", curacao=").append(duracao);
        sb.append('}');
        return sb.toString();
    }

}
