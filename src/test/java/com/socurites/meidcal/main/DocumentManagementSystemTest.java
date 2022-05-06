package com.socurites.meidcal.main;

import com.socurites.meidcal.domain.Attributes;
import com.socurites.meidcal.domain.Document;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class DocumentManagementSystemTest {
    private static final String RESOURCES =
            "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "patient.report";
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";
    private static final String JOE_BLOGGS = "Joe Bloggs";

    private final DocumentManagementSystem system = new DocumentManagementSystem();

    @Test
    public void shouldImportFile() throws IOException {
        system.importFile(LETTER);
        final Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.PATH, LETTER);
    }

    private void assertAttributeEquals(final Document document, final String attributeName, final String expectedVaue) {
        assertThat(expectedVaue).isEqualTo(document.getAttribute(attributeName));
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        assertThat(documents.size()).isEqualTo(1);
        return documents.get(0);
    }
}
