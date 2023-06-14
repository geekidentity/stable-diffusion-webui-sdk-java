package tech.xxlab.sd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgressResponse extends BaseResponse {
    @JsonProperty("active")
    private boolean active;

    @JsonProperty("queued")
    private boolean queued;

    @JsonProperty("completed")
    private boolean completed;

    @JsonProperty("progress")
    private int progress;

    @JsonProperty("eta")
    private int eta;

    @JsonProperty("live_preview")
    private String livePreview;

    @JsonProperty("id_live_preview")
    private int idLivePreview;

    @JsonProperty("textinfo")
    private String textInfo;
}
