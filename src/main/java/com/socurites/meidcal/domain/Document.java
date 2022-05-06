package com.socurites.meidcal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class Document {
    private final Map<String, String> attributes;

    public String getAttribute(final String attributeName) {
        return this.attributes.get(attributeName);
    }
}
