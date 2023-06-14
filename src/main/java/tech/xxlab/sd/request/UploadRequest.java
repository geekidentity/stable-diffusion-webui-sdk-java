package tech.xxlab.sd.request;

import tech.xxlab.sd.HttpMethodType;
import tech.xxlab.sd.response.UploadResponse;

public class UploadRequest extends BaseRequest<UploadResponse>{

    public UploadRequest() {
        super("/upload", HttpMethodType.POST);
    }

    @Override
    public Class<UploadResponse> responseClass() {
        return UploadResponse.class;
    }
}
