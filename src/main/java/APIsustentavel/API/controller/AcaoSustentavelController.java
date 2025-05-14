package APIsustentavel.API.controller;

import APIsustentavel.API.dto.AcaoSustentavelDTO;
import APIsustentavel.API.model.TipoAcao;
import jakarta.validation.Valid;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/v1/acoes")
@Tag(name = "Ações Sustentáveis", description = "API para gerenciamento de ações sustentáveis")
public class AcaoSustentavelController {

    private final AcaoSustentavelService service;

    @Autowired
    public AcaoSustentavelController(AcaoSustentavelService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar uma nova ação sustentável",
            description = "Salva uma nova ação sustentável no sistema")
    @ApiResponse(responseCode = "201", description = "Ação sustentável criada com sucesso")
    public ResponseEntity<AcaoSustentavelDTO> criar(@Valid @RequestBody AcaoSustentavelDTO dto) {
        AcaoSustentavelDTO criada = service.criar(dto);
        return new ResponseEntity<>(criada, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar ação por ID",
            description = "Retorna uma ação sustentável específica pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Ação sustentável encontrada")
    @ApiResponse(responseCode = "404", description = "Ação sustentável não encontrada")
    public ResponseEntity<AcaoSustentavelDTO> buscarPorId(
            @Parameter(description = "ID da ação sustentável") @PathVariable Long id) {
        AcaoSustentavelDTO acao = service.buscarPorId(id);
        return ResponseEntity.ok(acao);
    }

    @GetMapping
    @Operation(summary = "Listar todas as ações sustentáveis",
            description = "Retorna uma lista paginada de todas as ações sustentáveis")
    @ApiResponse(responseCode = "200", description = "Lista de ações sustentáveis retornada com sucesso")
    public ResponseEntity<Page<AcaoSustentavelDTO>> listarTodas(
            @PageableDefault(size = 10, sort = "dataCadastro") Pageable pageable) {
        Page<AcaoSustentavelDTO> acoes = service.listarTodas(pageable);
        return ResponseEntity.ok(acoes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ação sustentável",
            description = "Atualiza os dados de uma ação sustentável existente")
    @ApiResponse(responseCode = "200", description = "Ação sustentável atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Ação sustentável não encontrada")
    public ResponseEntity<AcaoSustentavelDTO> atualizar(
            @Parameter(description = "ID da ação sustentável") @PathVariable Long id,
            @Valid @RequestBody AcaoSustentavelDTO dto) {
        AcaoSustentavelDTO atualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir ação sustentável",
            description = "Remove uma ação sustentável do sistema")
    @ApiResponse(responseCode = "204", description = "Ação sustentável excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Ação sustentável não encontrada")
    public ResponseEntity<Void> excluir(
            @Parameter(description = "ID da ação sustentável") @PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo-acao/{tipoAcao}")
    @Operation(summary = "Buscar ações por tipo",
            description = "Retorna uma lista paginada de ações sustentáveis pelo tipo de ação")
    public ResponseEntity<Page<AcaoSustentavelDTO>> buscarPorTipoAcao(
            @Parameter(description = "Tipo da ação sustentável") @PathVariable TipoAcao tipoAcao,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<AcaoSustentavelDTO> acoes = service.buscarPorTipoAcao(tipoAcao, pageable);
        return ResponseEntity.ok(acoes);
    }

    @GetMapping("/tipo-responsavel/{tipoResponsavel}")
    @Operation(summary = "Buscar ações por tipo de responsável",
            description = "Retorna uma lista paginada de ações sustentáveis pelo tipo de responsável")
    public ResponseEntity<Page<AcaoSustentavelDTO>> buscarPorTipoResponsavel(
            @Parameter(description = "Tipo do responsável pela ação") @PathVariable TipoResponsavel tipoResponsavel,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<AcaoSustentavelDTO> acoes = service.buscarPorTipoResponsavel(tipoResponsavel, pageable);
        return ResponseEntity.ok(acoes);
    }

    @GetMapping("/responsavel")
    @Operation(summary = "Buscar ações por nome do responsável",
            description = "Retorna uma lista paginada de ações sustentáveis pelo nome do responsável")
    public ResponseEntity<Page<AcaoSustentavelDTO>> buscarPorResponsavel(
            @Parameter(description = "Nome do responsável") @RequestParam String nome,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<AcaoSustentavelDTO> acoes = service.buscarPorResponsavel(nome, pageable);
        return ResponseEntity.ok(acoes);
    }

    @GetMapping("/localizacao")
    @Operation(summary = "Buscar ações por localização",
            description = "Retorna uma lista paginada de ações sustentáveis pela localização")
    public ResponseEntity<Page<AcaoSustentavelDTO>> buscarPorLocalizacao(
            @Parameter(description = "Nome da localização") @RequestParam String local,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<AcaoSustentavelDTO> acoes = service.buscarPorLocalizacao(local, pageable);
        return ResponseEntity.ok(acoes);
    }

    @GetMapping("/status")
    @Operation(summary = "Buscar ações por status de conclusão",
            description = "Retorna uma lista paginada de ações sustentáveis pelo status de conclusão")
    public ResponseEntity<Page<AcaoSustentavelDTO>> buscarPorStatus(
            @Parameter(description = "Status de conclusão (true/false)") @RequestParam Boolean concluida,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<AcaoSustentavelDTO> acoes = service.buscarPorStatus(concluida, pageable);
        return Response;
    }
}
