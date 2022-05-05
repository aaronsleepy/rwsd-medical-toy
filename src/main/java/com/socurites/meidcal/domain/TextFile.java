package com.socurites.meidcal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Builder
@Getter
public class TextFile {
    private final Map<String, String> attributes;
    private final List<String> lines;
}
