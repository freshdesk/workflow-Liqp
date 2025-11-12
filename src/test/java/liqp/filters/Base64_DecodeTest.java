package liqp.filters;

import liqp.Template;
import liqp.TemplateContext;
import liqp.TemplateParser;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Base64_DecodeTest {

    @Test
    public void applyTest() throws RecognitionException {

        String[][] tests = {
                {"{{'YQ==' | base64_decode}}", "a"},
                {"{{'' | base64_decode}}", ""},
                {"{{'aGVsbG8=' | base64_decode}}", "hello"},
                {"{{'MTIz' | base64_decode}}", "123"},
                {"{{'SGVsbG8gV29ybGQh' | base64_decode}}", "Hello World!"},
        };

        for (String[] test : tests) {

            Template template = TemplateParser.DEFAULT.parse(test[0]);
            String rendered = String.valueOf(template.render());

            assertThat(rendered, is(test[1]));
        }
    }

    @Test
    public void applyOriginalTest() {
        Filter filter = Filters.COMMON_FILTERS.get("base64_decode");

        TemplateContext context = new TemplateContext();
        assertThat(filter.apply("aGVsbG8=", context), is("hello"));
        assertThat(filter.apply(null, context), is(""));
    }

    @Test
    public void applyInvalidBase64Test() {
        Filter filter = Filters.COMMON_FILTERS.get("base64_decode");

        TemplateContext context = new TemplateContext();
        // Invalid Base64 strings should return empty string
        assertThat(filter.apply("not-valid-base64!!!", context), is(""));
    }
}

