package dev.julioperez.api.certificate.infrastructure.delivery;

import dev.julioperez.api.certificate.application.createStudentCertificate.delivery.StudentCertificateToCreate;
import dev.julioperez.api.certificate.domain.port.createStudentCertificate.CreateStudentCertificateInputPort;
import dev.julioperez.api.shared.infrastructure.delivery.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/certificate/create/student/certificate")
@AllArgsConstructor
public class SpringCreateStudentCertificateController {

    private final CreateStudentCertificateInputPort createStudentCertificateInputPort;

    @PostMapping
    public RestResponse<Boolean> createStudentCertificate(@RequestBody StudentCertificateToCreate studentCertificateToCreate){
        createStudentCertificateInputPort.createStudentCertificate(studentCertificateToCreate);
        return new RestResponse<>(HttpStatus.CREATED);
    }
}
