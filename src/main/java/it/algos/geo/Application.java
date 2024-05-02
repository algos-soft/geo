package it.algos.geo;

import com.vaadin.flow.component.dependency.*;
import com.vaadin.flow.component.page.*;
import com.vaadin.flow.theme.*;
import it.algos.geo.boot.*;
import it.algos.vbase.backend.boot.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.service.*;
import it.algos.vbase.backend.wrapper.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.*;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.*;
import org.springframework.core.env.*;
import org.springframework.scheduling.annotation.*;

import javax.inject.*;
import java.util.*;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"it.algos"})
@Theme(value = "geo")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@NpmPackage(value = "@vaadin-component-factory/vcf-nav", version = "1.0.6")
public class Application implements AppShellConfigurator {

    @Inject
    public Environment environment;

    @Inject
    ApplicationContext applicationContext;

    @Inject
    protected LoggerService logger;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /**
     * Primo ingresso nel programma <br>
     * <p>
     * 1) Prima SpringBoot crea tutte le classi SINGLETON, col metodo constructor() <br>
     * 2) Dopo SpringBoot esegue tutti i metodi con l'annotation @PostConstruct delle classi SINGLETON appena costruite <br>
     * 3) Infine SpringBoot arriva qui <br>
     */
    @EventListener(ContextRefreshedEvent.class)
    private void doSomethingAfterStartup() {
        GeoBoot currentBoot = null;
        String property;
        String message;
        String bootClazzQualifier = VUOTA;
        Class bootClazz = null;

        try {
            property = "algos.project.boot.qualifier";
            bootClazzQualifier = Objects.requireNonNull(environment.getProperty(property));
        } catch (Exception unErrore) {
            logger.error(new WrapLog().exception(new Exception(unErrore)).type(TypeLog.startup));
        }

        try {
            currentBoot = (GeoBoot) applicationContext.getBean(bootClazzQualifier);
        } catch (Exception unErrore) {
            logger.error(new WrapLog().exception(new Exception(unErrore)).type(TypeLog.startup));
        }

        if (currentBoot != null) {
            currentBoot.inizia();
        }
        else {
            if (bootClazz == null) {
                message = String.format("La variabile generale %s non può essere nulla", "BaseVar.bootClazz");
            }
            else {
                message = String.format("Non ho trovato nessuna classe di Boot con 'qualifier'=[%s]", bootClazzQualifier);
            }
            logger.error(new WrapLog().exception(new Exception(message)).type(TypeLog.startup));
        }
    }

}
