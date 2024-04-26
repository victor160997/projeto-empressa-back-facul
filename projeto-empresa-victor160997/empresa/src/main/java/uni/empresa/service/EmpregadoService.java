package uni.empresa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.empresa.model.EStatusEmpregado;
import uni.empresa.model.Empregado;
import uni.empresa.model.Funcao;
import uni.empresa.model.Remuneracao;
import uni.empresa.model.dao.EmpregadoDAO;
import uni.empresa.model.dao.FuncaoDAO;
import uni.empresa.model.dao.RemuneracaoDAO;
import uni.empresa.model.dto.empregado.EmpregadoCreateDTO;
import uni.empresa.model.dto.empregado.EmpregadoViewDTO;

@Service
public class EmpregadoService {
    @Autowired
    EmpregadoDAO empregadoDAO;

    @Autowired
    FuncaoDAO funcaoDAO;

    @Autowired
    RemuneracaoDAO remuneracaoDAO;

    public void criaEmpregado(EmpregadoCreateDTO empregadoCreateDTO) throws Exception {

        if (empregadoCreateDTO.getNome().isEmpty() || empregadoCreateDTO.getNome().isBlank()) {
            throw new Exception("Nome do empregado não pode ser vazio");
        }

        Optional<Funcao> funcao = funcaoDAO.findById(empregadoCreateDTO.getIdFuncao());
        if (!funcao.isPresent()) {
            throw new Exception("Função não encontrada");
        }

        Empregado empregado = new Empregado();
        empregado.setNome(empregadoCreateDTO.getNome());
        empregado.setStatus(EStatusEmpregado.ATIVO);
        empregado.setFuncao(funcao.get());
        empregadoDAO.save(empregado);
    }

    public List<EmpregadoViewDTO> listaEmpregadosAtivos(Optional<EStatusEmpregado> status) {
        List<Empregado> empregados = status.isPresent() ? empregadoDAO.findAllByStatus(status.get())
                : empregadoDAO.findAll();
        List<EmpregadoViewDTO> empregadosView = new ArrayList<EmpregadoViewDTO>();

        for (Empregado empregado : empregados) {
            EmpregadoViewDTO empregadoView = new EmpregadoViewDTO();
            empregadoView.setId(empregado.getId());
            empregadoView.setNome(empregado.getNome());
            empregadoView.setStatus(empregado.getStatus().toString());
            empregadoView.setFuncao(empregado.getFuncao().getNome());
            empregadoView.setSalarioBrutoMensal(empregado.getFuncao().getRemuneracao());
            empregadoView.setSalarioBrutoAnual(empregado.getFuncao().getRemuneracao() * 12);
            empregadosView.add(empregadoView);
        }

        return empregadosView;
    }

    public void voltaFeriasEmpregado(int idEmpregado) throws Exception {
        Optional<Empregado> empregado = empregadoDAO.findById(idEmpregado);
        if (!empregado.isPresent()) {
            throw new Exception("Empregado não encontrado");
        }

        empregado.get().setStatus(EStatusEmpregado.ATIVO);
        empregadoDAO.save(empregado.get());
    }

    public void registraFeriasEmpregado(int idEmpregado) throws Exception {
        Optional<Empregado> empregado = empregadoDAO.findById(idEmpregado);
        if (!empregado.isPresent()) {
            throw new Exception("Empregado não encontrado");
        }

        Funcao funcEmpregado = empregado.get().getFuncao();

        double tercoFerias = funcEmpregado.getRemuneracao() / 3;

        Remuneracao remuneracao = new Remuneracao();
        remuneracao.setEmpregado(empregado.get());
        remuneracao.setDataPagamento(java.time.LocalDateTime.now());
        remuneracao.setValor(tercoFerias);

        empregado.get().setStatus(EStatusEmpregado.FERIAS);

        empregadoDAO.save(empregado.get());
        remuneracaoDAO.save(remuneracao);
    }

    public void salvaSalarioEmpregado(int idEmpregado) throws Exception {
        Optional<Empregado> empregado = empregadoDAO.findById(idEmpregado);
        if (!empregado.isPresent()) {
            throw new Exception("Empregado não encontrado");
        }

        Funcao funcEmpregado = empregado.get().getFuncao();

        double salario = funcEmpregado.getRemuneracao();

        salario = salario * 0.87; // INSS

        if (empregado.get().isNoturno()) { // Adicional noturno
            salario = salario * 1.05;
        }

        if (empregado.get().isValeTransporte()) { // Vale transporte
            salario = salario * 0.94;
        }

        int numFilhos = empregado.get().getFilhos().size();
        if (numFilhos > 0 && numFilhos <= 3) { // Bônus por filho
            int bonusFilhos = numFilhos * 50;
            salario += bonusFilhos;
        }

        if (numFilhos > 3) { // Bônus por filho
            int bonusFilhos = 3 * 50;
            salario += bonusFilhos;
        }

        Remuneracao remuneracao = new Remuneracao();
        remuneracao.setEmpregado(empregado.get());
        remuneracao.setDataPagamento(java.time.LocalDateTime.now());
        remuneracao.setValor(salario);

        remuneracaoDAO.save(remuneracao);
    }
}
