package com.socurites.meidcal.main;

import com.socurites.meidcal.domain.Document;
import com.socurites.meidcal.exception.UnknownFileTypeException;
import com.socurites.meidcal.importer.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.*;

public class DocumentManagementSystem {
    private final List<Document> documents = new ArrayList<>();
    private final Map<String, Importer> extensionToImporter = new HashMap<>();
    private final List<Document> documentsView = Collections.unmodifiableList(this.documents);

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("invoice", new InvoiceImporter());
        extensionToImporter.put("jpg", new ImageImporter());
    }

    public void importFile(final String path) throws IOException {
        final File file = new File(path);
        if (!file.exists()) {
            throw new FileSystemNotFoundException(path);
        }

        final int separatorIndex = path.lastIndexOf('.');
        if (separatorIndex != -1) {
            if (separatorIndex == path.length()) {
                throw new UnknownFileTypeException("No extension found for file: " + path);
            }

            final String extension = path.substring(separatorIndex + 1);
            final Importer importer = extensionToImporter.get(extension);
            if (null == importer) {
                throw new UnknownFileTypeException("For extension: " + extension);
            }

            final Document document = importer.importFile(file);
            documents.add(document);
        }
    }

    public List<Document> contents() {
        return documentsView;
    }
}
