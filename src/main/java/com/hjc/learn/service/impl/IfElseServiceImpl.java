package com.hjc.learn.service.impl;

import com.hjc.learn.enums.IfElseDemo;
import com.hjc.learn.model.IfElseVO;
import com.hjc.learn.service.IfElseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author houjichao
 */
@Service
@Slf4j
public class IfElseServiceImpl implements IfElseService {

    @Override
    public void ifElseByEnum() {

        IfElseVO vo = new IfElseVO();
        vo.setOperator(1);
        vo.setName("houjichao");
        // 直接调用赋值 （实际业务逻辑根据需要自己改）
        StringBuilder stringBuilder = IfElseDemo.getMethodByType(vo.getOperator()).buildSql(vo, new StringBuilder());
        log.info("当type为1时：" + stringBuilder.toString());

    }
}
