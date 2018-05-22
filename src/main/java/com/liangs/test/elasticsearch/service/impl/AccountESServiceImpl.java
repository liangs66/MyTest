package com.liangs.test.elasticsearch.service.impl;

import com.liangs.test.elasticsearch.domain.Account;
import com.liangs.test.elasticsearch.repository.AccountRepository;
import com.liangs.test.elasticsearch.service.AccountService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class AccountESServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountESServiceImpl.class);

    @Autowired
    AccountRepository accountRepository;

    public Long saveAccount(Account account) {

        Account accountResult = accountRepository.save(account);
        return accountResult.getId();
    }

    /**
     *           "firstname": "Virginia",
     *           "lastname": "Ayala",
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
//    public List<Account> searchAccount(Integer pageNumber,
//                                 Integer pageSize,
//                                 String searchContent) {
//        // 分页参数
//        Pageable pageable = new PageRequest(pageNumber, pageSize);
//
//        // Function Score Query
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(null)
////                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("accountname", searchContent)),
////                    ScoreFunctionBuilders.weightFactorFunction(1000))
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("firstname", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(100));
//
//        // 创建搜索 DSL 查询
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withPageable(pageable)
//                .withQuery(functionScoreQueryBuilder).build();
//
//        LOGGER.info("\n searchAccount(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());
//
//        Page<Account> searchPageResults = accountRepository.search(searchQuery);
//        return searchPageResults.getContent();
//    }

}
