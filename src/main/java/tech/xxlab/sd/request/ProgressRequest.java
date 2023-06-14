package tech.xxlab.sd.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tech.xxlab.sd.HttpMethodType;
import tech.xxlab.sd.response.ProgressResponse;

@Getter
@Setter
public class ProgressRequest extends BaseRequest<ProgressResponse> {

    @JsonProperty("id_task")
    private String idTask;

    @JsonProperty("id_live_preview")
    private int idLivePreview;

    public ProgressRequest() {
        super("/internal/progress", HttpMethodType.POST);
    }

    @Override
    public Class<ProgressResponse> responseClass() {
        return ProgressResponse.class;
    }
}
