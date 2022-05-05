package com.socurites.meidcal.domain;

import lombok.Builder;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@Getter
public class TextFile {
    private final Map<String, String> attributes;
    private final List<String> lines;

    public TextFile(final File file) throws IOException {
        this.attributes = new HashMap<>();
        this.attributes.put(Attributes.PATH, file.getPath());
        this.lines = Files.lines(file.toPath()).collect(Collectors.toList());
    }

    public void addLineSuffix(final String prefix, final String attributeName) {
        lines.stream()
                .filter(l -> l.startsWith(prefix))
                .findAny()
                .ifPresent(l -> attributes.put(attributeName, l.substring(prefix.length())));
    }
}
