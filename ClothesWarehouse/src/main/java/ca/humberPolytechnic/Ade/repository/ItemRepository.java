package ca.humberPolytechnic.Ade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ca.humberPolytechnic.Ade.model.Brand;
import ca.humberPolytechnic.Ade.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.brand = :brand AND i.yearOfCreation = 2025")
    Page<Item> findBrandIn2025(Brand brand, Pageable pageable);
}