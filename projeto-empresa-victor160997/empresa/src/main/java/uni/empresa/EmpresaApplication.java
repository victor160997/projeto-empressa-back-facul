package uni.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uni.empresa.model.EStatusEmpregado;
import uni.empresa.model.Empregado;
import uni.empresa.model.Funcao;
import uni.empresa.model.dao.EmpregadoDAO;
import uni.empresa.model.dao.FuncaoDAO;

@SpringBootApplication
public class EmpresaApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(EmpresaApplication.class, args);
	}

	@Autowired
	FuncaoDAO dFuncao;

	@Autowired
	EmpregadoDAO dEmpregado;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("###### iniciando carregamento dos dados");

		Funcao func = new Funcao();
		func.setNome("Gerente");
		func.setRemuneracao(10000.0);
		dFuncao.save(func);

		Empregado emp1 = new Empregado();
		emp1.setNome("João");
		emp1.setFuncao(func);
		emp1.setStatus(EStatusEmpregado.FERIAS);
		dEmpregado.save(emp1);

		Empregado emp2 = new Empregado();
		emp2.setNome("Maria");
		emp2.setFuncao(func);
		emp2.setStatus(EStatusEmpregado.ATIVO);
		dEmpregado.save(emp2);
	}

}
