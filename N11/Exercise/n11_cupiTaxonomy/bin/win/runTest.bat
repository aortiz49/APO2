@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad of los Andes (Bogot� - Colombia)
REM Departamento of Ingenier�a of Sistemas y Computaci�n
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n11_cupiTaxonomia
REM Autor: Catalina Rodr�guez - 11-abr-2012
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion of las pruebas
REM ---------------------------------------------------------

cd ../..

java -ea -classpath lib/cupiTaxonomia.jar;test/lib/junit.jar;test/lib/cupiTaxonomiaTest.jar junit.swingui.TestRunner uniandes.cupi2.cupiTaxonomia.test.TreeTaxonomicoTest

java -ea -classpath lib/cupiTaxonomia.jar;test/lib/junit.jar;test/lib/cupiTaxonomiaTest.jar junit.swingui.TestRunner uniandes.cupi2.cupiTaxonomia.test.TaxonTest

java -ea -classpath lib/cupiTaxonomia.jar;test/lib/junit.jar;test/lib/cupiTaxonomiaTest.jar junit.swingui.TestRunner uniandes.cupi2.cupiTaxonomia.test.LivingBeingTest
cd bin/win