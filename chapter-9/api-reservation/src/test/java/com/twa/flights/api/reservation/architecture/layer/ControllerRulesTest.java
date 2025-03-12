package com.twa.flights.api.reservation.architecture.layer;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.twa.flights.api.reservation.architecture.general.ArchitectureConstants.*;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
public class ControllerRulesTest {

    @ArchTest
    static final ArchRule publicConstructorsAreOnlyAllowed =
            constructors()
                    .that()
                    .areDeclaredInClassesThat()
                    .resideInAPackage(CONTROLLER_PACKAGE)
                    .and()
                    .areDeclaredInClassesThat()
                    .areNotAnonymousClasses()
                    .should()
                    .bePublic()
                    .because("Public constructors are only allowed in " + CONTROLLER_PACKAGE);

    @ArchTest
    static final ArchRule publicMethodsShouldBeProperlyAnnotated =
            methods()
                    .that()
                    .areDeclaredInClassesThat()
                    .resideInAPackage(CONTROLLER_PACKAGE)
                    .and()
                    .arePublic()
                    .should()
                    .notBeAnnotatedWith(RequestMapping.class)
                    .andShould()
                    .notBeAnnotatedWith(ResponseBody.class)
                    .because(
                            "Controller endpoints should not be annotated with @RequestMapping or @ResponseBody");

    @ArchTest
    static final ArchRule classesShouldBeAnnotated =
            classes()
                    .that()
                    .resideInAPackage(CONTROLLER_PACKAGE)
                    .should()
                    .beAnnotatedWith(RestController.class)
                    .orShould()
                    .beAnnotatedWith(Controller.class)
                    .because(
                            ANNOTATED_EXPLANATION.formatted(CONTROLLER_SUFFIX, "@RestController"));

    @ArchTest
    static final ArchRule methodsShouldReturnResponseEntity =
            methods()
                    .that()
                    .arePublic()
                    .and()
                    .areDeclaredInClassesThat()
                    .resideInAPackage(CONTROLLER_PACKAGE)
                    .should()
                    .haveRawReturnType(ResponseEntity.class)
                    .because("Controller endpoints should return a ResponseEntity object");

    @ArchTest
    static final ArchRule classesInControllerShouldBeNamedProperly =
            classes()
                    .that()
                    .resideInAPackage(CONTROLLER_PACKAGE)
                    .should()
                    .haveSimpleNameEndingWith(CONTROLLER_SUFFIX)
                    .because(namingExplanation(CONTROLLER_PACKAGE, CONTROLLER_SUFFIX));
}
