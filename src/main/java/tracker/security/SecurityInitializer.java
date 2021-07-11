package tracker.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{
	//Non occorre codice - Spring scansiona i package, trova la classe e inizializza la filterChain di sicurezza
}
