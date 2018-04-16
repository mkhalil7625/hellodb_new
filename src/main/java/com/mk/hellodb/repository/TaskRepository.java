package com.mk.hellodb.repository;

import com.mk.hellodb.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepository extends PagingAndSortingRepository<Task,Long>{

List<Task> findAllByOrderByUrgentDesc();
}