package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.models.sdk.ModelSDK;

@Service
public class ModelService {

    @Autowired
    private ModelSDK modelSDK;

    public String generateText(String prompt) {
        return modelSDK.getOpenAI().generateText(prompt);
    }

    public String generateImage(String prompt) {
        return modelSDK.getMultimodal().generateImage(prompt);
    }

    public String synthesizeSpeech(String text) {
        return modelSDK.getDeepSpeak().synthesizeSpeech(text);
    }
}