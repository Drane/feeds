package com.clarity;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
 
@Named
@SessionScoped
      
public class User implements Serializable {	  
  private final String VALID_NAME     = "Gaspe";
  private final String VALID_PASSWORD = "jsf";
  
  private String name;
  private String password, nameError;
 
  public String getName() { return name; }
  public void setName(String newValue) { name = newValue; }
  
  public void setNameError(String error) {nameError = error;}
  public String getNameError() {return nameError;}
  
  public String getPassword() { return password; }
  public void setPassword(String newValue) { password = newValue; }  
  
  public void validateName(FacesContext fc, UIComponent c, Object v) {
    String name = (String)v;
    
    if (name.contains("_")) {
    	throw new ValidatorException(
    			new FacesMessage("Name cannot contain underscores"));
    }
  } 
   
  public void validate(ComponentSystemEvent e) {
    UIForm form = (UIForm)e.getComponent(); 
    UIInput nameInput = (UIInput)form.findComponent("name");
    UIInput pwdInput = (UIInput)form.findComponent("password");
    
    if ( ! (nameInput.getValue().equals(VALID_NAME) &&
    		pwdInput.getValue().equals(VALID_PASSWORD))) {
      
      FacesContext fc = FacesContext.getCurrentInstance();
      fc.addMessage(form.getClientId(), 
        new FacesMessage("Name and password are invalid. Please try again."));
      fc.renderResponse();
    }
  }
  
  public String login() {
    return "/views/feeds?faces-redirect=true";
  }

  public String logout() {
	name = password = nameError = null;
	return "/views/login?faces-redirect=true";
  }
  
  public String drop() {
	return null;
  }

}