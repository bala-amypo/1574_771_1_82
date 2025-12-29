@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService service;

    public AnomalyFlagController(AnomalyFlagService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AnomalyFlagRecord> create(@RequestBody AnomalyFlagRecord record) {
        return new ResponseEntity<>(service.flagAnomaly(record), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<AnomalyFlagRecord> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(service.resolveFlag(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AnomalyFlagRecord>> byEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getFlagsByEmployee(employeeId));
    }

    @GetMapping("/metric/{metricId}")
    public ResponseEntity<List<AnomalyFlagRecord>> byMetric(@PathVariable Long metricId) {
        return ResponseEntity.ok(service.getFlagsByMetric(metricId));
    }

    @GetMapping
    public ResponseEntity<List<AnomalyFlagRecord>> getAll() {
        return ResponseEntity.ok(service.getAllFlags());
    }
}
