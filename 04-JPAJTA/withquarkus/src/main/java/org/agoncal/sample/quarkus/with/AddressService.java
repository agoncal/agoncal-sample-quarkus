package org.agoncal.sample.quarkus.with;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */

@ApplicationScoped
public class AddressService {

    @Inject
    EntityManager em;

    @Transactional(REQUIRED)
    public Address save(Address address) {
        em.persist(address);
        return address;
    }

    @Transactional(SUPPORTS)
    public Address findById(Long id) {
        return em.find(Address.class, id);
    }
}
