package tech.xxlab.sd.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tech.xxlab.sd.HttpMethodType;
import tech.xxlab.sd.response.Txt2ImgResponse;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Txt2ImgRequest extends BaseRequest<Txt2ImgResponse> {
    public Txt2ImgRequest() {
        super("/sdapi/v1/txt2img", HttpMethodType.POST);
    }

    @JsonProperty("enable_hr")
    private boolean enableHr = false;

    @JsonProperty("denoising_strength")
    private int denoisingStrength = 0;

    @JsonProperty("firstphase_width")
    private int firstPhaseWidth = 0;

    @JsonProperty("firstphase_height")
    private int firstPhaseHeight = 0;

    @JsonProperty("hr_scale")
    private int hrScale = 2;

    @JsonProperty("hr_upscaler")
    private String hrUpScaler;

    @JsonProperty("hr_second_pass_steps")
    private int hrSecondPassSteps = 0;

    @JsonProperty("hr_resize_x")
    private int hrResizeX = 0;

    @JsonProperty("hr_resize_y")
    private int hrResizeY = 0;

    @JsonProperty("prompt")
    private String prompt = "a girl";

    @JsonProperty("styles")
    private List<String> styles;

    @JsonProperty("seed")
    private int seed = -1;

    @JsonProperty("subseed")
    private int subSeed = -1;

    @JsonProperty("subseed_strength")
    private int subSeedStrength = 0;

    @JsonProperty("seed_resize_from_h")
    private int seedResizeFromH = -1;

    @JsonProperty("seed_resize_from_w")
    private int seedResizeFromW = -1;

    @JsonProperty("sampler_name")
    private String samplerName;

    @JsonProperty("batch_size")
    private int batchSize = 1;

    @JsonProperty("n_iter")
    private int nIter = 1;

    @JsonProperty("steps")
    private int steps = 50;

    @JsonProperty("cfg_scale")
    private int cfgScale = 7;

    @JsonProperty("width")
    private int width = 512;

    @JsonProperty("height")
    private int height = 512;

    @JsonProperty("restore_faces")
    private boolean restoreFaces = false;

    @JsonProperty("tiling")
    private boolean tiling = false;

    @JsonProperty("do_not_save_samples")
    private boolean doNotSaveSamples = false;

    @JsonProperty("do_not_save_grid")
    private boolean doNotSaveGrid = false;

    @JsonProperty("negative_prompt")
    private String negativePrompt = "string";

    @JsonProperty("eta")
    private int eta = 0;

    @JsonProperty("s_min_uncond")
    private int sMinUncond = 0;

    @JsonProperty("s_churn")
    private int sChurn = 0;

    @JsonProperty("s_tmax")
    private int sTmax = 0;

    @JsonProperty("s_tmin")
    private int sTmin = 0;

    @JsonProperty("s_noise")
    private int sNoise = 1;

    @JsonProperty("override_settings")
    private Map<String, String> overrideSettings;

    @JsonProperty("override_settings_restore_afterwards")
    private boolean overrideSettingsRestoreAfterwards = true;

    @JsonProperty("script_args")
    private List<String> scriptArgs = new ArrayList<>();

    @NotNull
    @JsonProperty("sampler_index")
    private String samplerIndex;

    @JsonProperty("script_name")
    private String scriptName = "";

    @JsonProperty("send_images")
    private boolean sendImages = true;

    @JsonProperty("save_images")
    private boolean saveImages;

    @JsonProperty("alwayson_scripts")
    private Map<String, String> alwaysOnScripts = new HashMap<>();

    @Override
    public Class<Txt2ImgResponse> responseClass() {
        return Txt2ImgResponse.class;
    }
}
