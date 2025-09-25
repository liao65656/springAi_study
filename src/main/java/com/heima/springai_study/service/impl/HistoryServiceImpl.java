package com.heima.springai_study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.springai_study.entity.History;
import com.heima.springai_study.mapper.HistoryMapper;
import com.heima.springai_study.service.IHistoryService;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements IHistoryService {
}
