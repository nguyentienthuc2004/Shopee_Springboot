package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thuc.shoppe.service.SaveService;

@RestController
@RequestMapping("/api/save")
@RequiredArgsConstructor
public class SaveController {
    private final SaveService saveService;
    @GetMapping
    public String saveValue() {
        saveService.saveValue("so", 1);
        return "Value saved successfully";
    }
}
