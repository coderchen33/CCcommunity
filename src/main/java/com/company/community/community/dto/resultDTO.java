package com.company.community.community.dto;

import com.company.community.community.exception.CustomizeErrorCode;
import com.company.community.community.exception.CustomizeException;
import lombok.Data;

@Data
public class resultDTO {
    private Integer code;
    private String message;

    public static resultDTO errorOf(Integer code,String message){
        resultDTO resultDTO = new resultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static resultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static resultDTO errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(),ex.getMessage());
    }

    public static resultDTO okOf() {
        resultDTO resultDTO = new resultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("登录成功");
        return resultDTO;
    }

}
