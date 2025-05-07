package Service;

import domain.Restriccion;
import domain.Empresa;
import repository.RestriccionRepository;
import repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RestriccionService {

    @Autowired
    private RestriccionRepository restriccionRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    // Crear nueva restricción
    public Restriccion crearRestriccion(UUID empresaId, Restriccion restriccion) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        restriccion.setEmpresa(empresa);
        return restriccionRepository.save(restriccion);
    }

    // Listar todas las restricciones de una empresa
    public List<Restriccion> listarRestricciones(UUID empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        return empresa.getRestricciones();
    }

    // Actualizar una restricción existente
    @Transactional
    public Restriccion actualizarRestriccion(UUID id, Restriccion restriccionActualizada) {
        Restriccion restriccion = restriccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restricción no encontrada"));
        restriccion.setModeloIA(restriccionActualizada.getModeloIA());
        restriccion.setLimiteUso(restriccionActualizada.getLimiteUso());
        restriccion.setLimiteTokens(restriccionActualizada.getLimiteTokens());
        restriccion.setVentanaTiempo(restriccionActualizada.getVentanaTiempo());
        return restriccionRepository.save(restriccion);
    }

    // Eliminar una restricción
    @Transactional
    public void eliminarRestriccion(UUID id) {
        if (!restriccionRepository.existsById(id)) {
            throw new RuntimeException("Restricción no encontrada");
        }
        restriccionRepository.deleteById(id);
    }
}
