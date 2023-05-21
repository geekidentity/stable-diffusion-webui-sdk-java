package tech.xxlab.sd;

import tech.xxlab.sd.exceptions.SDKClientException;
import tech.xxlab.sd.request.Txt2ImgRequest;
import tech.xxlab.sd.response.Txt2ImgResponse;

public class StableDiffusionClient extends BaseClient {

    public StableDiffusionClient(StableDiffusionClientConfiguration configuration) {
        super(configuration);
    }

    public Txt2ImgResponse txt2Img(Txt2ImgRequest request) {
        try {
            return getResponseModel(request);
        } catch (SDKClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
