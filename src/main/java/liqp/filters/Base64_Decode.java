package liqp.filters;

import liqp.TemplateContext;
import java.util.Base64;

public class Base64_Decode extends Filter {

    /*
     * (Object) base64_decode(input)
     *
     * Decodes a Base64 encoded string.
     */
    @Override
    public Object apply(Object value, TemplateContext context, Object... params) {
        String encoded = super.asString(value, context);

        if (encoded.isEmpty()) {
            return encoded;
        }

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            // Invalid Base64 input - return empty string
            return "";
        }
    }
}

