package no.dcat.configuration;

import no.dcat.webutils.exceptions.GlobalControllerExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerExceptionHandler extends GlobalControllerExceptionHandler {
}
