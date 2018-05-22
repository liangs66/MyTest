package com.liangs.test.elasticsearch.repository;

import com.liangs.test.elasticsearch.domain.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bysocket on 17/05/2017.
 */
@Repository
public interface AccountRepository extends ElasticsearchRepository<Account,Long> {


}
