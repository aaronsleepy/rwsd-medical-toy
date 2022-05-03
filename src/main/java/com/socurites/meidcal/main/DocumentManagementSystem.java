package com.socurites.meidcal.main;

import com.socurites.meidcal.importer.ImageImporter;
import com.socurites.meidcal.importer.Importer;

import java.util.HashMap;
import java.util.Map;

public class DocumentManagementSystem {
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new ImageImporter());
    }
}
