package com.jhs.mokoji.controller.response.common;

import lombok.Getter;

@Getter
public class SingleResponseData<T> extends ResponseData {
    private T data;

    protected SingleResponseData(T data) {
        super();
        this.data = data;
    }

    public static <T> SingleResponseData of(T data) {
        return new SingleResponseData(data);
    }
}