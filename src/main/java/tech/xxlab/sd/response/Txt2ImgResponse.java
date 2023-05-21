package tech.xxlab.sd.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tech.xxlab.sd.request.Txt2ImgRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;

@Data
public class Txt2ImgResponse extends BaseResponse {
    private List<String> images;
    private Txt2ImgRequest parameters;
    private String info;

    @JsonIgnore // 忽略getter方法
    public BufferedImage[] getDecodedImages() throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        BufferedImage[] decodedImages = new BufferedImage[images.size()];
        for (int i = 0; i < images.size(); i++) {
            byte[] imageBytes = decoder.decode(images.get(i));
            decodedImages[i] = ImageIO.read(new ByteArrayInputStream(imageBytes));
        }
        return decodedImages;
    }
}
