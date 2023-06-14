package tech.xxlab.sd.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tech.xxlab.sd.HttpMethodType;
import tech.xxlab.sd.response.Img2ImgResponse;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Img2ImgRequest extends BaseRequest<Img2ImgResponse>{

    @JsonProperty("init_images")
    private List<String> initImages;

    @JsonProperty("resize_mode")
    private int resizeMode;

    @JsonProperty("denoising_strength")
    private double denoisingStrength;

    @JsonProperty("image_cfg_scale")
    private int imageCfgScale;

    @JsonProperty("mask")
    private String mask;

    @JsonProperty("mask_blur")
    private int maskBlur;

    @JsonProperty("inpainting_fill")
    private int inPaintingFill;

    @JsonProperty("inpaint_full_res")
    private boolean inPaintFullRes;

    @JsonProperty("inpaint_full_res_padding")
    private int inPaintFullResPadding;

    @JsonProperty("inpainting_mask_invert")
    private int inPaintingMaskInvert;

    @JsonProperty("initial_noise_multiplier")
    private int initialNoiseMultiplier;

    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("styles")
    private List<String> styles;

    @JsonProperty("seed")
    private int seed;

    @JsonProperty("subseed")
    private int subSeed;

    @JsonProperty("subseed_strength")
    private double subSeedStrength;

    @JsonProperty("seed_resize_from_h")
    private int seedResizeFromH;

    @JsonProperty("seed_resize_from_w")
    private int seedResizeFromW;

    @JsonProperty("sampler_name")
    private String samplerName;

    @JsonProperty("batch_size")
    private int batchSize;

    @JsonProperty("n_iter")
    private int nIter;

    @JsonProperty("steps")
    private int steps;

    @JsonProperty("cfg_scale")
    private int cfgScale;

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    @JsonProperty("restore_faces")
    private boolean restoreFaces;

    @JsonProperty("tiling")
    private boolean tiling;

    @JsonProperty("do_not_save_samples")
    private boolean doNotSaveSamples;

    @JsonProperty("do_not_save_grid")
    private boolean doNotSaveGrid;

    @JsonProperty("negative_prompt")
    private String negativePrompt;

    @JsonProperty("eta")
    private int eta;

    @JsonProperty("s_min_uncond")
    private int sMinUnCond;

    @JsonProperty("s_churn")
    private int sChurn;

    @JsonProperty("s_tmax")
    private int sTmax;

    @JsonProperty("s_tmin")
    private int sTmin;

    @JsonProperty("s_noise")
    private int sNoise;

    @JsonProperty("override_settings")
    private Map<String, Object> overrideSettings;

    @JsonProperty("override_settings_restore_afterwards")
    private boolean overrideSettingsRestoreAfterwards;

    @JsonProperty("script_args")
    private List<String> scriptArgs;

    @JsonProperty("sampler_index")
    private String samplerIndex;

    @JsonProperty("include_init_images")
    private boolean includeInitImages;

    @JsonProperty("script_name")
    private String scriptName;

    @JsonProperty("send_images")
    private boolean sendImages;

    @JsonProperty("save_images")
    private boolean saveImages;

    @JsonProperty("alwayson_scripts")
    private Map<String, Object> alwaySonScripts;


    public Img2ImgRequest() {
        super("/sdapi/v1/img2img", HttpMethodType.POST);
    }

    @Override
    public Class<Img2ImgResponse> responseClass() {
        return Img2ImgResponse.class;
    }
}
