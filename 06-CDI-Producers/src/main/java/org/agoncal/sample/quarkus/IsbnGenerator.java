package org.agoncal.sample.quarkus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@ApplicationScoped
@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

    @Override
    public String generateNumber() {
        return "13-" + Math.abs(new Random().nextInt());
    }
}
