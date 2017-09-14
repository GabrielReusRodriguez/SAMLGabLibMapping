<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:n0="http://les_catsalut.csi"
	xmlns:prx="urn:sap.com:proxy:CSD:/1SAI/TAS595C462332CB76DB8C39:740"
	exclude-result-prefixes="prx"
	xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">

	<!-- extension-element-prefixes -->

	<xsl:template match="//TiquetSAML" />
	
	<xsl:template match="wsse:Security">
		<xsl:copy select=".">
			<xsl:copy-of select="//TiquetSAML/*" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="@* | node()">
		<xsl:copy>
			<xsl:apply-templates select="@* | node()" />
		</xsl:copy>
	</xsl:template>

</xsl:stylesheet> 