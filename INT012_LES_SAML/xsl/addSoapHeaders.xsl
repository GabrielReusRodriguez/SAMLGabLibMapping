<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:n0="http://les_catsalut.csi"
xmlns:prx="urn:sap.com:proxy:CSD:/1SAI/TAS595C462332CB76DB8C39:740"
exclude-result-prefixes="prx"
>

<!-- extension-element-prefixes  -->

<xsl:template match="/">
	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<soap:Header>
		<wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" soap:mustUnderstand="1">
		</wsse:Security>
	</soap:Header>
	<soap:Body>
		<xsl:copy-of select="./*"/>
	</soap:Body>
	</soap:Envelope>
</xsl:template>

</xsl:stylesheet> 