package org.example.suanfa.project.buff.service;

import java.util.List;

import org.example.suanfa.project.buff.vo.BuffVO;

// BuffService.java
public interface BuffService {
    List<BuffVO> getUserBuffs(Long userId);
    BuffVO upgradeBuff(Long userId, Integer buffId);
}