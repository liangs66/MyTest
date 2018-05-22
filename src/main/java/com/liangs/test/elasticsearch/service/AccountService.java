
package com.liangs.test.elasticsearch.service;


import com.liangs.test.elasticsearch.domain.Account;

import java.util.List;

public interface AccountService {

    /**
     * 新增城市信息
     *
     * @param account
     * @return
     */
    Long saveAccount(Account account);

    /**
     * 根据关键词，function score query 权重分分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
//    List<Account> searchAccount(Integer pageNumber, Integer pageSize, String searchContent);
}