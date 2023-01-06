package org.rost.study;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YandexResponse {
    List<Translation> translations;

    @Override
    public String toString() {
        return translations != null
                ? translations.stream()
                .map(Translation::getText)
                .collect(Collectors.joining("\n"))
                : "";
    }
}
