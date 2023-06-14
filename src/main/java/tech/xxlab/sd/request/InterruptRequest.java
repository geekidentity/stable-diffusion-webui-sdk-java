package tech.xxlab.sd.request;

import tech.xxlab.sd.HttpMethodType;
import tech.xxlab.sd.response.InterruptResponse;

public class InterruptRequest extends BaseRequest<InterruptResponse> {

    public InterruptRequest() {
        super("/sdapi/v1/interrupt", HttpMethodType.POST);
    }

    @Override
    public Class<InterruptResponse> responseClass() {
        return InterruptResponse.class;
    }
}
