package dev.julioperez.api.certificate.domain.model;

import java.util.UUID;

public record PdfBatch(
        UUID id,
        int attempt) {}
