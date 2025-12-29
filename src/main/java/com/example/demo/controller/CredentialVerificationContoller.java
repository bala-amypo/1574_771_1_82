@RestController
@RequestMapping("/api/credentials")
public class CredentialVerificationController {

    private final CredentialVerificationService service;

    public CredentialVerificationController(CredentialVerificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Credential> register(
            @RequestBody CredentialRequestDto dto) {

        Credential credential = new Credential();
        EmployeeProfile emp = new EmployeeProfile();
        emp.setId(dto.getEmployeeId());

        credential.setEmployee(emp);
        credential.setCredentialId(dto.getCredentialId());
        credential.setIssuer(dto.getIssuer());
        credential.setIssuedAt(dto.getIssuedAt());
        credential.setExpiresAt(dto.getExpiresAt());
        credential.setMetadataJson(dto.getMetadataJson());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.registerCredential(credential));
    }

    @PostMapping("/{credentialId}/verify")
    public ResponseEntity<CredentialStatusDto> verify(
            @PathVariable String credentialId) {

        return ResponseEntity.ok(service.verifyCredential(credentialId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Credential>> byEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(service.getCredentialsForEmployee(employeeId));
    }
}
