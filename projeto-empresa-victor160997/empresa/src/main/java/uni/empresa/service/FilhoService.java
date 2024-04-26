package uni.empresa.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.empresa.model.Empregado;
import uni.empresa.model.Filho;
import uni.empresa.model.dao.EmpregadoDAO;
import uni.empresa.model.dao.FilhoDAO;
import uni.empresa.model.dto.filho.FilhoCreateDTO;
import uni.empresa.model.dto.filho.FilhoUpdateDTO;
import uni.empresa.model.dto.filho.FilhoViewDTO;

@Service
public class FilhoService {
    @Autowired
    FilhoDAO filhoDAO;

    @Autowired
    EmpregadoDAO empregadoDAO;

    public void criaFilho(FilhoCreateDTO filhoCreateDTO) throws Exception {

        if (filhoCreateDTO.getNome().isEmpty() || filhoCreateDTO.getNome().isBlank()) {
            throw new Exception("Nome do filho não pode ser vazio");
        }

        Optional<Empregado> empregado = empregadoDAO.findById(filhoCreateDTO.getIdEmpregado());
        if (!empregado.isPresent()) {
            throw new Exception("Empregado não encontrado");
        }

        Filho filho = new Filho();
        filho.setNome(filhoCreateDTO.getNome());
        filho.setEmpregado(empregado.get());
        filhoDAO.save(filho);
    }

    public void atualizaFilho(FilhoUpdateDTO filhoUpdateDTO) throws Exception {
        if (filhoUpdateDTO.getNome().isEmpty() || filhoUpdateDTO.getNome().isBlank()) {
            throw new Exception("Nome do filho não pode ser vazio");
        }

        Optional<Empregado> empregado = empregadoDAO.findById(filhoUpdateDTO.getIdEmpregado());
        if (!empregado.isPresent()) {
            throw new Exception("Empregado não encontrado");
        }

        Filho filho = filhoDAO.findById(filhoUpdateDTO.getId()).get();
        filho.setNome(filhoUpdateDTO.getNome());
        filho.setEmpregado(empregado.get());
        filhoDAO.save(filho);
    }

    public void deletaFilho(int id) throws Exception {
        Optional<Filho> filho = filhoDAO.findById(id);
        if (!filho.isPresent()) {
            throw new Exception("Filho não encontrado");
        }

        filhoDAO.delete(filho.get());
    }

    public FilhoViewDTO buscaFilho(int id) throws Exception {
        Optional<Filho> filho = filhoDAO.findById(id);
        if (!filho.isPresent()) {
            throw new Exception("Filho não encontrado");
        }

        FilhoViewDTO filhoView = new FilhoViewDTO();
        filhoView.setId(filho.get().getId());
        filhoView.setNome(filho.get().getNome());
        filhoView.setEmpregado(filho.get().getEmpregado());
        return filhoView;
    }

    public List<FilhoViewDTO> listaFilhos() {
        List<Filho> filhos = filhoDAO.findAll();
        List<FilhoViewDTO> filhosView = new ArrayList<FilhoViewDTO>();

        for (Filho filho : filhos) {
            FilhoViewDTO filhoView = new FilhoViewDTO();
            filhoView.setId(filho.getId());
            filhoView.setNome(filho.getNome());
            filhoView.setEmpregado(filho.getEmpregado());
            filhosView.add(filhoView);
        }

        return filhosView;
    }
}
