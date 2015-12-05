package org.apache.commons.lang.nonstatic;

import org.apache.commons.lang3.text.translate.duplicate.AggregateTranslator;
import org.apache.commons.lang3.text.translate.duplicate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.duplicate.EntityArrays;
import org.apache.commons.lang3.text.translate.duplicate.LookupTranslator;
import org.apache.commons.lang3.text.translate.duplicate.NumericEntityEscaper;
import org.apache.commons.lang3.text.translate.duplicate.UnicodeUnpairedSurrogateRemover;

public class SrvXml {

    /**
     * <p>Escapes the characters in a {@code String} using XML entities.</p>
     *
     * <p>For example: {@code "bread" & "butter"} =&gt;
     * {@code &quot;bread&quot; &amp; &quot;butter&quot;}.
     * </p>
     *
     * <p>Note that XML 1.0 is a text-only format: it cannot represent control
     * characters or unpaired Unicode surrogate codepoints, even after escaping.
     * {@code escapeXml10} will remove characters that do not fit in the
     * following ranges:</p>
     * 
     * <p>{@code #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]}</p>
     * 
     * <p>Though not strictly necessary, {@code escapeXml10} will escape
     * characters in the following ranges:</p>
     * 
     * <p>{@code [#x7F-#x84] | [#x86-#x9F]}</p>
     * 
     * <p>The returned string can be inserted into a valid XML 1.0 or XML 1.1
     * document. If you want to allow more non-text characters in an XML 1.1
     * document, use {@link #escapeXml11(String)}.</p>
     *
     * @param input  the {@code String} to escape, may be null
     * @return a new escaped {@code String}, {@code null} if null string input
     * @see #unescapeXml(java.lang.String)
     * @since 3.3
     */
    public String escapeXml10(final String input) {
        return escapeXml10.translate(input);
    }

    /**
     * Translator object for escaping XML 1.0.
     * 
     * While {@link #escapeXml10(String)} is the expected method of use, this
     * object allows the XML escaping functionality to be used
     * as the foundation for a custom translator.
     *
     * @since 3.3
     */
    public final CharSequenceTranslator escapeXml10 =
        new AggregateTranslator(
            new LookupTranslator(EntityArrays.BASIC_ESCAPE()),
            new LookupTranslator(EntityArrays.APOS_ESCAPE()),
            new LookupTranslator(
                    new String[][] {
                            { "\u0000", "" },
                            { "\u0001", "" },
                            { "\u0002", "" },
                            { "\u0003", "" },
                            { "\u0004", "" },
                            { "\u0005", "" },
                            { "\u0006", "" },
                            { "\u0007", "" },
                            { "\u0008", "" },
                            { "\u000b", "" },
                            { "\u000c", "" },
                            { "\u000e", "" },
                            { "\u000f", "" },
                            { "\u0010", "" },
                            { "\u0011", "" },
                            { "\u0012", "" },
                            { "\u0013", "" },
                            { "\u0014", "" },
                            { "\u0015", "" },
                            { "\u0016", "" },
                            { "\u0017", "" },
                            { "\u0018", "" },
                            { "\u0019", "" },
                            { "\u001a", "" },
                            { "\u001b", "" },
                            { "\u001c", "" },
                            { "\u001d", "" },
                            { "\u001e", "" },
                            { "\u001f", "" },
                            { "\ufffe", "" },
                            { "\uffff", "" }
                    }),
            NumericEntityEscaper.between(0x7f, 0x84),
            NumericEntityEscaper.between(0x86, 0x9f),
            new UnicodeUnpairedSurrogateRemover()
        );
    
}
