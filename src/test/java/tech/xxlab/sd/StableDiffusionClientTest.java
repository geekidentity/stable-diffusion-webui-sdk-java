package tech.xxlab.sd;

import org.junit.Before;
import org.junit.Test;
import tech.xxlab.sd.contant.StableDiffusionConstant;
import tech.xxlab.sd.request.Txt2ImgRequest;
import tech.xxlab.sd.response.Txt2ImgResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class StableDiffusionClientTest {

    private StableDiffusionClient client;

    @Before
    public void init() {
        StableDiffusionClientConfiguration configuration = new StableDiffusionClientConfiguration();
        configuration.setHost("http://localhost:7860");
        client = new StableDiffusionClient(configuration);
    }

    @Test
    public void txt2ImgTest() throws Exception {
        Txt2ImgRequest request = new Txt2ImgRequest();
        request.setSamplerIndex(StableDiffusionConstant.SAMPLE_DPMPP_2M_KA);
        request.setPrompt("a cat");
        Txt2ImgResponse response = client.txt2Img(request);

        // 获取解码后的缓冲图像数组
        BufferedImage[] decodedImages = response.getDecodedImages();

        // 将缓冲图像保存到本地文件或输出流中
        for (int i = 0; i < decodedImages.length; i++) {
            File outputFile = new File("output" + i + ".png");
            ImageIO.write(decodedImages[i], "png", outputFile);

            OutputStream output = new FileOutputStream(outputFile);
            ImageIO.write(decodedImages[i], "png", output);
        }
    }
}
