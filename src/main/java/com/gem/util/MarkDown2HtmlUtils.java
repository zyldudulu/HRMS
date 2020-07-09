package com.gem.util;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.util.Arrays;

public class MarkDown2HtmlUtils {
    /**
     * 直接将markdown语义的文本转为html格式输出
     * @param content markdown语义文本
     * @return
     */
    public static String markdown2Html(String content) {
        String html = parse(content);
        return html;
    }
    /**
     * markdown to image
     * @param content markdown contents
     * @return parse html contents
     */
    public static String parse(String content) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        //enable table parse!
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(content);
        return renderer.render(document);
    }

    public static  void main(String[] args)
    {
        String s="### abc";
        System.out.println(markdown2Html(s));
    }
}
