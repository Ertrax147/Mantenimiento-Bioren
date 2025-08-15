package cl.ufro.bioren_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entidad que representa un registro de mantenimiento de un equipo.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceRecord {
    /** Identificador único del registro de mantenimiento */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Fecha del mantenimiento (ISO) */
    private LocalDate date;

    /** Descripción del mantenimiento realizado */
    private String description;

    /** Usuario responsable que realizó el mantenimiento */
    @ManyToOne
    @JoinColumn(name = "performed_by_user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "invitationTokens"})
    private User performedBy;

    /** Lista de archivos adjuntos (nombre y URL) */
    @ElementCollection
    private List<Attachment> attachments;

    /** Equipo al que pertenece este registro */
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @JsonBackReference
    private Equipment equipment;

    /**
     * Clase embebida para adjuntos de mantenimiento.
     */
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attachment {
        private String name;
        private String url;
    }

    /**
     * DTO para representar un registro de mantenimiento en las respuestas API.
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MaintenanceRecordDTO {
        private Long id;
        private LocalDate date;
        private String description;
        private UserDTO performedBy;
        private List<Attachment> attachments;
        private Long equipmentId;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class UserDTO {
            private Long id;
            private String name;
            private String email;
            private String role;
            private String unit;
        }
    }
}
