package entidade;

import java.util.Objects;

public class Disciplina {
	

    private Integer idDisciplina;
    private String nomeDisciplina;

    public Disciplina(Integer idDisciplina, String nomeDisciplina) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDisciplina, nomeDisciplina);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disciplina other = (Disciplina) obj;
        return Objects.equals(idDisciplina, other.idDisciplina)
                && Objects.equals(nomeDisciplina, other.nomeDisciplina);
    }

    @Override
    public String toString() {
        return "Disciplina [idDisciplina=" + idDisciplina +
               ", nomeDisciplina=" + nomeDisciplina + "]";
        
    }
}