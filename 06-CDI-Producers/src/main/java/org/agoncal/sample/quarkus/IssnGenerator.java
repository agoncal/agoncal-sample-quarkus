package org.agoncal.sample.quarkus;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@ApplicationScoped
@EightDigits
public class IssnGenerator implements NumberGenerator {

    @Override
    public String generateNumber() {
        return "8-" + Math.abs(new Random().nextInt());
    }
}
