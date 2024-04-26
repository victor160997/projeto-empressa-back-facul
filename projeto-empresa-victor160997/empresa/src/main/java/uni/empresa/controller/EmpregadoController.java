package uni.empresa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.empresa.model.EStatusEmpregado;
import uni.empresa.model.dto.empregado.EmpregadoCreateDTO;
import uni.empresa.service.EmpregadoService;

@RestController
@RequestMapping("/empregado")
public class EmpregadoController {
    @Autowired
    private EmpregadoService empregadoService;

    @PostMapping("/")
    public ResponseEntity<?> criaEmpregado(@RequestBody(required = true) EmpregadoCreateDTO empregadoCreateDTO) {
        try {
            empregadoService.criaEmpregado(empregadoCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listaEmpregados(@RequestParam(required = false) Optional<EStatusEmpregado> status) {
        try {
            return ResponseEntity.ok(empregadoService.listaEmpregadosAtivos(status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/alocaFeriasEPagaTercoSalario/{idEmpregado}")
    public ResponseEntity<?> alocaFerias(@PathVariable(required = true) int idEmpregado) {
        try {
            empregadoService.registraFeriasEmpregado(idEmpregado);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/voltaFerias/{idEmpregado}")
    public ResponseEntity<?> voltaFerias(@PathVariable(required = true) int idEmpregado) {
        try {
            empregadoService.voltaFeriasEmpregado(idEmpregado);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/pagaEmpregado/{idEmpregado}")
    public ResponseEntity<?> pagaEmpregado(@PathVariable(required = true) int idEmpregado) {
        try {
            empregadoService.salvaSalarioEmpregado(idEmpregado);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
