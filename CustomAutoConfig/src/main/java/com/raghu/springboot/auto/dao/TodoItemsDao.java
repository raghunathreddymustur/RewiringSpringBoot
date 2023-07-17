package com.raghu.springboot.auto.dao;

import com.raghu.springboot.auto.pojo.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemsDao extends CrudRepository<TodoItem, Integer> {

}
