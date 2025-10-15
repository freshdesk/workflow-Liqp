package liqp.filters;

import liqp.TemplateContext;
import java.util.Base64;

public class Base64_Encode extends Filter {

    /*
     * (Object) base64_encode(input)
     *
     * Encodes the input string to Base64 format.
     */
    @Override
    public Object apply(Object value, TemplateContext context, Object... params) {
        String original = super.asString(value, context);

        if (original.isEmpty()) {
            return original;
        }

        return Base64.getEncoder().encodeToString(original.getBytes());
    }
}
