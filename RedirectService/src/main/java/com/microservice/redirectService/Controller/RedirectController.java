package com.microservice.redirectService.Controller;

import com.microservice.redirectService.Service.RedirectService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Getter
@Setter
@RequiredArgsConstructor
@RestController
@RequestMapping("/shortener")
public class RedirectController {

    private final RedirectService redirectService;

    @GetMapping("/{hash}")
    @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
    public RedirectView redirectTo(@PathVariable String hash) throws Exception {
        return redirectService.redirectTo(hash);
    }
}
