package tech.xxlab.sd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModelsResponse extends BaseResponse {
    @JsonProperty("models")
    private List<ModelInfo> models;

    @Getter
    @Setter
    public static class ModelInfo {
        @JsonProperty("title")
        private String title;

        @JsonProperty("model_name")
        private String modelName;

        @JsonProperty("hash")
        private String hash;

        @JsonProperty("sha256")
        private String sha256;

        @JsonProperty("filename")
        private String filename;

        @JsonProperty("config")
        private String config;
    }
}
