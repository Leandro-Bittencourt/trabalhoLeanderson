public class Main {
    public static void main(String[] args) {
        ConexaoBD conexaoBD = new ConexaoBD();

        LivroDAO livroDAO = new LivroDAO();
        livroDAO.inserirLivro(new Livro(2, "O Diário de um Mago", 1987, 1));
    }
}