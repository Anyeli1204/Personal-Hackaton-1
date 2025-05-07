package Service;

import domain.Empresa;
import domain.Administrador;
import repository.EmpresaRepository;
import repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    // Crear nueva empresa
    public Empresa crearEmpresa(Empresa empresa, Administrador administrador) {
        Empresa nuevaEmpresa = empresaRepository.save(empresa);
        administrador.setEmpresa(nuevaEmpresa);
        administradorRepository.save(administrador);
        return nuevaEmpresa;
    }

    // Listar todas las empresas
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    // Obtener empresa por ID
    public Empresa obtenerEmpresaPorId(UUID id) {
        return empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }

    // Actualizar información de la empresa
    @Transactional
    public Empresa actualizarEmpresa(UUID id, Empresa empresaActualizada) {
        Empresa empresa = obtenerEmpresaPorId(id);
        empresa.setNombre(empresaActualizada.getNombre());
        empresa.setRuc(empresaActualizada.getRuc());
        empresa.setEstadoActivo(empresaActualizada.isEstadoActivo());
        return empresaRepository.save(empresa);
    }

    // Cambiar estado de la empresa
    @Transactional
    public Empresa cambiarEstadoEmpresa(UUID id, boolean estado) {
        Empresa empresa = obtenerEmpresaPorId(id);
        empresa.setEstadoActivo(estado);
        return empresaRepository.save(empresa);
    }

    // Obtener reporte de consumo
    public String obtenerConsumoEmpresa(UUID id) {
        Empresa empresa = obtenerEmpresaPorId(id);
        // Aquí iría la lógica para calcular el consumo, por ahora solo un texto de ejemplo
        return "Consumo total: 1000 tokens"; // Reemplazar por lógica real
    }
}
