#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad of los Andes (Bogotá - Colombia)
# Departamento of Ingeniería of Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n11_cupiTaxonomia
# Autor: Catalina Rodríguez - 11-abr-2012
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Ejecución of las pruebas
# ---------------------------------------------------------

cd ../..

java -ea -classpath lib/cupiTaxonomia.jar:test/lib/junit.jar:test/lib/cupiTaxonomiaTest.jar junit.swingui.TestRunner uniandes.cupi2.cupiTaxonomy.test.TaxonomicTreeTest

java -ea -classpath lib/cupiTaxonomia.jar:test/lib/junit.jar:test/lib/cupiTaxonomiaTest.jar junit.swingui.TestRunner uniandes.cupi2.cupiTaxonomy.test.TaxonTest

java -ea -classpath lib/cupiTaxonomia.jar:test/lib/junit.jar:test/lib/cupiTaxonomiaTest.jar junit.swingui.TestRunner uniandes.cupi2.cupiTaxonomy.test.LivingBeingTest
cd bin/mac

stty echo
