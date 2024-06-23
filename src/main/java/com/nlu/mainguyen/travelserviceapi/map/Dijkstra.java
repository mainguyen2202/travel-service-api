package com.nlu.mainguyen.travelserviceapi.map;

public class Dijkstra {
    public static void main(String[] args) {
        System.out.println("\n==============================");
        // Khởi tạo các điểm
        Vert vA = new Vert("A", 10.745599477893528, 106.73375715767084);
        Vert vB = new Vert("B", 10.773690, 106.698270);
        Vert vC = new Vert("C", 21.032695777179843, 105.8398253541974);
        Vert vD = new Vert("D", 10.78890184446407, 106.70472658193842);
        Vert vE = new Vert("E", 11.143514074434606, 106.461428559136);

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