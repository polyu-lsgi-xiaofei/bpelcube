/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ode.bpel.runtime;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.compiler.bom.Process.Version;
import org.apache.ode.bpel.elang.xpath10.o.OXPath10Expression;
import org.apache.ode.bpel.elang.xpath10.o.OXPath10ExpressionBPEL20;
import org.apache.ode.bpel.elang.xpath20.o.OXPath20ExpressionBPEL20;
import org.apache.ode.bpel.explang.EvaluationContext;
import org.apache.ode.bpel.explang.EvaluationException;
import org.apache.ode.bpel.o.OConstantVarType;
import org.apache.ode.bpel.o.OElementVarType;
import org.apache.ode.bpel.o.OExpression;
import org.apache.ode.bpel.o.OExpressionLanguage;
import org.apache.ode.bpel.o.OMessageVarType;
import org.apache.ode.bpel.o.OScope.Variable;
import org.apache.ode.bpel.o.OSwitch;
import org.apache.ode.bpel.o.OXsdTypeVarType;
import org.apache.ode.bpel.runtime.channels.FaultData;
import org.apache.ode.utils.DOMUtils;

import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import gr.uoa.di.s3lab.envision.scsclient.SCSClient;

import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.s3lab.space.common.ResultEntry;
import com.s3lab.space.common.ResultsList;
import com.s3lab.space.common.XMLEntryNode;


/**
 * Runtime implementation of the <code>&lt;switch&gt;</code> activity.
 */
class SWITCH extends ACTIVITY {
	private static final long serialVersionUID = 1L;
	private static final Log __log = LogFactory.getLog(SWITCH.class);
	//the following field is for the expression
	public static final URI[] EXPRESSION_LANGS = new URI[] {URI.create("urn:oasis:names:tc:wsbpel:2.0:sublang:xpath1.0"),URI.create("urn:oasis:names:tc:wsbpel:2.0:sublang:xpath2.0")};

  public SWITCH(ActivityInfo self, ScopeFrame scopeFrame, LinkFrame linkFrame) {
    super(self, scopeFrame, linkFrame);
  }

  public final void run() {
    OSwitch oswitch = (OSwitch)_self.o;
    OSwitch.OCase matchedOCase = null;
    FaultData faultData = null;
    
    /********************************************************************/
    //Pigi Kouki: Somewhere in the above code we will put the read operation to 
    //the SCS Engine - not exactly down here but where George indicates!
    
    //set the boolean variable to true
    String trueVarContent = "<temporary-simple-type-wrapper>true</temporary-simple-type-wrapper>";
    Node trueVarElement = null;
	try {
		trueVarElement = DOMUtils.stringToDOM(trueVarContent);
	} catch (SAXException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    SCSClient client = SCSClient.AccessSCSClient();
    if(client.spaceHasBeenInitialized()){
        //attributes metaInformation and multipolygon will be given from George
        
    	//for the syntacticType we should check the type of the oscope varibale
    	
    	//wait for George to give me a variablesMap
    	QName syntType = null;
    	/*for(OSwitch.OCase _case: oswitch.getCases())
    		_case.expression;*/
       
       //pigi: i need the matchedOCase variable, right??
       //so, where exactly to put the above code?? after the matchedOCase = ocase; ??
       //HashMap<String,Variable> variablesMap = _scopeFrame.oscope.variables;	//this variablesMap is temporary!i'll put what george will send me
    	
    	/*HashMap<String,Variable> variablesMap = new HashMap<String,Variable>();
        for(OSwitch.OCase oCase : oswitch.getCases()){
    	   OExpression expression = oCase.expression;
    	   OExpressionLanguage expLanguage = oCase.expression.expressionLanguage;
           if(expLanguage.expressionLanguageUri.equals(EXPRESSION_LANGS[0])){
               if(oCase.getOwner().version.equals(Version.BPEL11.name())){
                   OXPath10Expression xpath10Exp = (OXPath10Expression) oCase.expression;
                   variablesMap.putAll(xpath10Exp.vars);
               }
               if(oCase.getOwner().version.equals(Version.BPEL20.name())){
                   OXPath10ExpressionBPEL20 xpath10Exp = (OXPath10ExpressionBPEL20) oCase.expression;
                   variablesMap.putAll(xpath10Exp.vars);
               }
           }   
           else if(expLanguage.expressionLanguageUri.equals(EXPRESSION_LANGS[1])){
               OXPath20ExpressionBPEL20 xpath20Exp = (OXPath20ExpressionBPEL20) oCase.expression;
               variablesMap.putAll(xpath20Exp.vars);
           }
       }*/
    	Map<String,Variable> variablesMap = oswitch.getVarMaps();
    	System.out.println("*** Switch vars map contains :"+variablesMap.size());
	       for(Variable var : variablesMap.values()){
	    	   System.out.println("varname = " + var.name);
	    	   if(var.type instanceof OConstantVarType){
	    			OConstantVarType oConstant = (OConstantVarType) var.type;
	    			syntType = new QName(oConstant.getValue().getNamespaceURI(), oConstant.getValue().getLocalName());
	    		}
	    		else if(var.type instanceof OElementVarType){
	    			OElementVarType oElement = (OElementVarType) var.type;
	    			syntType = oElement.elementType;
	    		}
	    		else if(var.type instanceof OMessageVarType){
	    			OMessageVarType oMessage = (OMessageVarType) var.type;
	    			syntType = oMessage.messageType;
	    		}
	    		else if(var.type instanceof OXsdTypeVarType){
	    			OXsdTypeVarType oXsd = (OXsdTypeVarType) var.type;
	    			syntType = oXsd.xsdType;
	    		}
	    		else {
	    			System.out.println("[SWITCH] Error: var.type is not instance of any available type.");
	    		}
	    	    System.out.println("--> read information with syntType.toString()=" + syntType.toString());
	    		for(URI metaInformation : oswitch._varMetamodelReferences.get(var.name)){
	    			System.out.println("metaInformation = " + metaInformation.toString());
	    			for(String multiPolygon : oswitch._varMPolygons.get(var.name)){
	    				System.out.println("multiPolygon = " + multiPolygon.toString());
	    				for(String time_Intervals : oswitch._varTimeIntervals.get(var.name)){
	    					System.out.println("case where multipolygon!=null && temporalFeatures!=null");
	    					String[] times = time_Intervals.split(" ");
	        				SimpleDateFormat simpleDataFormat1 = new SimpleDateFormat(times[0]);
	        				Timestamp timestampStart = null;
							try {
								timestampStart = new Timestamp(simpleDataFormat1.parse(times[0]).getTime());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        				SimpleDateFormat simpleDataFormat2 = new SimpleDateFormat(times[0]);
	        				Timestamp timestampEnd = null;
							try {
								timestampEnd = new Timestamp(simpleDataFormat2.parse(times[1]).getTime());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        				TimeZone tz = simpleDataFormat1.getTimeZone();
	        				
	        				ResultsList results = client.read(metaInformation, syntType.toString(), getBpelProcess().getPID().getLocalPart(), getP2PSessionId(), multiPolygon, timestampStart, timestampEnd, tz);
				    		
				    		if(!results.getCollection().isEmpty())
				    		for(ResultEntry currentResEntry : results.getCollection()){
				    		     Node resultNode = (Node) currentResEntry.getEntry();
				    		     VariableInstance varInstance = _scopeFrame.resolve(var);
				    		     this.getBpelRuntimeContext().writeVariable(varInstance, resultNode);
				    		     
				    		     //make true the variable
				    		     Variable trueVar = oswitch.getColateralVar(var.name);
				    		     VariableInstance trueVarInstance = _scopeFrame.resolve(trueVar);
				    		     
				    		     //Document doc = DOMUtils.newDocument();
				    		     //System.out.println("About to set value to "+trueVarInstance.declaration.name);
				    		     //solution1
				    		     //CDATASection cdataNode = doc.createCDATASection("");
				    		     //CharacterData cdata = cdataNode;
				    		     //cdata.setData("true");
				    		     
				    		     //solution2
				    		     //Element root = doc.createElement("Item");
				    		     //root.setAttribute("VariableIsTrue", "true");
				    		     
				    		     System.out.println("About to write true variable");
				    		     if(trueVarElement!=null)
				    		    	 this.getBpelRuntimeContext().writeVariable(trueVarInstance, trueVarElement);
				    		     //this.getBpelRuntimeContext().writeVariable(trueVarInstance, doc.getFirstChild());
				    		     
					    		/*if(there are results){
					    	       final VariableInstance varInstance = _scopeFrame.resolve(_oinvoke.outputVar);
					    		   //changes is the entryType returned from the read operation
					    	       this.getBpelRuntimeContext().writeVariable(varInstance, changes);
					    		}*/
				    		     break; //we need to store only the first returned result
				    		}
	    				}
	    			}
	    			//case where multipolygon==null && temporalFeatures==null
	    			if(oswitch._varMPolygons.get(var.name).isEmpty() && oswitch._varTimeIntervals.get(var.name).isEmpty()){
	    				System.out.println("case where multipolygon==null && temporalFeatures==null");
	    				System.out.println("About to read in the SCS with metainfo = " + metaInformation.toString() + " and syntType=" + syntType.toString());
	    				ResultsList results = client.read(metaInformation, syntType.toString(), getBpelProcess().getPID().getLocalPart(), getP2PSessionId(), null, null, null, null);
	    				if(results==null)
	    					System.out.println("0 results returned");
	    				else
	    					System.out.println(results.getCollection().size() + " results returned");
			    		if(!results.getCollection().isEmpty()){
				    		for(ResultEntry currentResEntry : results.getCollection()){
				    		     //Node resultNode = (Node) currentResEntry.getEntry();
				    		     XMLEntryNode xmlNode = (XMLEntryNode) currentResEntry.getEntry();
				    		     Node resultNode = xmlNode.getNode();
				    		     if(xmlNode.getNode()==null) System.out.println("Null xml entry Node!!");
				    		     System.out.println("Retrieved element value is :\n"+DOMUtils.domToString(resultNode)+"\n");
				    		     VariableInstance varInstance = _scopeFrame.resolve(var);
				    		     this.getBpelRuntimeContext().writeVariable(varInstance, resultNode);
				    		     
				    		     //make true the variable
				    		     Variable trueVar = oswitch.getColateralVar(var.name);
				    		     VariableInstance trueVarInstance = _scopeFrame.resolve(trueVar);
				    		     
				    		     //Document doc = DOMUtils.newDocument();
				    		     //System.out.println("About to set value to "+trueVarInstance.declaration.name);
				    		     //solution1
				    		     //CDATASection cdataNode = doc.createCDATASection("");
				    		     //CharacterData cdata = cdataNode;
				    		     //cdata.setData("true");
				    		     
				    		     //solution2
				    		     //Element root = doc.createElement("Item");
				    		     //root.setAttribute("VariableIsTrue", "true");
				    		     
				    		     //this.getBpelRuntimeContext().writeVariable(trueVarInstance, doc.getFirstChild());
				    		     
				    		     if(trueVarElement!=null)
				    		    	 this.getBpelRuntimeContext().writeVariable(trueVarInstance, trueVarElement);
				    		     
					    		/*if(there are results){
					    	       final VariableInstance varInstance = _scopeFrame.resolve(_oinvoke.outputVar);
					    		   //changes is the entryType returned from the read operation
					    	       this.getBpelRuntimeContext().writeVariable(varInstance, changes);
					    		}*/
				    		     break; //we need to store only the first returned result
				    		}
			    		}
	    			}
	    			//case where multipolygon!=null && temporalFeatures==null
	    			if(!oswitch._varMPolygons.get(var.name).isEmpty() && oswitch._varTimeIntervals.get(var.name).isEmpty()){
	    				for(String multiPolygon : oswitch._varMPolygons.get(var.name)){
	    					System.out.println("case where multipolygon!=null && temporalFeatures==null");
	    					ResultsList results = client.read(metaInformation, syntType.toString(), getBpelProcess().getPID().getLocalPart(), getP2PSessionId(), multiPolygon, null, null, null);
				    		
				    		if(!results.getCollection().isEmpty())
				    		for(ResultEntry currentResEntry : results.getCollection()){
				    		     Node resultNode = (Node) currentResEntry.getEntry();
				    		     VariableInstance varInstance = _scopeFrame.resolve(var);
				    		     this.getBpelRuntimeContext().writeVariable(varInstance, resultNode);
				    		     
				    		     //make true the variable
				    		     Variable trueVar = oswitch.getColateralVar(var.name);
				    		     VariableInstance trueVarInstance = _scopeFrame.resolve(trueVar);
				    		     
				    		     //Document doc = DOMUtils.newDocument();
				    		     //System.out.println("About to set value to "+trueVarInstance.declaration.name);
				    		     //solution1
				    		     //CDATASection cdataNode = doc.createCDATASection("");
				    		     //CharacterData cdata = cdataNode;
				    		     //cdata.setData("true");
				    		     
				    		     //solution2
				    		     //Element root = doc.createElement("Item");
				    		     //root.setAttribute("VariableIsTrue", "true");
				    		     
				    		     if(trueVarElement!=null)
				    		    	 this.getBpelRuntimeContext().writeVariable(trueVarInstance, trueVarElement);
				    		     
					    		/*if(there are results){
					    	       final VariableInstance varInstance = _scopeFrame.resolve(_oinvoke.outputVar);
					    		   //changes is the entryType returned from the read operation
					    	       this.getBpelRuntimeContext().writeVariable(varInstance, changes);
					    		}*/
				    		     break; //we need to store only the first returned result
				    		}
	    				}
	    			}
	    			//case where multipolygon==null && temporalFeatures!=null
	    			if(oswitch._varMPolygons.get(var.name).isEmpty() && !oswitch._varTimeIntervals.get(var.name).isEmpty()){
	    				System.out.println("case where multipolygon==null && temporalFeatures!=null");
	    				for(String time_Intervals : oswitch._varTimeIntervals.get(var.name)){
	    					String[] times = time_Intervals.split(" ");
	        				SimpleDateFormat simpleDataFormat1 = new SimpleDateFormat(times[0]);
	        				Timestamp timestampStart = null;
							try {
								timestampStart = new Timestamp(simpleDataFormat1.parse(times[0]).getTime());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        				SimpleDateFormat simpleDataFormat2 = new SimpleDateFormat(times[0]);
	        				Timestamp timestampEnd = null;
							try {
								timestampEnd = new Timestamp(simpleDataFormat2.parse(times[1]).getTime());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        				TimeZone tz = simpleDataFormat1.getTimeZone();
	        				
				    		ResultsList results = client.read(metaInformation, syntType.toString(), getBpelProcess().getPID().getLocalPart(), getP2PSessionId(), null, timestampStart, timestampEnd, tz);
				    		
				    		if(!results.getCollection().isEmpty())
				    		for(ResultEntry currentResEntry : results.getCollection()){
				    		     Node resultNode = (Node) currentResEntry.getEntry();
				    		     VariableInstance varInstance = _scopeFrame.resolve(var);
				    		     this.getBpelRuntimeContext().writeVariable(varInstance, resultNode);
				    		     
				    		     //make true the variable
				    		     Variable trueVar = oswitch.getColateralVar(var.name);
				    		     VariableInstance trueVarInstance = _scopeFrame.resolve(trueVar);
				    		     
				    		     
				    		     //System.out.println("About to set value to "+trueVarInstance.declaration.name);
				    		     //solution1
				    		     //CDATASection cdataNode = doc.createCDATASection("");
				    		     //CharacterData cdata = cdataNode;
				    		     //cdata.setData("true");
				    		     
				    		     //solution2
				    		     //Element root = doc.createElement("Item");
				    		     //root.setAttribute("VariableIsTrue", "true");
				    		     System.out.println("About to write true variable");
				    		     if(trueVarElement!=null)
				    		    	 this.getBpelRuntimeContext().writeVariable(trueVarInstance, trueVarElement);
				    		     
					    		/*if(there are results){
					    	       final VariableInstance varInstance = _scopeFrame.resolve(_oinvoke.outputVar);
					    		   //changes is the entryType returned from the read operation
					    	       this.getBpelRuntimeContext().writeVariable(varInstance, changes);
					    		}*/
				    		     break; //we need to store only the first returned result
				    		}
	    				}
	    			}
	    		}
	    	}
	    }
    
    
    EvaluationContext evalCtx = getEvaluationContext();
    for (Iterator i = oswitch.getCases().iterator(); i.hasNext();) {
      OSwitch.OCase ocase = (OSwitch.OCase) i.next();
      try{
    	  try {
	      	if(getBpelRuntimeContext().getExpLangRuntime().evaluateAsBoolean(ocase.expression, evalCtx)){
	          matchedOCase = ocase;
	          break;
	        }
	      } catch (EvaluationException e) {
	    	  __log.error("Sub-Language execution failure evaluating " + ocase.expression, e);
	        throw new FaultException(oswitch.getOwner().constants.qnSubLanguageExecutionFault, e.getMessage());
	      }
      }catch(FaultException e){
      	__log.error(e.getMessage(),e);
        faultData = createFault(e.getQName(), ocase);
        _self.parent.completed(faultData, CompensationHandler.emptySet());

        // Dead path all the child activiites:
        for (Iterator<OSwitch.OCase> j = oswitch.getCases().iterator(); j.hasNext(); )
          dpe(j.next().activity);
        return;
      }
    }

    // Dead path cases not chosen
    for (Iterator<OSwitch.OCase> i = oswitch.getCases().iterator(); i.hasNext(); ) {
      OSwitch.OCase cs = i.next();
      if (cs != matchedOCase)
        dpe(cs.activity);
    }

    // no conditions satisfied, we're done.
    if (matchedOCase == null) {
      _self.parent.completed(null, CompensationHandler.emptySet());
    } else /* matched case */ {
      // Re-use our current channels.
      ActivityInfo child = new ActivityInfo(genMonotonic(),matchedOCase.activity, _self.self, _self.parent);
      instance(createChild(child,_scopeFrame,_linkFrame));
    }
  }
}
