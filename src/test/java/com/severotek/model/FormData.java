package com.severotek.model;

public class FormData {
    private final String title;
    private final String slug;
    private final String markdown;
    private final String text;

    public FormData(String title, String slug, String markdown, String text) {
        this.title = title;
        this.slug = slug;
        this.markdown = markdown;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getMarkdown() {
        return markdown;
    }

    public String getText() {
        return text;
    }
}
