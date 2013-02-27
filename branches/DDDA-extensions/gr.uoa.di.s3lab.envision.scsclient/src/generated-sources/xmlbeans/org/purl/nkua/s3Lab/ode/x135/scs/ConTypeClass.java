/*
 * XML Type:  conTypeClass
 * Namespace: http://purl.org/nkua/s3lab/ode/1.3.5/scs
 * Java type: org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass
 *
 * Automatically generated - do not modify.
 */
package org.purl.nkua.s3Lab.ode.x135.scs;


/**
 * An XML conTypeClass(@http://purl.org/nkua/s3lab/ode/1.3.5/scs).
 *
 * This is an atomic type that is a restriction of org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass.
 */
public interface ConTypeClass extends org.apache.xmlbeans.XmlString
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConTypeClass.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s291F045103348EDCD28E26425FC77B2E").resolveHandle("contypeclass094dtype");
    
    org.apache.xmlbeans.StringEnumAbstractBase enumValue();
    void set(org.apache.xmlbeans.StringEnumAbstractBase e);
    
    static final Enum ASYNCHRONOUS = Enum.forString("Asynchronous");
    static final Enum SYNCHRONOUS = Enum.forString("Synchronous");
    
    static final int INT_ASYNCHRONOUS = Enum.INT_ASYNCHRONOUS;
    static final int INT_SYNCHRONOUS = Enum.INT_SYNCHRONOUS;
    
    /**
     * Enumeration value class for org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass.
     * These enum values can be used as follows:
     * <pre>
     * enum.toString(); // returns the string value of the enum
     * enum.intValue(); // returns an int value, useful for switches
     * // e.g., case Enum.INT_ASYNCHRONOUS
     * Enum.forString(s); // returns the enum value for a string
     * Enum.forInt(i); // returns the enum value for an int
     * </pre>
     * Enumeration objects are immutable singleton objects that
     * can be compared using == object equality. They have no
     * public constructor. See the constants defined within this
     * class for all the valid values.
     */
    static final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase
    {
        /**
         * Returns the enum value for a string, or null if none.
         */
        public static Enum forString(java.lang.String s)
            { return (Enum)table.forString(s); }
        /**
         * Returns the enum value corresponding to an int, or null if none.
         */
        public static Enum forInt(int i)
            { return (Enum)table.forInt(i); }
        
        private Enum(java.lang.String s, int i)
            { super(s, i); }
        
        static final int INT_ASYNCHRONOUS = 1;
        static final int INT_SYNCHRONOUS = 2;
        
        public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
            new org.apache.xmlbeans.StringEnumAbstractBase.Table
        (
            new Enum[]
            {
                new Enum("Asynchronous", INT_ASYNCHRONOUS),
                new Enum("Synchronous", INT_SYNCHRONOUS),
            }
        );
        private static final long serialVersionUID = 1L;
        private java.lang.Object readResolve() { return forInt(intValue()); } 
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass newValue(java.lang.Object obj) {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) type.newValue( obj ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass newInstance() {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
