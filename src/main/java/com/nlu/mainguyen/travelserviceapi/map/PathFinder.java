package com.nlu.mainguyen.travelserviceapi.map;

import java.util.*;

public class PathFinder {
    private Vert[] vertices;
    private List<Vert> path;
    private double totalDistance;

    public Vert[] getVertices() {
        return vertices;
    }

    public List<Vert> getPath() {
        return path;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    // Sử dụng thuật toán Dijkstra để tìm đường đi ngắn nhất từ một điểm bắt đầu (start) đến tất cả các điểm còn lại.
    public void ShortestPath(Vert start) {
        // Khởi tạo các biến cần thiết
        path = new ArrayList<>();
        Vert current = start; // A
        totalDistance = 0; // tổng quảng đường

        // Thêm điểm bắt đầu vào đường đi
        path.add(current);

        // Lặp cho đến khi đi đến tất cả các điểm
        while (path.size() < vertices.length) {
            // Tìm điểm kế tiếp với khoảng cách ngắn nhất
            Vert nextVertex = null; // C
            double minDistance = Double.MAX_VALUE; // ngắn nhất là 5 km
            
            for (Edge edge : current.getNeighbours()) {
                Vert neighbor = edge.getDestination();
                // Trong danh sách đường đi chưa có điểm cần kiểm tra thì tiếp tục so sánh trọng số
                if (!path.contains(neighbor)) {
                    if (edge.getWeight() < minDistance) {
                        minDistance = edge.getWeight();
                        nextVertex = neighbor;
                    }
                }
            }

            // Thêm điểm kế tiếp vào đường đi
            path.add(nextVertex); // lưu C ==> A C
            current = nextVertex; // set điểm cần kiểm tra là C

            totalDistance += minDistance; // tổng quảng đường
        }

        // In ra đường đi ngắn nhất
        System.out.println("Vi vay, duong di ngan nhat la: " + path);
        System.out.println("Tong khoang cach: " + totalDistance);
    }

    public void InitGraph(Vert[] vertices) {
        this.vertices = vertices;

        // Lặp qua các Vert trong mảng
        for (int i = 0; i < vertices.length; i++) {
            Vert current = vertices[i];

            // Lặp qua các Vert còn lại
            for (int j = i + 1; j < vertices.length; j++) {
                Vert other = vertices[j];

                // Tính khoảng cách giữa current và other
                double distance = current.calculateDistance(other);// trọng số (weighted graph).
                // Máy Học trọng số khoảng cách giữa các điểm
                // Thêm cạnh vào các điểm
                current.addNeighbour(new Edge(distance, current, other));// A so với B
                other.addNeighbour(new Edge(distance, other, current));// B so với A

                // In khoảng cách
                System.out.println(">>Distance from " + current.getName() + " to " + other.getName() + ": " + distance);
                System.out.println("<<<Distance from " + other.getName() + " to " + current.getName() + ": " + distance);
            }
        }
    }
}
