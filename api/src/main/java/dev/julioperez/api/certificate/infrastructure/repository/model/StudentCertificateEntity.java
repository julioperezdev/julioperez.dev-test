package dev.julioperez.api.certificate.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(schema = "CERTIFICATE", name = "STUDENT_CERTIFICATE")
public class StudentCertificateEntity {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(name="STUDENT_ID",nullable = false)
    private UUID studentId;
    @Column(name="STUDENT_NAME",nullable = false)
    private String studentName;
    @Column(name="COURSE_ID",nullable = false)
    private UUID courseId;
    @Column(name="COURSE_NAME",nullable = false)
    private String courseName;
    @Column(name="CREATED_AT",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name="CERTIFICATE_HASH")
    private String certificateHash;
}
