package cl.ufro.bioren_backend.repository;

import cl.ufro.bioren_backend.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Equipment.
 */
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    // Ejemplo de método personalizado: buscar por unidad
    List<Equipment> findByLocationUnit(String locationUnit);
    Equipment findByInstitutionalId(String institutionalId);
    
    // Cargar equipo con relaciones de mantenimiento y usuario
    @Query("SELECT DISTINCT e FROM Equipment e " +
           "LEFT JOIN FETCH e.maintenanceRecords mr " +
           "LEFT JOIN FETCH mr.performedBy " +
           "LEFT JOIN FETCH e.encargado " +
           "WHERE e.id = :id")
    Optional<Equipment> findByIdWithMaintenanceRecords(@Param("id") Long id);
    
    // Cargar todos los equipos con relaciones
    @Query("SELECT DISTINCT e FROM Equipment e " +
           "LEFT JOIN FETCH e.maintenanceRecords mr " +
           "LEFT JOIN FETCH mr.performedBy " +
           "LEFT JOIN FETCH e.encargado")
    List<Equipment> findAllWithMaintenanceRecords();
    
    // Cargar equipos por unidad con relaciones
    @Query("SELECT DISTINCT e FROM Equipment e " +
           "LEFT JOIN FETCH e.maintenanceRecords mr " +
           "LEFT JOIN FETCH mr.performedBy " +
           "LEFT JOIN FETCH e.encargado " +
           "WHERE e.locationUnit = :locationUnit")
    List<Equipment> findByLocationUnitWithMaintenanceRecords(@Param("locationUnit") String locationUnit);
} 