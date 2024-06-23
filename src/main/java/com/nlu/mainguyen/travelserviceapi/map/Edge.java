package com.nlu.mainguyen.travelserviceapi.map;

//cạnh (Edge)
public class Edge {

    private double weight;//trọng số (weighted graph).
    private Vert source; // điểm đầu
    private Vert destination;// điểm  cuối

    public Edge(double weight, Vert source, Vert destination) {
        this.weight = weight;
        this.source = source;
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public Vert getSource() {
        return source;
    }

    public Vert getDestination() {
        return destination;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setSource(Vert source) {
        this.source = source;
    }

    public void setDestination(Vert destination) {
        this.destination = destination;
    }
}
