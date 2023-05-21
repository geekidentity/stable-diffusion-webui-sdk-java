package tech.xxlab.sd;

import org.junit.Before;
import org.junit.Test;
import tech.xxlab.sd.contant.StableDiffusionConstant;
import tech.xxlab.sd.request.Txt2ImgRequest;
import tech.xxlab.sd.response.Txt2ImgResponse;

public class StableDiffusionClientTest {

    private StableDiffusionClient client;

    @Before
    public void init() {
        StableDiffusionClientConfiguration configuration = new StableDiffusionClientConfiguration();
        configuration.setHost("http://nas.xxlab.tech:7860");
        client = new StableDiffusionClient(configuration);
    }

    @Test
    public void txt2ImgTest() {
        Txt2ImgRequest request = new Txt2ImgRequest();
        request.setSamplerIndex(StableDiffusionConstant.SAMPLE_DPMPP_2M_KA);
        request.setPrompt("a cat");
        Txt2ImgResponse response = client.txt2Img(request);
        System.out.println(response.getInfo());
    }
}
