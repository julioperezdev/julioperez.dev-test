package dev.julioperez.api.certificate.domain.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class StudentCertificate{
    private UUID id;
    private UUID studentId;
    private String studentName;
    private UUID courseId;
    private String courseName;
    private Date createdAt;
    private String certificateHash;

    public StudentCertificate(UUID id, UUID studentId, String studentName, UUID courseId, String courseName, Date createdAt, String certificateHash) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.createdAt = createdAt;
        this.certificateHash = certificateHash;
    }

    public StudentCertificate(UUID id, UUID studentId, String studentName, UUID courseId, String courseName, Date createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCertificateHash() {
        return certificateHash;
    }

    public void setCertificateHash(String certificateHash) {
        this.certificateHash = certificateHash;
    }

    public boolean isInvalidFieldsWithinCertificateHash(){
        return Objects.isNull(id) ||
                Objects.isNull(courseId) ||
                Objects.isNull(courseName) ||
                Objects.isNull(studentId) ||
                Objects.isNull(studentName);
    }
    public boolean isInvalidFieldsWithoutCertificateHash(){
        return Objects.isNull(id) ||
                Objects.isNull(courseId) ||
                Objects.isNull(courseName) ||
                Objects.isNull(studentId) ||
                Objects.isNull(studentName) ||
                Objects.isNull(certificateHash);
    }
}
