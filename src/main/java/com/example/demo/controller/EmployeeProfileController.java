@PostMapping
public ResponseEntity<EmployeeProfileDto> create(
        @RequestBody EmployeeProfile employee) {

    EmployeeProfile saved = service.createEmployee(employee);

    EmployeeProfileDto dto = new EmployeeProfileDto();
    dto.setId(saved.getId());
    dto.setEmployeeId(saved.getEmployeeId());
    dto.setFullName(saved.getFullName());
    dto.setEmail(saved.getEmail());
    dto.setTeamName(saved.getTeamName());
    dto.setRole(saved.getRole());
    dto.setActive(saved.getActive());
    dto.setCreatedAt(saved.getCreatedAt());

    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
}
