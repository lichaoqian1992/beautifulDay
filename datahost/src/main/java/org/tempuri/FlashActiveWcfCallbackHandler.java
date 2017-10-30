/**
 * FlashActiveWcfCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.6  Built on : Jul 30, 2017 (09:08:31 BST)
 */
package org.tempuri;


/**
 *  FlashActiveWcfCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class FlashActiveWcfCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public FlashActiveWcfCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public FlashActiveWcfCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for addPlatformActive method
     * override this method for handling normal response from addPlatformActive operation
     */
    public void receiveResultaddPlatformActive(
        org.tempuri.FlashActiveWcfStub.AddPlatformActiveResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from addPlatformActive operation
     */
    public void receiveErroraddPlatformActive(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getActiveArticleList method
     * override this method for handling normal response from getActiveArticleList operation
     */
    public void receiveResultgetActiveArticleList(
        org.tempuri.FlashActiveWcfStub.GetActiveArticleListResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getActiveArticleList operation
     */
    public void receiveErrorgetActiveArticleList(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateFlashStatistics method
     * override this method for handling normal response from updateFlashStatistics operation
     */
    public void receiveResultupdateFlashStatistics(
        org.tempuri.FlashActiveWcfStub.UpdateFlashStatisticsResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateFlashStatistics operation
     */
    public void receiveErrorupdateFlashStatistics(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getUserShowFlashActiveInfo method
     * override this method for handling normal response from getUserShowFlashActiveInfo operation
     */
    public void receiveResultgetUserShowFlashActiveInfo(
        org.tempuri.FlashActiveWcfStub.GetUserShowFlashActiveInfoResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getUserShowFlashActiveInfo operation
     */
    public void receiveErrorgetUserShowFlashActiveInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateFlashPurchase method
     * override this method for handling normal response from updateFlashPurchase operation
     */
    public void receiveResultupdateFlashPurchase(
        org.tempuri.FlashActiveWcfStub.UpdateFlashPurchaseResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateFlashPurchase operation
     */
    public void receiveErrorupdateFlashPurchase(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getFlashPurchaseList method
     * override this method for handling normal response from getFlashPurchaseList operation
     */
    public void receiveResultgetFlashPurchaseList(
        org.tempuri.FlashActiveWcfStub.GetFlashPurchaseListResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getFlashPurchaseList operation
     */
    public void receiveErrorgetFlashPurchaseList(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getUserShowFlashActiveIng method
     * override this method for handling normal response from getUserShowFlashActiveIng operation
     */
    public void receiveResultgetUserShowFlashActiveIng(
        org.tempuri.FlashActiveWcfStub.GetUserShowFlashActiveIngResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getUserShowFlashActiveIng operation
     */
    public void receiveErrorgetUserShowFlashActiveIng(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getFlashPurchase method
     * override this method for handling normal response from getFlashPurchase operation
     */
    public void receiveResultgetFlashPurchase(
        org.tempuri.FlashActiveWcfStub.GetFlashPurchaseResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getFlashPurchase operation
     */
    public void receiveErrorgetFlashPurchase(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateFlashPurchaseState method
     * override this method for handling normal response from updateFlashPurchaseState operation
     */
    public void receiveResultupdateFlashPurchaseState(
        org.tempuri.FlashActiveWcfStub.UpdateFlashPurchaseStateResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateFlashPurchaseState operation
     */
    public void receiveErrorupdateFlashPurchaseState(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for deleteFlashPurchase method
     * override this method for handling normal response from deleteFlashPurchase operation
     */
    public void receiveResultdeleteFlashPurchase(
        org.tempuri.FlashActiveWcfStub.DeleteFlashPurchaseResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from deleteFlashPurchase operation
     */
    public void receiveErrordeleteFlashPurchase(java.lang.Exception e) {
    }
}
