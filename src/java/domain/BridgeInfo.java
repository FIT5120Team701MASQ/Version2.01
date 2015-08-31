/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Loops
 */
public class BridgeInfo {

    private String objectId;
    private String collName1;
    private String collName2;
    private String collName3;
    private double minClearance;
    private double length;
    private double width;
    private double lat;
    private double longit;

    public BridgeInfo() {
    }

    public BridgeInfo(String objectId, String collName1,
            String collName2, String collName3, double minClearance,
            double length, double width, double lat, double longit) {
        this.objectId = objectId;
        this.collName1 = collName1;
        this.collName2 = collName2;
        this.collName3 = collName3;
        this.minClearance = minClearance;
        this.length = length;
        this.width = width;
        this.lat = lat;
        this.longit = longit;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCollName1() {
        return collName1;
    }

    public void setCollName1(String collName1) {
        this.collName1 = collName1;
    }

    public String getCollName2() {
        return collName2;
    }

    public void setCollName2(String collName2) {
        this.collName2 = collName2;
    }

    public String getCollName3() {
        return collName3;
    }

    public void setCollName3(String collName3) {
        this.collName3 = collName3;
    }

    public double getMinClearance() {
        return minClearance;
    }

    public void setMinClearance(double minClearance) {
        this.minClearance = minClearance;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongit() {
        return longit;
    }

    public void setLongit(double longit) {
        this.longit = longit;
    }

    @Override
    public String toString() {
        return "brigeInfo{" + "objectId=" + objectId + ", collName1=" 
                + collName1 + ", collName2="
                + collName2 + ", collName3=" + collName3 + ", minClearance="
                + minClearance + ", length=" + length + ", width=" + width
                + ", lat=" + lat + ", longit=" + longit + '}';
    }

}
