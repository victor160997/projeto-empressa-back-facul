package uni.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uni.empresa.model.dto.filho.FilhoCreateDTO;
import uni.empresa.model.dto.filho.FilhoUpdateDTO;
import uni.empresa.service.FilhoService;

@RestController
@RequestMapping("/filho")
public class FilhoController {
    @Autowired
    private FilhoService filhoService;

    @PostMapping("/")
    public ResponseEntity<?> criaFilho(@RequestBody(required = true) FilhoCreateDTO filhoCreateDTO) {
        try {
            filhoService.criaFilho(filhoCreateDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> atualizaFilho(@RequestBody(required = true) FilhoUpdateDTO filhoUpdateDTO) {
        try {
            filhoService.atualizaFilho(filhoUpdateDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaFilho(@PathVariable(required = true) int id) {
        try {
            filhoService.deletaFilho(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idFilho}")
    public ResponseEntity<?> buscaFilho(@PathVariable(required = true) int idFilho) {
        try {
            return ResponseEntity.ok(filhoService.buscaFilho(idFilho));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listaFilhos() {
        try {
            return ResponseEntity.ok(filhoService.listaFilhos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
