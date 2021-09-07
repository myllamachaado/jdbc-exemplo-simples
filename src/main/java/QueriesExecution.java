import java.util.List;

public class QueriesExecution {

    public static void main(String[] args){
        CursoDAO cursoDAO = new CursoDAO();

        // [Modelo consulta com filtro]
        Curso curso = cursoDAO.getById(6);
        System.out.println(curso);

        // [Modelo create]
        Curso inserir = new Curso("Lógica", 30.0f);
        cursoDAO.create(inserir);

        // [Modelo update]
        Curso atualizar = new Curso(6, "Java Web", 200.1f);
        cursoDAO.update(atualizar);

        // [Modelo delete]
        cursoDAO.delete(4);

        // [Modelo consulta]
        List<Curso> cursos = cursoDAO.list();
        cursos.stream().forEach(System.out::println);
        cursos.clear();
    }
}
