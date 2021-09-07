import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public List<Curso> list(){
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM CURSO";

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                float horas = rs.getInt("duracao_horas");

                cursos.add(new Curso(id, nome, horas));
            }

        }
        catch (SQLException e){
            System.out.println("Erro ao listar cursos.");
            e.printStackTrace();
        }

        return cursos;
    }

    public Curso getById(int id){
        Curso curso = new Curso();
        String sql = "SELECT * FROM CURSO WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                curso.setDuracao(rs.getFloat("duracao_horas"));
            }
            else{
                System.out.println("Registro não existe.");
            }

        }
        catch (SQLException e){
            System.out.println("Erro ao buscar curso.");
            e.printStackTrace();
        }

        return curso;
    }

    public void create(Curso curso){
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO CURSO(NOME, DURACAO_HORAS) VALUES( ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setFloat(2, curso.getDuracao());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Inserção BEM SUCEDIDA!. Foi adicionada " + rowsAffected + " linha");

        }
        catch (SQLException e){
            System.out.println("Erro ao criar curso.");
            e.printStackTrace();
        }

    }

    public void delete(int id){
        String sql = "DELETE FROM CURSO WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Delete BEM SUCEDIDO! Foi deletada " + rowsAffected + " linha");
        }
        catch (SQLException e){
            System.out.println("Erro ao buscar curso.");
            e.printStackTrace();
        }
    }

    public void update(Curso curso){
        String sql = "UPDATE CURSO SET NOME=?, DURACAO_HORAS=? WHERE ID=?";

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setFloat(2, curso.getDuracao());
            stmt.setInt(3, curso.getId());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Update BEM SUCEDIDO! " + rowsAffected + " linha atualizada");
        }
        catch (SQLException e){
            System.out.println("Erro ao buscar curso.");
            e.printStackTrace();
        }
    }

}
