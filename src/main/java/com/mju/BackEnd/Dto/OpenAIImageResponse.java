package com.mju.BackEnd.Dto;

import java.util.List;

public class OpenAIImageResponse {
    private long created;
    private List<ImageData> data;

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public List<ImageData> getData() {
        return data;
    }

    public void setData(List<ImageData> data) {
        this.data = data;
    }

    public static class ImageData {
        private String revised_prompt;
        private String url;

        public String getRevised_prompt() {
            return revised_prompt;
        }

        public void setRevised_prompt(String revised_prompt) {
            this.revised_prompt = revised_prompt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}