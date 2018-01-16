package org.apereo.cas.configuration.model.support.jpa.ticketregistry;

import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.configuration.model.core.util.EncryptionRandomizedSigningJwtCryptographyProperties;
import org.apereo.cas.configuration.model.support.jpa.AbstractJpaProperties;
import org.apereo.cas.configuration.support.RequiresModule;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import javax.persistence.LockModeType;
import lombok.Getter;
import lombok.Setter;

/**
 * Common properties for jpa ticket reg.
 *
 * @author Dmitriy Kopylenko
 * @since 5.0.0
 */
@RequiresModule(name = "cas-server-support-jpa-ticket-registry")
@Slf4j
@Getter
@Setter
public class JpaTicketRegistryProperties extends AbstractJpaProperties {

    /**
     * Default lock timeout is 1 hour.
     */
    public static final String DEFAULT_LOCK_TIMEOUT = "PT1H";

    private static final long serialVersionUID = -8053839523783801072L;

    /**
     * Ticket locking type. Acceptable values are
     * {@code READ,WRITE,OPTIMISTIC,OPTIMISTIC_FORCE_INCREMENT,PESSIMISTIC_READ,
     * PESSIMISTIC_WRITE,PESSIMISTIC_FORCE_INCREMENT,NONE}.
     */
    private LockModeType ticketLockType = LockModeType.NONE;

    /**
     * Indicates the lock duration when one is about to be acquired by the cleaner.
     */
    private String jpaLockingTimeout = DEFAULT_LOCK_TIMEOUT;

    /**
     * Crypto settings for the registry.
     */
    @NestedConfigurationProperty
    private EncryptionRandomizedSigningJwtCryptographyProperties crypto = new EncryptionRandomizedSigningJwtCryptographyProperties();

    public JpaTicketRegistryProperties() {
        super.setUrl("jdbc:hsqldb:mem:cas-ticket-registry");
        this.crypto.setEnabled(false);
    }
}
