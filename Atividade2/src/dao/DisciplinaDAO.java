package dao;

import java.util.List;
import entidade.Disciplina;

public interface DisciplinaDAO {

    void insert(Disciplina d);
    void update(Disciplina d);
    void deleteById(Integer id);
    Disciplina findById(Integer id);
    List<Disciplina> findAll();
}