package uni.empresa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uni.empresa.model.EStatusEmpregado;
import uni.empresa.model.Empregado;

public interface EmpregadoDAO extends JpaRepository<Empregado, Integer> {
    List<Empregado> findAllByStatus(EStatusEmpregado status);
}
