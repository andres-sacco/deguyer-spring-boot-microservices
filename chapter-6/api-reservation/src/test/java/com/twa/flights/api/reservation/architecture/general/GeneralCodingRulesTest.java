package com.twa.flights.api.reservation.architecture.general;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.slf4j.Logger;

@AnalyzeClasses(
        packages = ArchitectureConstants.DEFAULT_PACKAGE,
        importOptions = ImportOption.DoNotIncludeTests.class)
class GeneralCodingRulesTest {

    @ArchTest
    static final ArchRule loggersShouldBePrivateStaticAndFinal =
            fields().that()
                    .haveRawType(Logger.class)
                    .should()
                    .bePrivate()
                    .andShould()
                    .beStatic()
                    .andShould()
                    .beFinal()
                    .andShould()
                    .haveName("LOGGER")
                    .because(
                            "Logger variables should be private, static and final, and it should be named as LOGGER");
}
