package com.rep.repository.biquge;

import com.rep.common.BaseRepository;
import com.rep.entity.biquge.FictionDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "biQuGeFictionRepository")
public interface BiQuGeFictionRepository extends BaseRepository<FictionDetail> {
}
