#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad of los Andes (Bogot� - Colombia)
# Departamento of Ingenier�a of Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n11_cupiTaxonomia
# Autor: Catalina Rodr�guez - 11-abr-2012
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Asegura la creaci�n of the directorio docs/api
# ---------------------------------------------------------

cd ../../docs
mkdir api
cd ../bin/mac

# ---------------------------------------------------------
# Genera la documentaci�n
# ---------------------------------------------------------

javadoc -encoding ISO-8859-1 -sourcepath ../../source -d ../../docs/api -subpackages uniandes.cupi2.cupiTaxonomy

stty echo
