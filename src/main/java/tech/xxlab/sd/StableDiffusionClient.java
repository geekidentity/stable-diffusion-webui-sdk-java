package tech.xxlab.sd;

import tech.xxlab.sd.exceptions.SDKClientException;
import tech.xxlab.sd.request.*;
import tech.xxlab.sd.response.*;

public class StableDiffusionClient extends BaseClient {

    public StableDiffusionClient(StableDiffusionClientConfiguration configuration) {
        super(configuration);
    }

    public Txt2ImgResponse txt2Img(Txt2ImgRequest request) throws SDKClientException {
        return getResponseModel(request);
    }

    public Img2ImgResponse img2img(Img2ImgRequest request) throws SDKClientException {
        return getResponseModel(request);
    }

    public ModelsResponse listModels() throws SDKClientException {
        return getResponseModel(new ModelsRequest());
    }

    public UploadResponse upload(UploadRequest request) throws SDKClientException {
        return getResponseModel(request);
    }

    public ProgressResponse progress(ProgressRequest request) throws SDKClientException {
        return getResponseModel(request);
    }

    public InterruptResponse interrupt() throws SDKClientException {
        return getResponseModel(new InterruptRequest());
    }
}
