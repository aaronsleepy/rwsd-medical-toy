package com.socurites.meidcal.importer;

import com.socurites.meidcal.domain.Document;

import java.io.File;
import java.io.IOException;

public interface Importer {
    Document importFile(final File file) throws IOException;
}
