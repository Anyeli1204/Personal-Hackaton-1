package domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "limite")
public class Limite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String modeloIA;

    @Column(nullable = false)
    private int limiteUso;

    @Column(nullable = false)
    private int limiteTokens;

    @Column(nullable = false)
    private String ventanaTiempo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModeloIA() {
        return modeloIA;
    }

    public void setModeloIA(String modeloIA) {
        this.modeloIA = modeloIA;
    }

    public int getLimiteUso() {
        return limiteUso;
    }

    public void setLimiteUso(int limiteUso) {
        this.limiteUso = limiteUso;
    }

    public int getLimiteTokens() {
        return limiteTokens;
    }

    public void setLimiteTokens(int limiteTokens) {
        this.limiteTokens = limiteTokens;
    }

    public String getVentanaTiempo() {
        return ventanaTiempo;
    }

    public void setVentanaTiempo(String ventanaTiempo) {
        this.ventanaTiempo = ventanaTiempo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
