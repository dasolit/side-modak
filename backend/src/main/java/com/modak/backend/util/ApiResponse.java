package com.modak.backend.util;

import org.springframework.lang.Nullable;

public record ApiResponse<T> (
    boolean success,
    @Nullable int code,
    @Nullable T data
) {

  public static <T> ApiResponse<T> ok(@Nullable int code, @Nullable final T data) {
    return new ApiResponse<>(true, code, data);
  }


}
