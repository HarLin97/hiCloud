package com.fuchangling.system.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author harlin
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NacosResultDTO implements Serializable {

    /**
     * code : 200
     * message : null
     * data : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWNvcyIsImF1dGgiOiIiLCJleHAiOjE1ODg1OTEyMzZ9.loCPtXKX3eDZqTwqTt8gWicZaAtErxO47O9llHM91Os
     */

    private Integer code;
    private String message;
    private String data;

}
