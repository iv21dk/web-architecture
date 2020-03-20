package com.ids.webarchitecture.dto;

import com.ids.webarchitecture.model.NodeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BackendDto {
    private String host;
    private Integer port;
    private NodeStatus status;
}
