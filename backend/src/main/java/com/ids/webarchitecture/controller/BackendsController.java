package com.ids.webarchitecture.controller;

import com.ids.webarchitecture.dto.BackendDto;
import com.ids.webarchitecture.model.NodeStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BackendsController {

    @GetMapping("/api/backends")
    public List<BackendDto> getBackends() {
        List result = new ArrayList<>();
        BackendDto dto1 = new BackendDto();
        dto1.setHost("localhost");
        dto1.setPort(8080);
        dto1.setStatus(NodeStatus.STARTED);

        BackendDto dto2 = new BackendDto();
        dto2.setHost("192.168.12.543");
        dto2.setPort(8081);
        dto2.setStatus(NodeStatus.STOPPED);

        result.add(dto1);
        result.add(dto2);
        return result;
    }

}
