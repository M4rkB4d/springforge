package dev.springforge.t1_04;

/**
 * Exercise 2: @Value injection
 *
 * Sometimes you just need one property, not a whole config class.
 * @Value injects a single property from application.yml.
 *
 * YOUR TASK:
 * 1. Annotate the class with @Component
 * 2. Use @Value("${app.feature.beta-enabled:false}") on the betaEnabled field
 * 3. Implement isBetaEnabled() to return the field value
 *
 * The ":false" part is the default — if the property isn't set, use false.
 */
// TODO: Add @Component
public class FeatureToggle {

    // TODO: Add @Value("${app.feature.beta-enabled:false}")
    private boolean betaEnabled;

    public boolean isBetaEnabled() {
        // TODO: Return betaEnabled field
        throw new UnsupportedOperationException("Implement isBetaEnabled()");
    }
}
