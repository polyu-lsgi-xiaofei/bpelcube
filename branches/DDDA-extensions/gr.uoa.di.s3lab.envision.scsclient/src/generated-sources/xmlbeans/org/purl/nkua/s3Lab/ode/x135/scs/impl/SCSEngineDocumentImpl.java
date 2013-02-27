/*
 * An XML document type.
 * Localname: SCSEngine
 * Namespace: http://purl.org/nkua/s3lab/ode/1.3.5/scs
 * Java type: org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument
 *
 * Automatically generated - do not modify.
 */
package org.purl.nkua.s3Lab.ode.x135.scs.impl;
/**
 * A document containing one SCSEngine(@http://purl.org/nkua/s3lab/ode/1.3.5/scs) element.
 *
 * This is a complex type.
 */
public class SCSEngineDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument
{
    private static final long serialVersionUID = 1L;
    
    public SCSEngineDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCSENGINE$0 = 
        new javax.xml.namespace.QName("http://purl.org/nkua/s3lab/ode/1.3.5/scs", "SCSEngine");
    
    
    /**
     * Gets the "SCSEngine" element
     */
    public org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine getSCSEngine()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine target = null;
            target = (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine)get_store().find_element_user(SCSENGINE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SCSEngine" element
     */
    public void setSCSEngine(org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine scsEngine)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine target = null;
            target = (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine)get_store().find_element_user(SCSENGINE$0, 0);
            if (target == null)
            {
                target = (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine)get_store().add_element_user(SCSENGINE$0);
            }
            target.set(scsEngine);
        }
    }
    
    /**
     * Appends and returns a new empty "SCSEngine" element
     */
    public org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine addNewSCSEngine()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine target = null;
            target = (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine)get_store().add_element_user(SCSENGINE$0);
            return target;
        }
    }
    /**
     * An XML SCSEngine(@http://purl.org/nkua/s3lab/ode/1.3.5/scs).
     *
     * This is a complex type.
     */
    public static class SCSEngineImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine
    {
        private static final long serialVersionUID = 1L;
        
        public SCSEngineImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName SCOPE$0 = 
            new javax.xml.namespace.QName("", "scope");
        
        
        /**
         * Gets the "scope" element
         */
        public org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope getScope()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope target = null;
                target = (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope)get_store().find_element_user(SCOPE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "scope" element
         */
        public void setScope(org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope scope)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope target = null;
                target = (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope)get_store().find_element_user(SCOPE$0, 0);
                if (target == null)
                {
                    target = (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope)get_store().add_element_user(SCOPE$0);
                }
                target.set(scope);
            }
        }
        
        /**
         * Appends and returns a new empty "scope" element
         */
        public org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope addNewScope()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope target = null;
                target = (org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope)get_store().add_element_user(SCOPE$0);
                return target;
            }
        }
        /**
         * An XML scope(@).
         *
         * This is an atomic type that is a restriction of org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument$SCSEngine$Scope.
         */
        public static class ScopeImpl extends org.apache.xmlbeans.impl.values.JavaUriHolderEx implements org.purl.nkua.s3Lab.ode.x135.scs.SCSEngineDocument.SCSEngine.Scope
        {
            private static final long serialVersionUID = 1L;
            
            public ScopeImpl(org.apache.xmlbeans.SchemaType sType)
            {
                super(sType, true);
            }
            
            protected ScopeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
            {
                super(sType, b);
            }
            
            private static final javax.xml.namespace.QName CONTYPE$0 = 
                new javax.xml.namespace.QName("", "conType");
            
            
            /**
             * Gets the "conType" attribute
             */
            public org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass.Enum getConType()
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    org.apache.xmlbeans.SimpleValue target = null;
                    target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CONTYPE$0);
                    if (target == null)
                    {
                      target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(CONTYPE$0);
                    }
                    if (target == null)
                    {
                      return null;
                    }
                    return (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass.Enum)target.getEnumValue();
                }
            }
            
            /**
             * Gets (as xml) the "conType" attribute
             */
            public org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass xgetConType()
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass target = null;
                    target = (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass)get_store().find_attribute_user(CONTYPE$0);
                    if (target == null)
                    {
                      target = (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass)get_default_attribute_value(CONTYPE$0);
                    }
                    return target;
                }
            }
            
            /**
             * True if has "conType" attribute
             */
            public boolean isSetConType()
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    return get_store().find_attribute_user(CONTYPE$0) != null;
                }
            }
            
            /**
             * Sets the "conType" attribute
             */
            public void setConType(org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass.Enum conType)
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    org.apache.xmlbeans.SimpleValue target = null;
                    target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CONTYPE$0);
                    if (target == null)
                    {
                      target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(CONTYPE$0);
                    }
                    target.setEnumValue(conType);
                }
            }
            
            /**
             * Sets (as xml) the "conType" attribute
             */
            public void xsetConType(org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass conType)
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass target = null;
                    target = (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass)get_store().find_attribute_user(CONTYPE$0);
                    if (target == null)
                    {
                      target = (org.purl.nkua.s3Lab.ode.x135.scs.ConTypeClass)get_store().add_attribute_user(CONTYPE$0);
                    }
                    target.set(conType);
                }
            }
            
            /**
             * Unsets the "conType" attribute
             */
            public void unsetConType()
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    get_store().remove_attribute(CONTYPE$0);
                }
            }
        }
    }
}
