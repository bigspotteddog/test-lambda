package hello;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.bigspotteddog.number.translator.NumberTranslator;

public class Hello {

    public String myHandler(String input, Context context) throws IOException {
        String words = translate(input);

        LambdaLogger logger = context.getLogger();
        StringBuilder result = new StringBuilder("");
        result.append(words);

        logger.log(result.toString());
        return result.toString();
    }

    private String translate(String input) throws IOException {
        long number = 0;
        try {
            String numberToTranslate = input;
            numberToTranslate = numberToTranslate.replaceAll(",", "");
            numberToTranslate = numberToTranslate.split("\\.")[0];

            number = Long.parseLong(numberToTranslate);
        } catch (NumberFormatException e) {
            return "The number entered is not valid.";
        }

        NumberTranslator translator = new NumberTranslator();
        String words = translator.translate(number, "en-US");

        return words;
    }

}
