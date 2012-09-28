<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<wps:Execute xmlns:wps="http://www.opengis.net/wps/1.0.0" xmlns:ows="http://www.opengis.net/ows/1.1" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" service="WPS" version="1.0.0" xsi:schemaLocation="http://www.opengis.net/wps/1.0.0 ../wpsExecute_request.xsd">
			<ows:Identifier>ProbaLSComputationSync</ows:Identifier>
			<wps:DataInputs>
				<wps:Input>
					<ows:Identifier>iterations</ows:Identifier>
					<ows:Title>Number of iterations</ows:Title>
					<wps:Data>
						<wps:LiteralData>
							<xsl:value-of select="//ows:Identifier[.='iterations']/..//wps:LiteralData/."/>
						</wps:LiteralData>
					</wps:Data>
				</wps:Input>
				<wps:Input>
					<ows:Identifier>format</ows:Identifier>
					<ows:Title>Format of the returned grid</ows:Title>
					<wps:Data>
						<wps:LiteralData>
							<xsl:value-of select="//ows:Identifier[.='format']/..//wps:LiteralData/."/>
						</wps:LiteralData>
					</wps:Data>
				</wps:Input>
				<wps:Input>
					<ows:Identifier>minDepth</ows:Identifier>
					<ows:Title>Minimum depth of the water layer</ows:Title>
					<wps:Data>
						<wps:LiteralData>
							<xsl:value-of select="//ows:Identifier[.='minDepth']/..//wps:LiteralData/."/>						
						</wps:LiteralData>
					</wps:Data>
				</wps:Input>
				<wps:Input>
					<ows:Identifier>maxDepth</ows:Identifier>
					<ows:Title>Maximum depth of the water layer</ows:Title>
					<wps:Data>
						<wps:LiteralData>
							<xsl:value-of select="//ows:Identifier[.='maxDepth']/..//wps:LiteralData/."/>						
						</wps:LiteralData>
					</wps:Data>
				</wps:Input>
				<wps:Input>
					<ows:Identifier>waterSaturationRatio</ows:Identifier>
					<ows:Title>Saturation ratio of the water layer</ows:Title>
					<wps:Data>
						<wps:LiteralData>
							<xsl:value-of select="//ows:Identifier[.='waterSaturationRatio']/..//wps:LiteralData/."/>						
						</wps:LiteralData>
					</wps:Data>
				</wps:Input>
				<wps:Input>
					<ows:Identifier>zone</ows:Identifier>
					<ows:Title>Name of the zone</ows:Title>
					<wps:Data>
						<wps:LiteralData>
							<xsl:value-of select="//ows:Identifier[.='zone']/..//wps:LiteralData/."/>						
						</wps:LiteralData>
					</wps:Data>
				</wps:Input>
			</wps:DataInputs>
			<wps:ProcessOutputs>
				<wps:Output>
					<wps:LiteralOutput>
						<ows:Identifier>EnvisionResult</ows:Identifier>
					</wps:LiteralOutput>
				</wps:Output>
			</wps:ProcessOutputs>
		</wps:Execute>
	</xsl:template>
</xsl:stylesheet>
