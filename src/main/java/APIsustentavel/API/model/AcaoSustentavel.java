package APIsustentavel.API.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "aoes_sustentaveis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcaoSustentavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O título da ação é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição da ação é obrigatória")
    @Size(min = 10, max = 500, message = "A descrição deve ter entre 10 e 500 caracteres")
    private String descricao;

    @NotNull(message = "O tipo de ação é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoAcao tipoAcao;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    @NotBlank(message = "O responsável pela ação é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do responsável deve ter entre 3 e 100 caracteres")
    private String responsavel;

    @NotNull(message = "O tipo de responsável é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoResponsavel tipoResponsavel;

    @Email(message = "O email deve ser válido")
    private String email;

    private String telefone;

    @Min(value = 0, message = "O impacto estimado não pode ser negativo")
    private Double impactoEstimado;

    private String unidadeImpacto;

    private String localizacao;

    private Boolean concluida = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
