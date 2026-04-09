package main
;

import dao.DisciplinaDAOImp;
import entidade.Disciplina;
import jdbc.DB;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class TesteDisciplinaDAO {

    public static void main(String[] args) {

        Connection conexao = null;
        DisciplinaDAOImp disciplinaDAO = null;

        try {
            conexao = DB.getConexao();
            disciplinaDAO = new DisciplinaDAOImp(conexao);

            try (Statement stmt = conexao.createStatement()) {
                stmt.executeUpdate("TRUNCATE TABLE disciplina RESTART IDENTITY");
            }

            System.out.println("=== TESTE DISCIPLINA DAO ===");

            Disciplina d1 = new Disciplina(null, "Programação Orientada a Objetos");
            Disciplina d2 = new Disciplina(null, "Banco de Dados");
            Disciplina d3 = new Disciplina(null, "Engenharia de Software");

            disciplinaDAO.insert(d1);
            disciplinaDAO.insert(d2);
            disciplinaDAO.insert(d3);

            System.out.println("\n--- Listando Disciplinas ---");
            List<Disciplina> list = disciplinaDAO.findAll();
            list.forEach(System.out::println);

            System.out.println("\n--- Atualizando Disciplina ID 2 ---");
            d2.setNomeDisciplina("Banco de Dados II");
            disciplinaDAO.update(d2);

            System.out.println("\n--- Removendo Disciplina ID 3 ---");
            disciplinaDAO.deleteById(3);

            System.out.println("\n--- Resultado Final ---");
            disciplinaDAO.findAll().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.fechaConexao();
        }
    }
}