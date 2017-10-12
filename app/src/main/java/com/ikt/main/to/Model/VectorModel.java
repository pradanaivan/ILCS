package com.ikt.main.to.model;

import com.ikt.main.to.object.EditTicketObject;
import com.ikt.main.to.object.IncomingObject;
import com.ikt.main.to.object.ObjectNewSlot;
import com.ikt.main.to.object.ScanVINObject;
import com.ikt.main.to.object.SimpleObject;
import com.ikt.main.to.object.TicketObject;
import com.ikt.main.to.object.TicketObjectDetail;
import com.ikt.main.to.object.TimeSlotObject;
import com.ikt.main.to.object.TripObject;
import com.ikt.main.to.object.TruckActivitiesDetailObject;
import com.ikt.main.to.object.TruckActivitiesObject;

import java.util.ArrayList;

/**
 * Created by Arifin on 3/20/16.
 */
public class VectorModel {

    public static VectorModel instance;
    public ArrayList<TimeSlotObject> timeSlotObjectVector;
    public ArrayList<IncomingObject> incomingObjects;
    public ArrayList<TicketObjectModel> assignTicketObjects;
    public ArrayList<TicketObjectModel> unassignTicketObjects;
    public ArrayList<TruckActivitiesObject> truckActivitiesObjects;
    public ArrayList<TruckActivitiesDetailObject> truckActivitiesDetailObjects;
    public ArrayList<TicketObject> listAssignTicketObjects;
    public ArrayList<TicketObject> listUnassignTicketObjects;
    public ArrayList<TicketObjectDetail> ticketObjectDetails;
    public ArrayList<EditTicketObject> editTicketObjects;
    public ArrayList<String> arrVin;
    public ArrayList<TripObject> tripObjects;
    public ArrayList<EditTicketObject> saveTicketObjects;
    public ArrayList<String> arrTrip;
    public ArrayList<SimpleObject> checkUpdateObject;
    public ArrayList<ScanVINObject> scanVINObjects;
    public ArrayList<String> objectNewSlotsDateIn;
    public ArrayList<String> objectNewSlotsSlotIn;
    public ArrayList<String> objectNewSlotsAreaIn;

    public ArrayList<String> objectNewSlotsDateOut;
    public ArrayList<String> objectNewSlotsSlotOut;
    public ArrayList<String> objectNewSlotsAreaOut;

    public ArrayList<ObjectNewSlot> objectNewSlotsIn;
    public ArrayList<ObjectNewSlot> objectNewSlotsOut;


    public VectorModel(){
        timeSlotObjectVector = new ArrayList<TimeSlotObject>();
        incomingObjects = new ArrayList<IncomingObject>();
        assignTicketObjects = new ArrayList<TicketObjectModel>();
        unassignTicketObjects = new ArrayList<TicketObjectModel>();
        truckActivitiesObjects = new ArrayList<TruckActivitiesObject>();
        truckActivitiesDetailObjects = new ArrayList<TruckActivitiesDetailObject>();
        listAssignTicketObjects = new ArrayList<TicketObject>();
        listUnassignTicketObjects = new ArrayList<TicketObject>();
        ticketObjectDetails = new ArrayList<TicketObjectDetail>();
        editTicketObjects = new ArrayList<EditTicketObject>();
        arrVin = new ArrayList<String>();
        tripObjects = new ArrayList<TripObject>();
        saveTicketObjects = new ArrayList<EditTicketObject>();
        arrTrip = new ArrayList<String>();
        checkUpdateObject = new ArrayList<SimpleObject>();
        scanVINObjects = new ArrayList<ScanVINObject>();
        objectNewSlotsDateIn = new ArrayList<String>();
        objectNewSlotsSlotIn = new ArrayList<String>();
        objectNewSlotsAreaIn = new ArrayList<String>();

        objectNewSlotsDateOut = new ArrayList<String>();
        objectNewSlotsSlotOut = new ArrayList<String>();
        objectNewSlotsAreaOut = new ArrayList<String>();

        objectNewSlotsIn = new ArrayList<ObjectNewSlot>();
        objectNewSlotsOut = new ArrayList<ObjectNewSlot>();
    }

    public static VectorModel getInstance(){
        if(instance == null){
            instance = new VectorModel();
        }
        return instance;
    }

    public ArrayList<TimeSlotObject> getTimeSlotObjectVector() {
        return timeSlotObjectVector;
    }

    public void setTimeSlotObjectVector(TimeSlotObject timeSlotObjectVector) {
        this.timeSlotObjectVector.add(timeSlotObjectVector);
    }

    public void clearTimeSlotVector(){
        if(timeSlotObjectVector != null && timeSlotObjectVector.size() > 0){
            timeSlotObjectVector.clear();
        }
    }

    public ArrayList<IncomingObject> getIncomingObjects() {
        return incomingObjects;
    }

    public void setIncomingObjects(IncomingObject incomingObjects) {
        this.incomingObjects.add(incomingObjects);
    }

    public void clearIncomingObjects(){
        if(incomingObjects != null && incomingObjects.size() > 0){
            incomingObjects.clear();
        }
    }

    public ArrayList<TicketObjectModel> getAssignTicketObjects() {
        return assignTicketObjects;
    }

    public void setAssignTicketObjects(TicketObjectModel ticketObject) {
        this.assignTicketObjects.add(ticketObject);
    }

    public void clearAssignTicketObjects(){
        if(assignTicketObjects != null && assignTicketObjects.size() > 0){
            assignTicketObjects.clear();
        }
    }

    public ArrayList<TicketObjectModel> getUnassignTicketObjects() {
        return unassignTicketObjects;
    }

    public void setUnassignTicketObjects(TicketObjectModel unassignTicketObjects) {
        this.unassignTicketObjects.add(unassignTicketObjects);
    }

    public void clearUnassignTicketObjects(){
        if(unassignTicketObjects != null && unassignTicketObjects.size() > 0){
            unassignTicketObjects.clear();
        }
    }

    public ArrayList<TruckActivitiesObject> getTruckActivitiesObjects() {
        return truckActivitiesObjects;
    }

    public void setTruckActivitiesObjects(TruckActivitiesObject truckActivitiesObjects) {
        this.truckActivitiesObjects.add(truckActivitiesObjects);
    }

    public void clearTruckActivitiesObjects(){
        if(truckActivitiesObjects != null && truckActivitiesObjects.size() > 0){
            truckActivitiesObjects.clear();
        }
    }

    public ArrayList<TruckActivitiesDetailObject> getTruckActivitiesDetailObjects() {
        return truckActivitiesDetailObjects;
    }

    public void setTruckActivitiesDetailObjects(TruckActivitiesDetailObject truckActivitiesDetailObject) {
        this.truckActivitiesDetailObjects.add(truckActivitiesDetailObject);
    }


    public void clearTruckDetailObjects(){
        if(truckActivitiesDetailObjects != null && truckActivitiesDetailObjects.size() > 0){
            truckActivitiesDetailObjects.clear();
        }
    }

    public ArrayList<TicketObject> getListAssignTicketObjects() {
        return listAssignTicketObjects;
    }

    public void setListAssignTicketObjects(TicketObject listTicketObject) {
        listAssignTicketObjects.add(listTicketObject);
    }

    public void clearListAssignTicketObjects(){
        if(listAssignTicketObjects != null && listAssignTicketObjects.size() > 0){
            listAssignTicketObjects.clear();
        }
    }

    public ArrayList<TicketObject> getListUnassignTicketObjects() {
        return listUnassignTicketObjects;
    }

    public void setListUnassignTicketObjects(TicketObject listUnassignTicketObjects) {
        this.listUnassignTicketObjects.add(listUnassignTicketObjects);
    }

    public void clearListUnassignTicketObjects(){
        if(listUnassignTicketObjects != null && listUnassignTicketObjects.size() > 0){
            listUnassignTicketObjects.clear();
        }
    }

    public ArrayList<TicketObjectDetail> getTicketObjectDetails() {
        return ticketObjectDetails;
    }

    public void setTicketObjectDetails(TicketObjectDetail ticketObjectDetails) {
        this.ticketObjectDetails.add(ticketObjectDetails);
    }

    public void clearTicketObjectDetails(){
        if(ticketObjectDetails != null && ticketObjectDetails.size() > 0){
            ticketObjectDetails.clear();
        }
    }

    public ArrayList<EditTicketObject> getEditTicketObjects() {
        return editTicketObjects;
    }

    public void setEditTicketObjects(EditTicketObject editTicketObjects) {
        this.editTicketObjects.add(editTicketObjects);
    }

    public void clearEditTicketObjects(){
        if(editTicketObjects != null && editTicketObjects.size() > 0)
            editTicketObjects.clear();
    }

    public ArrayList<String> getArrVin() {
        return arrVin;
    }

    public void setArrVin(String arrVin) {
        this.arrVin.add(arrVin);
    }

    public void clearArrVin(){
        if(arrVin != null && arrVin.size() > 0)
            arrVin.clear();
    }

    public ArrayList<TripObject> getTripObjects() {
        return tripObjects;
    }

    public void setTripObjects(TripObject tripObjects) {
        this.tripObjects.add(tripObjects);
    }

    public void clearTripObject(){
        if(tripObjects != null && tripObjects.size() > 0)
            tripObjects.clear();
    }

    public ArrayList<EditTicketObject> getSaveTicketObjects() {
        return saveTicketObjects;
    }

    public void setSaveTicketObjects(EditTicketObject saveTicketObject) {
        this.saveTicketObjects.add(saveTicketObject);
    }

    public void clearSaveTicketObjects(){
        if(saveTicketObjects != null && saveTicketObjects.size() > 0)
            saveTicketObjects.clear();
    }

    public ArrayList<String> getArrTrip() {
        return arrTrip;
    }

    public void setArrTrip(String arrTrip) {
        this.arrTrip.add(arrTrip);
    }

    public void clearArrTrip(){
        if(arrTrip != null && arrTrip.size() > 0)
            arrTrip.clear();
    }

    public ArrayList<SimpleObject> getCheckUpdateObject() {
        return checkUpdateObject;
    }

    public void setCheckUpdateObject(SimpleObject object) {
        this.checkUpdateObject.add(object);
    }

    public void clearCheckUpdate(){
        if(checkUpdateObject != null && checkUpdateObject.size() > 0){
            checkUpdateObject.clear();;
        }
    }

    public ArrayList<ScanVINObject> getScanVINObjects() {
        return scanVINObjects;
    }

    public void setScanVINObjects(ScanVINObject scanVINObjects) {
        this.scanVINObjects.add(scanVINObjects);
    }

    public void clearDatascanObject(){
        if(scanVINObjects != null && scanVINObjects != null && scanVINObjects.size() > 0){
            scanVINObjects.clear();
        }
    }

    public ArrayList<String> getObjectNewSlotsDateIn() {
        return objectNewSlotsDateIn;
    }

    public void setObjectNewSlotsDateIn(String objectNewSlotsDateIn) {
        this.objectNewSlotsDateIn.add(objectNewSlotsDateIn);
    }

    public void clearObjectNewSlotDateIn(){
        if(objectNewSlotsDateIn != null && objectNewSlotsDateIn.size() > 0){
            objectNewSlotsDateIn.clear();
        }
    }

    public ArrayList<String> getObjectNewSlotsSlotIn() {
        return objectNewSlotsSlotIn;
    }

    public void setObjectNewSlotsSlotIn(String objectNewSlotsSlotIn) {
        this.objectNewSlotsSlotIn.add(objectNewSlotsSlotIn);
    }

    public void clearObjectNewSlotSlotIn(){
        if(objectNewSlotsSlotIn != null && objectNewSlotsSlotIn.size() > 0){
            objectNewSlotsSlotIn.clear();
        }
    }

    public ArrayList<String> getObjectNewSlotsAreaIn() {
        return objectNewSlotsAreaIn;
    }

    public void setObjectNewSlotsAreaIn(String objectNewSlotsAreaIn) {
        this.objectNewSlotsAreaIn.add(objectNewSlotsAreaIn);
    }

    public void clearObjectNewSlotAreaIn(){
        if(objectNewSlotsAreaIn != null && objectNewSlotsAreaIn.size() > 0){
            objectNewSlotsAreaIn.clear();
        }
    }

    public ArrayList<String> getObjectNewSlotsDateOut() {
        return objectNewSlotsDateOut;
    }

    public void setObjectNewSlotsDateOut(String objectNewSlotsDateOut) {
        this.objectNewSlotsDateOut.add(objectNewSlotsDateOut);
    }

    public void clearObjectNewSlotDateOut(){
        if(objectNewSlotsDateOut != null && objectNewSlotsDateOut.size() > 0){
            objectNewSlotsDateOut.clear();
        }
    }

    public ArrayList<String> getObjectNewSlotsSlotOut() {
        return objectNewSlotsSlotOut;
    }

    public void setObjectNewSlotsSlotOut(String objectNewSlotsSlotOut) {
        this.objectNewSlotsSlotOut.add(objectNewSlotsSlotOut);
    }

    public void clearObjectNewSlotSlotOut(){
        if(objectNewSlotsSlotOut != null && objectNewSlotsSlotOut.size() > 0){
            objectNewSlotsSlotOut.clear();
        }
    }

    public ArrayList<String> getObjectNewSlotsAreaOut() {
        return objectNewSlotsAreaOut;
    }

    public void setObjectNewSlotsAreaOut(String objectNewSlotsAreaOut) {
        this.objectNewSlotsAreaOut.add(objectNewSlotsAreaOut);
    }

    public void clearObjectNewSlotAreaOut(){
        if(objectNewSlotsAreaOut != null && objectNewSlotsAreaOut.size() > 0){
            objectNewSlotsAreaOut.clear();
        }
    }

    public ArrayList<ObjectNewSlot> getObjectNewSlotsIn() {
        return objectNewSlotsIn;
    }

    public void setObjectNewSlotsIn(ObjectNewSlot objectNewSlots) {
        this.objectNewSlotsIn.add(objectNewSlots);
    }

    public void clearObjectNewSlotIn(){
        if(objectNewSlotsIn != null && objectNewSlotsIn.size() > 0){
            objectNewSlotsIn.clear();
        }
    }

    public ArrayList<ObjectNewSlot> getObjectNewSlotsOut() {
        return objectNewSlotsOut;
    }

    public void setObjectNewSlotsOut(ObjectNewSlot objectNewSlots) {
        this.objectNewSlotsOut.add(objectNewSlots);
    }

    public void clearObjectNewSlotOut(){
        if(objectNewSlotsOut != null && objectNewSlotsOut.size() > 0){
            objectNewSlotsOut.clear();
        }
    }
}
