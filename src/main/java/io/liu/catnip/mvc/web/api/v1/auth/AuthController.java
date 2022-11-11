package io.liu.catnip.mvc.web.api.v1.auth;

import io.liu.catnip.entity.dto.PasswordDTO;
import io.liu.catnip.model.APIResponseMap;
import io.liu.catnip.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    // Logger
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/password")
    public ResponseEntity<APIResponseMap> passwordAuth(@RequestBody @Validated PasswordDTO passwordDTO,
                                                       BindingResult bindingResult) {
        // 数据校验
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(APIResponseMap.FAILED(-1, bindingResult.getFieldError()));
        }

        String token = userService.authByPassword(passwordDTO);

        return ResponseEntity.ok(APIResponseMap.SUCCEEDED("", token));
    }

    @PostMapping("/sms_code")
    public ResponseEntity<APIResponseMap> smsCode() {
        return ResponseEntity.ok(APIResponseMap.SUCCEEDED(""));
    }

    @PostMapping("/sms_code/send")
    public ResponseEntity<APIResponseMap> sendSMSCode() {
        return ResponseEntity.ok(APIResponseMap.SUCCEEDED(""));
    }
}
