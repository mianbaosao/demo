package org.example.suanfa.project.buff.controller;

import java.util.List;

import org.example.suanfa.project.buff.service.BuffService;
import org.example.suanfa.project.buff.vo.BuffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buffs")
public class BuffController {
    @Autowired
    private  BuffService buffService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<BuffVO>> getUserBuffs(@PathVariable Long userId) {
        return ResponseEntity.ok(buffService.getUserBuffs(userId));
    }
    
    @PostMapping("/{userId}/upgrade/{buffId}")
    public ResponseEntity<BuffVO> upgradeBuff(
            @PathVariable Long userId,
            @PathVariable Integer buffId) {
        return ResponseEntity.ok(buffService.upgradeBuff(userId, buffId));
    }
}