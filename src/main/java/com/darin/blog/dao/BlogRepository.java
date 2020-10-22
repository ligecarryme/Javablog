package com.darin.blog.dao;

import com.darin.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Query(value = "select function('date_format',b.updateTime,'%M') as month from Blog b group by month order by month DESC",nativeQuery = true)
    List<String> findGroupByMonth();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%M') = ?1")
    List<Blog> findByMonth(String month);
}
