package com.socurites.meidcal.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class Document {
    private final Map<String, String> attributes;

}
