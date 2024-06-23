package com.nlu.mainguyen.travelserviceapi.map;

public class Dijkstra {
    public static void main(String[] args) {
        System.out.println("\n==============================");
        // Khởi tạo các điểm
        Vert vA = new Vert(1, "A", 10.745599477893528, 106.73375715767084, null);
        Vert vB = new Vert(2, "B", 10.773690, 106.698270, null);
        Vert vC = new Vert(3, "C", 21.032695777179843, 105.8398253541974, null);
        Vert vD = new Vert(4, "D", 10.78890184446407, 106.70472658193842, null);
        Vert vE = new Vert(5, "E", 11.143514074434606, 106.461428559136, null);

        // Tạo mảng các điểm
        Vert[] vertices = {
                vA,
                vB,
                vC,
                vD,
                vE
        };

        // Khởi tạo PathFinder và tính toán đường đi ngắn nhất
        PathFinder shortestPath = new PathFinder();
        // Tính toán khoảng cách giữa các điểm
        shortestPath.InitGraph(vertices);
        // Tìm đường đi ngắn nhất qua tất cả các điểm
        shortestPath.ShortestPath(vA);
        System.out.println("Vi vay, duong di ngan nhat la: " + shortestPath.getPath());
        System.out.println("Tong khoang cach: " + shortestPath.getTotalDistance());
    }
}