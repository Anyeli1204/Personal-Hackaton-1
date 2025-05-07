package com.example.hacka1.versionfinal.service;

import com.example.hacka1.versionfinal.domain.Administrador;
import com.example.hacka1.versionfinal.domain.Empresa;
import com.example.hacka1.versionfinal.domain.Solicitud;
import com.example.hacka1.versionfinal.domain.Usuario;
import com.example.hacka1.versionfinal.dtos.ConsumoEmpresaResponseDTO;
import com.example.hacka1.versionfinal.dtos.EmpresaRequestDTO;
import com.example.hacka1.versionfinal.dtos.EmpresasReponseDTO;
import com.example.hacka1.versionfinal.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GestionEmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Empresa> createEmpresaAdministrador(EmpresaRequestDTO empresaDTO) {
        Empresa EmpresaValido = modelMapper.map(empresaDTO, Empresa.class);
        if (EmpresaValido.getAdministrador() != null) {
            EmpresaValido.getAdministrador().setEmpresa(EmpresaValido);
        }
        return ResponseEntity.ok(empresaRepository.save(EmpresaValido));
    }


    public ResponseEntity<?> getAllEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        if (!empresas.isEmpty()) {
            List<EmpresasReponseDTO> response = empresas.stream()
                    .map(empresa -> {
                        EmpresasReponseDTO dto = new EmpresasReponseDTO();
                        dto.setId(empresa.getId());
                        dto.setNombre(empresa.getNombre());
                        dto.setFechaAfiliacion(empresa.getFechaAfiliacion());
                        dto.setIsActivo(empresa.getIsActivo());
                        if (empresa.getAdministrador() != null) {
                            dto.setNombreAdministrador(empresa.getAdministrador().getNombre());
                            dto.setApellidoAdministrador(empresa.getAdministrador().getApellido());
                        }
                        return dto;
                    }).toList();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body("NINGUNA EMPRESA ENCONTRADA -> NO EXISTEN EMPRESAS AÚN");
    }

    public ResponseEntity<?> getEmpresaByID(Long id) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        EmpresasReponseDTO dto = new EmpresasReponseDTO();
        if (optionalEmpresa.isPresent()) {
            Empresa valido = optionalEmpresa.get();
            dto.setId(valido.getId());
            dto.setNombre(valido.getNombre());
            dto.setFechaAfiliacion(valido.getFechaAfiliacion());
            dto.setIsActivo(valido.getIsActivo());
            if (valido.getAdministrador() != null) {
                dto.setNombreAdministrador(valido.getAdministrador().getNombre());
                dto.setApellidoAdministrador(valido.getAdministrador().getApellido());
            }
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(404).body("EMPRESA CON ID " + id + " NO ENCONTRADA");
    }

    public ResponseEntity<?> actualizarTodoEmpresaAdministrador(Long id, EmpresaRequestDTO actualizarEmpresaDTO) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        if (optionalEmpresa.isPresent()) {
            Empresa valido = optionalEmpresa.get();
            valido.setNombre(actualizarEmpresaDTO.getNombre());
            valido.setFechaAfiliacion(actualizarEmpresaDTO.getFechaAfiliacion());
            valido.setIsActivo(actualizarEmpresaDTO.getIsActivo());
            if (actualizarEmpresaDTO.getAdministrador() != null) {
                Administrador admin = valido.getAdministrador();
                admin.setNombre(actualizarEmpresaDTO.getAdministrador().getNombre());
                admin.setApellido(actualizarEmpresaDTO.getAdministrador().getApellido());
            } else {
                return ResponseEntity.status(400).body("NUEVO REQUEST NO TIENE ADMINISTRADOR A ACTUALIZAR");
            }
            return ResponseEntity.ok(empresaRepository.save(valido));
        }
        return ResponseEntity.status(404).body("NO ACTUALIZADO -> EMPRESA CON ID" + id + " NO ENCONTRADO");
    }

    public ResponseEntity<?> statusEmpresa(Long id, Boolean status) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        if (optionalEmpresa.isPresent()) {
            Empresa valido = optionalEmpresa.get();
            valido.setIsActivo(status);
            return ResponseEntity.ok(empresaRepository.save(valido));
        }
        return ResponseEntity.status(404).body("STATUS NO ACTUALIZADO -> EMPRESA CON ID" + id + " NO ENCONTRADO");
    }


    public ConsumoEmpresaResponseDTO calcularConsumoEmpresa(Long idEmpresa) {
        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new EntityNotFoundException("Empresa con id " + idEmpresa + "NO encontrada"));
        int total = 0;
        Map<String, Integer> porModelo = new HashMap<>();
        for (Usuario usuario : empresa.getUsuarios()) {
            for (Solicitud solicitud : usuario.getSolicitud()) {
                int tokens = solicitud.getTokensConsumidos();
                String modelo = solicitud.getModelo();
                total += tokens;
                porModelo.merge(modelo, tokens, Integer::sum);
            }
        }
        ConsumoEmpresaResponseDTO dto = new ConsumoEmpresaResponseDTO();
        dto.setIdEmpresa(empresa.getId());
        dto.setTotalTokens(total);
        dto.setConsumoPorModelo(porModelo);
        return dto;
    }
}
