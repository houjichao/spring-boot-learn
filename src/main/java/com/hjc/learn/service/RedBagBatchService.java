package com.hjc.learn.service;

import java.math.BigDecimal;

/**
 * @author houjichao
 */
public interface RedBagBatchService {

    /**
     *
     * @param userId
     * @param redBagId
     * @return
     */
    BigDecimal grabRedBag(Long userId, Long redBagId);
}
