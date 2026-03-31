package org.thuc.shoppe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;
import org.thuc.shoppe.entity.TemporaryTable;
import org.thuc.shoppe.repo.MemoryRepository;
import org.thuc.shoppe.service.SaveService;

@RequiredArgsConstructor
public class MemoryServiceImpl implements SaveService {
    private final MemoryRepository memoryRepository;

    @Override
    public void saveValue(String key, Object value) {
        TemporaryTable temporaryTable = new TemporaryTable();
        temporaryTable.setKeyTest(key);
        temporaryTable.setValueTest(value.toString());
        memoryRepository.save(temporaryTable);
    }
}
