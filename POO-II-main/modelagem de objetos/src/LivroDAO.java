import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO Livro (Titulo, AnoPublicacao, IDAutor) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAnoPublicacao());
            stmt.setInt(3, livro.getIdAutor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE Livro SET Titulo = ?, AnoPublicacao = ? WHERE ID_Livro = ?";

        try (Connection conn = ConexaoBD.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAnoPublicacao());
            stmt.setInt(3, livro.getIdLivro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirLivro(int idLivro) {
        String sql = "DELETE FROM Livro WHERE ID_Livro = ?";

        try (Connection conn = ConexaoBD.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro";

        try (Connection conn = ConexaoBD.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setIdLivro(rs.getInt("ID_Livro"));
                livro.setTitulo(rs.getString("Titulo"));
                livro.setAnoPublicacao(rs.getInt("AnoPublicacao"));
                livro.setIdAutor(rs.getInt("IDAutor"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public List<Livro> listarLivrosPorAutor(int idAutor) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro WHERE IDAutor = ?";

        try (Connection conn = ConexaoBD.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAutor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro();
                    livro.setIdLivro(rs.getInt("ID_Livro"));
                    livro.setTitulo(rs.getString("Titulo"));
                    livro.setAnoPublicacao(rs.getInt("AnoPublicacao"));
                    livro.setIdAutor(rs.getInt("IDAutor"));
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }
}
