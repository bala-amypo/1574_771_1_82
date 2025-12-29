@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    private final AnomalyRuleService service;

    public AnomalyRuleController(AnomalyRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AnomalyRule> create(@RequestBody AnomalyRule rule) {
        return new ResponseEntity<>(service.createRule(rule), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnomalyRule> update(@PathVariable Long id,
                                              @RequestBody AnomalyRule rule) {
        return ResponseEntity.ok(service.updateRule(id, rule));
    }

    @GetMapping("/active")
    public ResponseEntity<List<AnomalyRule>> active() {
        return ResponseEntity.ok(service.getActiveRules());
    }

    @GetMapping
    public ResponseEntity<List<AnomalyRule>> getAll() {
        return ResponseEntity.ok(service.getAllRules());
    }
}
