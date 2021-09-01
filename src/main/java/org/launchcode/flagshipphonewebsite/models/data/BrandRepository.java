package org.launchcode.flagshipphonewebsite.models.data;

import org.launchcode.flagshipphonewebsite.models.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer> {

    }

