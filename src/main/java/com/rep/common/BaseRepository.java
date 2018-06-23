package com.rep.common;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "baseRepository")
public interface BaseRepository<T> extends CrudRepository<T,Long> {


}
