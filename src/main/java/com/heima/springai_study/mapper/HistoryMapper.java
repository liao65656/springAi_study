package com.heima.springai_study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.springai_study.entity.History;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {
}
