package com.nlu.mainguyen.travelserviceapi.map;

import java.util.*;

// đỉnh (Vert)
public class Vert implements Comparable<Vert> {

    private boolean visited;
    private String name;
    private long id;
    private double latitude;
    private double longitude;
    private List<Edge> neighbours;
    private double dist = Double.MAX_VALUE;
    private Vert pr;
    private Object info;

    public Vert(long id, String name, double latitude, double longitude, Object info) {
        this.id = id;
        this.info = info;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.neighbours = new ArrayList<>();
    }

    public double calculateDistance(Vert other) {
        double lat1 = this.getLatitude();
        double lon1 = this.getLongitude();
        double lat2 = other.getLatitude();
        double lon2 = other.getLongitude();

        // Công thức tính khoảng cách trên mặt đất
        double R = 6371; // Bán kính trái đất (km)
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a1 = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a1), Math.sqrt(1 - a1));
        double distance = R * c;

        return distance;
    }

    public List<Edge> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Edge edge) {
        this.neighbours.add(edge);
    }

    public void setNeighbours(List<Edge> neighbours) {
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean Visited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vert getPr() {
        return pr;
    }

    public void setPr(Vert pr) {
        this.pr = pr;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
        // return this.id + " " this.name + " " + this.latitude + " " + this.longitude;
    }

    @Override
    public int compareTo(Vert otherV) {
        return Double.compare(this.dist, otherV.getDist());
    }
}
