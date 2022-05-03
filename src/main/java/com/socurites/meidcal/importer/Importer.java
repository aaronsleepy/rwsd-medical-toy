package com.socurites.meidcal.importer;

import java.io.File;
import java.io.IOException;

public interface Importer {
    Document importFile(File file) throws IOException;
}
