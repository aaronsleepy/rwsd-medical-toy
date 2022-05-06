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

    @Test
    public void shouldImportLetterAttribute() throws IOException {
        system.importFile(LETTER);
        final Document document = onlyDocument();

        assertTypeIs("LETTER", document);
        assertAttributeEquals(document, Attributes.PATIENT, "Joe Bloggs");
        assertAttributeEquals(document, Attributes.ADDRESS,
                "123 Fake Street\n" +
                        "Westminster\n" +
                        "London\n" +
                        "United Kingdom");
    }

    @Test
    public void shouldImportReportAttributes() throws IOException {
        system.importFile(REPORT);
        final Document document = onlyDocument();

        assertTypeIs("REPORT", document);
        assertAttributeEquals(document, Attributes.PATIENT, "Joe Bloggs");
    }

    @Test
    public void shouldImportImageAttributes() throws IOException {
        system.importFile(XRAY);
        final Document document = onlyDocument();

        assertTypeIs("IMAGE", document);
        assertAttributeEquals(document, Attributes.WIDTH, "320");
        assertAttributeEquals(document, Attributes.HEIGHT, "179");
    }

    private void assertAttributeEquals(final Document document, final String attributeName, final String expectedVaue) {
        assertThat(expectedVaue).isEqualTo(document.getAttribute(attributeName));
    }

    private void assertTypeIs(final String type, final Document document) {
        assertAttributeEquals(document, Attributes.TYPE, type);
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        assertThat(documents.size()).isEqualTo(1);
        return documents.get(0);
    }
}
