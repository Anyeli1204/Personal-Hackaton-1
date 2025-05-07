package domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "restriccion")
public class Restriccion {

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
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setVentanaTiempo(String ventanaTiempo) {
        this.ventanaTiempo = ventanaTiempo;
    }
    public String getVentanaTiempo() {
        return ventanaTiempo;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
