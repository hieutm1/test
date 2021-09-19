package com.quocminh.home;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.quocminh.home");

        noClasses()
            .that()
            .resideInAnyPackage("com.quocminh.home.service..")
            .or()
            .resideInAnyPackage("com.quocminh.home.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.quocminh.home.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
