package tech.xxlab.sd.request;

import tech.xxlab.sd.HttpMethodType;
import tech.xxlab.sd.response.ModelsResponse;

public class ModelsRequest extends BaseRequest<ModelsResponse> {

    public ModelsRequest() {
        super("/sdapi/v1/sd-models", HttpMethodType.POST);
    }

    @Override
    public Class<ModelsResponse> responseClass() {
        return ModelsResponse.class;
    }
}
