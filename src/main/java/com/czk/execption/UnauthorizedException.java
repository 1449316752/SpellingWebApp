package com.czk.execption;

import com.czk.tools.Result;

public class UnauthorizedException extends RuntimeException {

    private Result result;

    public UnauthorizedException(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}

