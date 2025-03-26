package org.example.suanfa.project.dubboFilter.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResult implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int code;
    private String message;
}