package dev.julioperez.api.certificate.domain.model;

import java.util.Objects;
import java.util.UUID;

public record PdfRetrieverRequest(
        UUID studentId,
        UUID courseId
) {
    public boolean isInvalidFields(){
        return Objects.isNull(courseId()) ||
                Objects.isNull(studentId());
    }
}