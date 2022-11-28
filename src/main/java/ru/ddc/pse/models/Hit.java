package ru.ddc.pse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Hit {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("_score")
    private Long score;
    @JsonProperty("_source")
    private Map<String, String> source;
    @JsonProperty("highlight")
    private Map<String, List<String>> highlight;
}
