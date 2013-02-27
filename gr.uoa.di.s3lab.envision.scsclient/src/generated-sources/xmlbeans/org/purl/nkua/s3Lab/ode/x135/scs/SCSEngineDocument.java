/*
 * An XML document type.
 * Localname: SCSEngine
 * Namespace: http://purl.org/nkua/s3lab/ode/1.3.5/scs
 * Java type: org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument
 *
 * Automatically generated - do not modify.
 */
package org.purl.nkua.s3Lab.ode.x135.scs;


/**
 * A document containing one SCSEngine(@http://purl.org/nkua/s3lab/ode/1.3.5/scs) element.
 *
 * This is a complex type.
 */
public interface SCSEngineDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SCSEngineDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s291F045103348EDCD28E26425FC77B2E").resolveHandle("scsengine9546doctype");
    
    /**
     * Gets the "SCSEngine" element
     */
    org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine getSCSEngine();
    
    /**
     * Sets the "SCSEngine" element
     */
    void setSCSEngine(org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine scsEngine);
    
    /**
     * Appends and returns a new empty "SCSEngine" element
     */
    org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine addNewSCSEngine();
    
    /**
     * An XML SCSEngine(@http://purl.org/nkua/s3lab/ode/1.3.5/scs).
     *
     * This is a complex type.
     */
    public interface SCSEngine extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SCSEngine.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s291F045103348EDCD28E26425FC77B2E").resolveHandle("scsengine9355elemtype");
        
        /**
         * Gets the "scope" element
         */
        org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope getScope();
        
        /**
         * Sets the "scope" element
         */
        void setScope(org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope scope);
        
        /**
         * Appends and returns a new empty "scope" element
         */
        org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope addNewScope();
        
        /**
         * An XML scope(@).
         *
         * This is an atomic type that is a restriction of org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument$SCSEngine$Scope.
         */
        public interface Scope extends org.apache.xmlbeans.XmlAnyURI
        {
            public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
                org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Scope.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s291F045103348EDCD28E26425FC77B2E").resolveHandle("scopea485elemtype");
            
            /**
             * Gets the "conType" attribute
             */
            org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass.Enum getConType();
            
            /**
             * Gets (as xml) the "conType" attribute
             */
            org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass xgetConType();
            
            /**
             * True if has "conType" attribute
             */
            boolean isSetConType();
            
            /**
             * Sets the "conType" attribute
             */
            void setConType(org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass.Enum conType);
            
            /**
             * Sets (as xml) the "conType" attribute
             */
            void xsetConType(org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass conType);
            
            /**
             * Unsets the "conType" attribute
             */
            void unsetConType();
            
            /**
             * A factory class with static methods for creating instances
             * of this type.
             */
            
            public static final class Factory
            {
                public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope newInstance() {
                  return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
                
                public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope newInstance(org.apache.xmlbeans.XmlOptions options) {
                  return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
                
                private Factory() { } // No instance of this class allowed
            }
        }
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine newInstance() {
              return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument newInstance() {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
