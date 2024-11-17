package com.example.usermanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
@Tag(name = "Error Handling", description = "Handles all application errors")
public class MyErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public MyErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    @Operation(summary = "Handle application errors", description = "Display error information to the user")
    public String handleError(WebRequest webRequest, Model model) {
        Map<String, Object> errors = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        model.addAllAttributes(errors);
        return "error/error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
