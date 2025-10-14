package liqp.filters;

import liqp.Template;
import liqp.TemplateContext;
import liqp.TemplateParser;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Base64_EncodeTest {

    @Test
    public void applyTest() throws RecognitionException {

        String[][] tests = {
                {"{{'a' | base64_encode}}", "YQ=="},
                {"{{'' | base64_encode}}", ""},
                {"{{'hello' | base64_encode}}", "aGVsbG8="},
                {"{{'123' | base64_encode}}", "MTIz"},
        };

        for (String[] test : tests) {

            Template template = TemplateParser.DEFAULT.parse(test[0]);
            String rendered = String.valueOf(template.render());

            assertThat(rendered, is(test[1]));
        }
    }

    /*
     *
     */
    @Test
    public void applyOriginalTest() {
        Filter filter = Filters.COMMON_FILTERS.get("base64_encode");

        TemplateContext context = new TemplateContext();
        assertThat(filter.apply("hello", context), is((Object)"aGVsbG8="));
        assertThat(filter.apply(null, context), is((Object)""));
    }
}