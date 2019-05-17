package com.example.readinglist.readinglist;

import java.util.Map;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> attributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
        attributes.put("foo", "bar");

        return attributes;
    }
}