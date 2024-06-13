package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    Link findByShorted(String shorted);

    /*
    Демонстрация вызова хранимых процедур и вариантов написания запросов:

    @Query("select l from Link l")
    List<Link> findAllByHql();

    @Query(
            value = "SELECT * FROM links",
            nativeQuery = true
    )
    List<Link> findAllByNativeSql();

    @Procedure("insert_random")
    void insertRandom();

    @Procedure("get_сount_link_like")
    Integer getCountLinkLike(String param);
    * */
}
