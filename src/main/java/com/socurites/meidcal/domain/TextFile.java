package com.socurites.meidcal.domain;

import lombok.Builder;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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

    public int addLines(final int start, final Predicate<String> isEnd, final String attributeName) {
        String accumulator = "";
        int lineNumber;
        for (lineNumber = start; lineNumber < lines.size(); lineNumber++) {
            final String line = lines.get(lineNumber);
            if (false == isEnd.test(line)) {
                accumulator += line;
                accumulator += "\n";
            }
        }

        this.attributes.put(attributeName, accumulator.trim());
        return lineNumber;
    }
}
