import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webapi")
public class TestRest {

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/test-status")
    public ResponseEntity<String> testStatus(
            @RequestParam("value") String value
    ) {
        if ("ok".equalsIgnoreCase(value)) {
            return ResponseEntity.ok("everything is fine");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
