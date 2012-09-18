/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.ode.bpel.compiler.bom;

import javax.xml.namespace.QName;

/** This class maintains the mappings between namespaces and localnames used for conveying the semantic, spatial and temporal details of 
 * process activities. This class is non-extendable so as to avoid overrides on provided member and functionality in sub-classes.
 *
 * @date    17/09/2012
 * @author george athanasopoulos - s3lab
 */
public final class EnvisionExtensionQNames {
    
    public static final String ENVISION_EXTENSIONS_NAMESPACE =  "http://purl.org/nkua/s3lab/ode/1.3.5/envision";
    
    public static final QName ENVISION_OUTCOME_QUERIES_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "OutcomeQueries");
    public static final QName ENVISION_OUTCOME_QUERY_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "Query");
    public static final QName ENVISION_OUTCOME_QUERY_VARIABLE_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "variable");
    public static final QName ENVISION_OUTCOME_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "OutcomeExtensions");
    public static final QName ENVISION_TIMEINTERVAL_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "TimeInterval");
    public static final QName ENVISION_STARTTIME_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "startTime");
    public static final QName ENVISION_ENDTIME_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "endTime");
    public static final QName ENVISION_MULTIPOLY_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "MultiPolygon");
    public static final QName ENVISION_POLY_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "Polygon");
    public static final QName ENVISION_NODE_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "node");
    public static final QName ENVISION_POINT_QNAME = new QName(ENVISION_EXTENSIONS_NAMESPACE, "point");
    
    
    public static final String SAWSDL_EXTENSIONS_NAMESPACE =  "http://www.w3.org/ns/sawsdl";
    
    public static final QName SAWSDL_MODELREFERENCE_QNAME = new QName(SAWSDL_EXTENSIONS_NAMESPACE, "modelReference");
    public static final QName SAWSDL_LIFTING_QNAME = new QName(SAWSDL_EXTENSIONS_NAMESPACE, "liftingSchemaMapping");
    public static final QName SAWSDL_LOWERING_QNAME = new QName(SAWSDL_EXTENSIONS_NAMESPACE, "loweringSchemaMapping");
    
}
