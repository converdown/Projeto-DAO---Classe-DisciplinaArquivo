package dao;

import entidade.Disciplina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOImp implements DisciplinaDAO {

    private final Connection conexao;

    public DisciplinaDAOImp(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insert(Disciplina d) {
        if (d == null || d.getNomeDisciplina() == null ||
            d.getNomeDisciplina().trim().isEmpty()) {
            throw new IllegalArgumentException("Disciplina inválida");
        }

        String sql = "INSERT INTO disciplina (nomedisciplina) VALUES (?)";

        try (PreparedStatement pst =
                     conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, d.getNomeDisciplina());
            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        d.setIdDisciplina(rs.getInt(1));
                    }
                }
                System.out.println("Disciplina inserida: " +
                        d.getIdDisciplina() + " - " + d.getNomeDisciplina());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir disciplina", e);
        }
    }

    @Override
    public void update(Disciplina d) {
        if (d == null || d.getIdDisciplina() == null) {
            throw new IllegalArgumentException("Disciplina ou ID inválido");
        }

        String sql = "UPDATE disciplina SET nomedisciplina = ? WHERE iddisciplina = ?";

        try (PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, d.getNomeDisciplina());
            pst.setInt(2, d.getIdDisciplina());

            int linhas = pst.executeUpdate();
            if (linhas == 0) {
                throw new RuntimeException("Disciplina não encontrada");
            }

            System.out.println("Disciplina atualizada: " +
                    d.getIdDisciplina() + " - " + d.getNomeDisciplina());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar disciplina", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("ID não pode ser nulo");

        String sql = "DELETE FROM disciplina WHERE iddisciplina = ?";

        try (PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, id);
            int linhas = pst.executeUpdate();

            if (linhas == 0) {
                throw new RuntimeException("Disciplina não encontrada");
            }

            System.out.println("Disciplina removida: ID " + id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover disciplina", e);
        }
    }

    @Override
    public Disciplina findById(Integer id) {
        if (id == null) return null;

        String sql = "SELECT iddisciplina, nomedisciplina FROM disciplina WHERE iddisciplina = ?";

        try (PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Disciplina(
                            rs.getInt("iddisciplina"),
                            rs.getString("nomedisciplina"));
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar disciplina", e);
        }
    }

    @Override
    public List<Disciplina> findAll() {
        List<Disciplina> disciplinas = new ArrayList<>();

        String sql = "SELECT iddisciplina, nomedisciplina FROM disciplina";

        try (PreparedStatement pst = conexao.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                disciplinas.add(new Disciplina(
                        rs.getInt("iddisciplina"),
                        rs.getString("nomedisciplina")));
            }
            return disciplinas;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar disciplinas", e);
        }
    }
}