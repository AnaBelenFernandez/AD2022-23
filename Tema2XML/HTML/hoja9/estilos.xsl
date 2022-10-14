<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>
    <xsl:variable name="newLine" select="'&#10;'"/>
    <xsl:template match="/">
        <HTML>
            <xsl:apply-templates/>
        </HTML>
    </xsl:template>
    <xsl:template match="Departamentos">
        <head>
            <title>LISTADO DE DEPARTAMENTOS</title>
        </head>
        <body>
            <h1>LISTA DE DEPARTAMENTOS</h1>
            <table border="1">
                <tr>
                    <th>NUMERO</th>
                    <th>NOMBRE</th>
                    <th>LOCALIDAD</th>
                    <th>EMPLEADOS</th>
                </tr>
                <xsl:apply-templates select="Departamentos"/>
            </table>
        </body>
    </xsl:template>
    <xsl:template match="departamento">
        <TR>
            <xsl:apply-templates/>
        </TR>
    </xsl:template>

    <xsl:template match="numero">
        <TD STYLE="font-size:14pt font-family:serif">
            <xsl:value-of select="."/>
        </TD>
    </xsl:template>

    <xsl:template match="nombre">
    <TD>
        <xsl:value-of select="."/>
    </TD>
    </xsl:template>

    <xsl:template match="localidad">
        <TD STYLE="font-size:14pt font-family:serif">
            <xsl:value-of select="."/>
        </TD>
    </xsl:template>

    <xsl:template match="empleados">
        <TD>
            <xsl:value-of select="."/>
        </TD>
    </xsl:template>

    <xsl:template match="Modulos"></xsl:template>
</xsl:stylesheet>
