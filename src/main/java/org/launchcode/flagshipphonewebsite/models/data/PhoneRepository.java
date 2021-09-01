package org.launchcode.flagshipphonewebsite.models.data;

import org.launchcode.flagshipphonewebsite.models.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PhoneRepository extends CrudRepository<Phone, Integer> {

    Iterable <Phone> findByBrandId(int brandId);

}

