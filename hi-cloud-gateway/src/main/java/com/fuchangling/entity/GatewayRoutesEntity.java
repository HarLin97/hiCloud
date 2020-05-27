package com.fuchangling.entity;

import lombok.Data;

/**
 * @author harlin
 */
@Data
public class GatewayRoutesEntity {

    private Long id;

    private String serviceId;

    private String uri;

    private String predicates;

    private String filters;

}