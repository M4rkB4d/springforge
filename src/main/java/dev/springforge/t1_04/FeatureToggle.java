package dev.springforge.t1_04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Exercise 2: @Value injection — SOLUTION
 */
@Component
public class FeatureToggle {

    @Value("${app.feature.beta-enabled:false}")
    private boolean betaEnabled;

    public boolean isBetaEnabled() {
        return betaEnabled;
    }
}
